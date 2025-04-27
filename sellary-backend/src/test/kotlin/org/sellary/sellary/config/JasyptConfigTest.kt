package org.sellary.sellary.config

import org.assertj.core.api.Assertions
import org.jasypt.encryption.StringEncryptor
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [JasyptConfig::class])
@ActiveProfiles("local")
internal class JasyptConfigTest {

    @Autowired
    lateinit var stringEncryptor: StringEncryptor

    @Test
    fun test() {
        val message = "sellary"
        val encryptedMessage = stringEncryptor.encrypt(message)
        val decryptedMessage = stringEncryptor.decrypt(encryptedMessage)

        println("encryptedMessage = $encryptedMessage")
        println("decryptedMessage = $decryptedMessage")
        Assertions.assertThat(message).isEqualTo(decryptedMessage)
    }
}