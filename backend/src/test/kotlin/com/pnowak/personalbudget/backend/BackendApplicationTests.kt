package com.pnowak.personalbudget.backend

import org.assertj.core.api.Assertions.assertThat
import com.pnowak.personalbudget.backend.controller.AccountController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private lateinit var controller: AccountController

	@Test
	fun contextLoads() {
		assertThat(controller).isNotNull
	}

}
