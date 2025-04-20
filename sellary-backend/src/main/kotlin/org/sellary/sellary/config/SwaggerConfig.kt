package org.sellary.sellary.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        val contact = Contact()
            .name("Sellary Team")

        val license = License()
            .name("Apache 2.0")
            .url("https://www.apache.org/licenses/LICENSE-2.0.html")

        val info = Info()
            .title("Sellary API")
            .version("v0.0.1-SNAPSHOT")
            .description("Sellary REST API Documentation")
            .contact(contact)
            .license(license)

        return OpenAPI()
            .info(info)
    }
}