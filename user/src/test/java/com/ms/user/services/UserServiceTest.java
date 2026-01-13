package com.ms.user.services;

import com.ms.user.exceptions.EmailAlreadyExistsException;
import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProducer userProducer;

    @InjectMocks
    private UserService userService;

    private UserModel userModel;

    @BeforeEach
    void setup() {
        userModel = new UserModel();
        userModel.setName("Joao Teste");
        userModel.setEmail("joao@email.com");
    }

    @Test
    void shouldSaveUserAndPublishMessage_whenEmailDoesNotExist() {

        when(userRepository.existsUserModelByEmail(userModel.getEmail())).thenReturn(false);

        UserModel saved = new UserModel();
        saved.setName((userModel.getName()));
        saved.setEmail(userModel.getEmail());

        when(userRepository.save(any(UserModel.class))).thenReturn(saved);

        UserModel result = userService.save(userModel);

        assertNotNull(result);
        assertEquals("Joao Teste", result.getName());
        assertEquals("joao@email.com", result.getEmail());

        verify(userRepository, times(1)).existsUserModelByEmail("joao@email.com");
        verify(userRepository, times(1)).save(userModel);
        verify(userProducer, times(1)).publishMessageEmail(saved);

        verifyNoMoreInteractions(userRepository, userProducer);
    }

    @Test
    void shouldThrowExceptionAndNotSaveNorPublish_whenEmailAlreadyExists() {
        when(userRepository.existsUserModelByEmail(userModel.getEmail())).thenReturn(true);

        EmailAlreadyExistsException ex = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.save(userModel)
        );

        assertTrue(ex.getMessage().contains("E-mail já cadastrado"));
        assertTrue(ex.getMessage().contains("joao@email.com"));

        verify(userRepository, times(1)).existsUserModelByEmail("joao@email.com");

        verify(userRepository, never()).save(any());
        verify(userProducer, never()).publishMessageEmail(any());

        verifyNoMoreInteractions(userRepository, userProducer);
    }
}
