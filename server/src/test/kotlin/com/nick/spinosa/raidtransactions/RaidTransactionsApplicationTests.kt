package com.nick.spinosa.raidtransactions

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.test.context.junit4.SpringRunner
import javax.persistence.Entity

@RunWith(SpringRunner::class)
@SpringBootTest
class RaidTransactionsApplicationTests {

	@Test
	fun contextLoads() {
	}
}

inline fun <reified T: Any> typeRef(): ParameterizedTypeReference<T> = object: ParameterizedTypeReference<T>(){}