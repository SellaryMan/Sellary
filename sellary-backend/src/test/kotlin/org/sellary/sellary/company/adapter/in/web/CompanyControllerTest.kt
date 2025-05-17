package org.sellary.sellary.company.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.sellary.sellary.adaptor.`in`.CompanyController
import org.sellary.sellary.company.application.port.dto.CompanyCreateCommand
import org.sellary.sellary.company.application.port.`in`.CreateCompanyUseCase
import org.sellary.sellary.core.`in`.web.ExceptionControllerAdvice
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

class CompanyControllerTest {

    private lateinit var controller: CompanyController

    private lateinit var createUseCase: CreateCompanyUseCase

    private lateinit var objectMapper: ObjectMapper
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        createUseCase = mock()
        val validator = LocalValidatorFactoryBean()
        validator.afterPropertiesSet()

        controller = CompanyController(
            createUseCase = createUseCase
        )

        mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .setControllerAdvice(ExceptionControllerAdvice())
            .setValidator(validator)
            .build()

        objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .registerKotlinModule()
    }

    @Nested
    @DisplayName("회사 생성 api 요청 시")
    inner class CreateCompanyTest {

        @Test
        @DisplayName("최소 필수 정보만 포함된 요청도 처리한다")
        fun `save company with minimum data`() {
            // given
            val command = CompanyCreateCommand(
                name = "테스트 회사",
            )

            // when & then
            mockMvc.perform(
                post("/company")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(command))
            )
                .andExpect(status().isOk)

            verify(createUseCase, times(1)).create(any())
        }

        @Test
        @DisplayName("필수 필드가 누락된 경우 예외가 발생한다")
        fun `save company with missing required fields`() {
            // given
            val invalidCommand = """
            {}
            """.trimIndent()

            // when & then
            mockMvc.perform(
                post("/company")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(invalidCommand)
            )
                .andExpect(status().isBadRequest)

            verify(createUseCase, never()).create(any())
        }

        @Test
        @DisplayName("이름 필드가 비어있는 경우 예외가 발생한다")
        fun createShippedProduct_blankName_returns400() {
            // given
            val command = CompanyCreateCommand(
                name = ""
            )

            // when & then
            mockMvc.perform(
                post("/company")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(command))
            )
                .andExpect(status().isBadRequest)
        }

    }
}