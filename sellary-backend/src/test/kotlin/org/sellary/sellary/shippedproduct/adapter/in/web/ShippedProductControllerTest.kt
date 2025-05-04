package org.sellary.sellary.shippedproduct.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.sellary.sellary.core.`in`.web.ExceptionControllerAdvice
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductDto
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductExpDto
import org.sellary.sellary.shippedproduct.application.port.`in`.CreateShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.`in`.DeleteShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.`in`.ReadShippedProductUseCase
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import java.time.LocalDateTime

class ShippedProductControllerTest {

    private lateinit var createUseCase: CreateShippedProductUseCase
    private lateinit var readUseCase: ReadShippedProductUseCase
    private lateinit var deleteUseCase: DeleteShippedProductUseCase

    private lateinit var objectMapper: ObjectMapper
    private lateinit var controller: ShippedProductController
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        createUseCase = mock()
        readUseCase = mock()
        deleteUseCase = mock()
        val validator = LocalValidatorFactoryBean()
        validator.afterPropertiesSet()

        controller = ShippedProductController(
            createUseCase = createUseCase,
            readUseCase = readUseCase,
            deleteUseCase = deleteUseCase
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
    @DisplayName("createShippedProduct 메소드는")
    inner class CreateShippedProductTest {

        @Test
        @DisplayName("유효한 요청이 들어오면 상품을 생성하고 200 응답을 반환한다")
        fun createShippedProduct_validRequest_returns200() {
            // given
            val command = ShippedProductCreateCommand(
                name = "고추장",
                type = ShippedProductType.PRODUCT,
                code = "PRD-001",
                barcode = "1234567890123",
                keywords = setOf("매운맛", "장류"),
                expDate = LocalDateTime.of(2025, 12, 31, 0, 0),
                quantity = 100,
                manufactureDate = LocalDateTime.of(2025, 1, 1, 0, 0),
                lowStockThresholdDay = 30,
                noShippingThresholdDay = 15,
                unitPurchasePrice = 5000.0,
                boxPurchasePrice = 45000.0,
                palletPurchasePrice = 400000.0,
                unitSellingPrice = 8000.0,
                boxSellingPrice = 75000.0,
                palletSellingPrice = 700000.0
            )

            // when & then
            mockMvc.perform(
                post("/shipped-product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(command))
            )
                .andExpect(status().isOk)

            verify(createUseCase, times(1)).create(any())
        }

        @Test
        @DisplayName("최소 필수 정보만 포함된 요청도 처리한다")
        fun createShippedProduct_minimumRequiredFields_returns200() {
            // given
            val command = ShippedProductCreateCommand(
                name = "고추장",
                quantity = 100,
                type = ShippedProductType.PRODUCT,
                code = "PRD-001"
            )

            // when & then
            mockMvc.perform(
                post("/shipped-product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(command))
            )
                .andExpect(status().isOk)

            verify(createUseCase, times(1)).create(any())
        }

        @Test
        @DisplayName("필수 필드가 누락된 경우 예외가 발생한다")
        fun createShippedProduct_missingRequiredFields_returns400() {
            // given
            val invalidCommand = """
            {
                "name": "고추장",
                "quantity": 100
            }
            """.trimIndent()

            // when & then
            mockMvc.perform(
                post("/shipped-product")
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
            val command = ShippedProductCreateCommand(
                name = "",
                quantity = 100,
                type = ShippedProductType.PRODUCT,
                code = "PD-LIKE"
            )

            // when & then
            mockMvc.perform(
                post("/shipped-product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(command))
            )
                .andExpect(status().isBadRequest)
        }

        @Test
        @DisplayName("코드 필드가 비어있는 경우 예외가 발생한다")
        fun createShippedProduct_blankCode_returns400() {
            // given
            val command = ShippedProductCreateCommand(
                name = "고추",
                quantity = 100,
                type = ShippedProductType.PRODUCT,
                code = ""
            )

            // when & then
            mockMvc.perform(
                post("/shipped-product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(command))
            )
                .andExpect(status().isBadRequest)
        }

        @Test
        @DisplayName("잘못된 JSON 형식이 들어오면 400 응답을 반환한다")
        fun createShippedProduct_invalidJson_returns400() {
            // given
            val invalidJson = """
            {
                "name": "고추장",
                "quantity": 100,
                "type": "PRODUCT"
                "code": "PRD-001"
            }
            """.trimIndent() // 쉼표가 누락된 JSON

            // when & then
            mockMvc.perform(
                post("/shipped-product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(invalidJson)
            )
                .andExpect(status().isBadRequest)

            verify(createUseCase, never()).create(any())
        }
    }

    @Nested
    @DisplayName("getShippedProduct 메소드는")
    inner class GetShippedProductTest {

        @Test
        @DisplayName("ID로 상품을 조회할 수 있다")
        fun getShippedProduct_byId_returnsMatchingProducts() {
            val expectedProducts = listOf(
                ShippedProductDto(
                    id = 1L,
                    name = "고추장",
                    type = ShippedProductType.PRODUCT,
                    code = "PRD-001",
                    barcode = "1234567890123",
                    tags = setOf("매운맛", "장류"),
                    shippedProductExp = listOf(
                        ShippedProductExpDto(
                            id = 1L,
                            expDate = LocalDateTime.of(2025, 12, 31, 0, 0),
                            quantity = 100,
                            manufactureDate = LocalDateTime.of(2025, 1, 1, 0, 0),
                            lowStockThresholdDay = 30,
                            noShippingThresholdDay = 15
                        )
                    )
                )
            )
            whenever(readUseCase.readShippedProduct(any())).thenReturn(expectedProducts)

            // when & then
            mockMvc.perform(
                get("/shipped-product")
                    .param("id", "1")
                    .param("queryType", "ID")
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("고추장"))
                .andExpect(jsonPath("$[0].type").value("PRODUCT"))

            verify(readUseCase, times(1)).readShippedProduct(any())
        }

        @Test
        @DisplayName("이름으로 상품을 조회할 수 있다")
        fun getShippedProduct_byName_returnsMatchingProducts() {
            // given
            val expectedProducts = listOf(
                ShippedProductDto(
                    id = 1L,
                    name = "고추장",
                    type = ShippedProductType.PRODUCT,
                    code = "PRD-001"
                ),
                ShippedProductDto(
                    id = 2L,
                    name = "매운 고추장",
                    type = ShippedProductType.PRODUCT,
                    code = "PRD-002"
                )
            )

            whenever(readUseCase.readShippedProduct(any())).thenReturn(expectedProducts)

            // when & then
            mockMvc.perform(
                get("/shipped-product")
                    .param("name", "고추장")
                    .param("queryType", "NAME")
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].name").value("고추장"))
                .andExpect(jsonPath("$[1].name").value("매운 고추장"))

            verify(readUseCase, times(1)).readShippedProduct(any())
        }

        @Test
        @DisplayName("타입으로 상품을 조회할 수 있다")
        fun getShippedProduct_byType_returnsMatchingProducts() {
            val expectedProducts = listOf(
                ShippedProductDto(
                    id = 1L,
                    name = "고추장",
                    type = ShippedProductType.PRODUCT,
                    code = "PRD-001"
                ),
                ShippedProductDto(
                    id = 2L,
                    name = "된장",
                    type = ShippedProductType.PRODUCT,
                    code = "PRD-002"
                )
            )

            whenever(readUseCase.readShippedProduct(any())).thenReturn(expectedProducts)

            // when & then
            mockMvc.perform(
                get("/shipped-product")
                    .param("type", "PRODUCT")
                    .param("queryType", "TYPE")
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].type").value("PRODUCT"))
                .andExpect(jsonPath("$[1].type").value("PRODUCT"))

            verify(readUseCase, times(1)).readShippedProduct(any())
        }

        @Test
        @DisplayName("코드로 상품을 조회할 수 있다")
        fun getShippedProduct_byCode_returnsMatchingProducts() {
            // given
            val expectedProducts = listOf(
                ShippedProductDto(
                    id = 1L,
                    name = "고추장",
                    type = ShippedProductType.PRODUCT,
                    code = "PRD-001"
                )
            )

            whenever(readUseCase.readShippedProduct(any())).thenReturn(expectedProducts)

            // when & then
            mockMvc.perform(
                get("/shipped-product")
                    .param("code", "PRD-001")
                    .param("queryType", "CODE")
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].code").value("PRD-001"))

            verify(readUseCase, times(1)).readShippedProduct(any())
        }

        @Test
        @DisplayName("조회 결과가 없는 경우 빈 배열을 반환한다")
        fun getShippedProduct_noResults_returnsEmptyArray() {
            // given
            whenever(readUseCase.readShippedProduct(any())).thenReturn(emptyList())

            // when & then
            mockMvc.perform(
                get("/shipped-product")
                    .param("name", "없는상품")
                    .param("queryType", "NAME")
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0))

            verify(readUseCase, times(1)).readShippedProduct(any())
        }

        @Test
        @DisplayName("queryType이 누락된 경우에도 요청을 처리한다")
        fun getShippedProduct_missingQueryType_stillProcesses() {
            // given
            val expectedProducts = listOf(
                ShippedProductDto(
                    id = 1L,
                    name = "고추장",
                    type = ShippedProductType.PRODUCT,
                    code = "PRD-001"
                )
            )

            whenever(readUseCase.readShippedProduct(any())).thenReturn(expectedProducts)

            // when & then
            mockMvc.perform(
                get("/shipped-product")
                    .param("name", "고추장")
            )
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))

            verify(readUseCase, times(1)).readShippedProduct(any())
        }

        @Test
        @DisplayName("읽기 UseCase에서 예외가 발생하면 적절히 처리한다")
        fun getShippedProduct_exceptionInUseCase_returns500() {
            // given
            whenever(readUseCase.readShippedProduct(any()))
                .thenThrow(EntityNotFoundException("Database error"))

            // when & then
            mockMvc.perform(
                get("/shipped-product")
                    .param("id", "1")
                    .param("queryType", "ID")
            )
                .andExpect(status().isNotFound)

            verify(readUseCase, times(1)).readShippedProduct(any())
        }
    }

    @Nested
    @DisplayName("deleteShippedProduct 메소드는")
    inner class DeleteShippedProductTest {

        @Test
        @DisplayName("ID로 상품을 삭제할 수 있다")
        fun deleteShippedProduct_validId_deletesAndReturns200() {
            // given
            val productId = 1L

            // when & then
            mockMvc.perform(
                delete("/shipped-product/{id}", productId)
            )
                .andExpect(status().isOk)

            verify(deleteUseCase, times(1)).delete(productId)
        }

        @Test
        @DisplayName("존재하지 않는 ID로 삭제 요청시 적절히 처리한다")
        fun deleteShippedProduct_nonExistentId_handlesAppropriately() {
            // given
            val nonExistentId = 999L
            doThrow(EmptyResultDataAccessException(1))
                .whenever(deleteUseCase).delete(nonExistentId)

            // when & then
            mockMvc.perform(
                delete("/shipped-product/{id}", nonExistentId)
            )
                .andExpect(status().isNotFound)

            verify(deleteUseCase, times(1)).delete(nonExistentId)
        }

        @Test
        @DisplayName("잘못된 형식의 ID로 요청시 400 응답을 반환한다")
        fun deleteShippedProduct_invalidIdFormat_returns400() {
            // when & then
            mockMvc.perform(
                delete("/shipped-product/{id}", "invalid-id")
            )
                .andExpect(status().isBadRequest)

            verify(deleteUseCase, never()).delete(any())
        }
    }
}