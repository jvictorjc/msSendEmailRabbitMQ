package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import com.ms.user.exceptions.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository  userRepository;
    private final UserProducer userProducer;

    @Transactional
    public UserModel save(UserModel userModel){

        if(userRepository.existsUserModelByEmail(userModel.getEmail())){
            throw new EmailAlreadyExistsException(
                    "E-mail já cadastrado: " + userModel.getEmail());
        }

        userModel = userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }

}
