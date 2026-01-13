package com.ms.user;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("Desabilitado no CI: teste de contexto requer infraestrutura (Rabbit/DB)")
@SpringBootTest
class UserApplicationTests {

	@Test
	void contextLoads() {
	}

}
