package org.sellary.sellary.shippedproduct.application.port.`in`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductExp
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductQuery
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductQueryType
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductCommandPort
import org.sellary.sellary.shippedproduct.application.port.out.ShippedProductQueryPort
import org.sellary.sellary.shippedproduct.application.service.ShippedProductCommandService
import org.sellary.sellary.shippedproduct.application.service.ShippedProductQueryService
import java.time.LocalDateTime
import kotlin.test.assertEquals


class ShippedProductUseCaseTest {

    private lateinit var createShippedProductUseCase: CreateShippedProductUseCase
    private lateinit var deleteUseCase: DeleteShippedProductUseCase
    private lateinit var readUseCase: ReadShippedProductUseCase

    private lateinit var mockCommandPort: ShippedProductCommandPort
    private lateinit var mockQueryPort: ShippedProductQueryPort

    @BeforeEach
    fun setUp() {
        mockCommandPort = mock()
        mockQueryPort = mock()

        val commandService = ShippedProductCommandService(
            mockCommandPort
        )
        createShippedProductUseCase = commandService
        deleteUseCase = commandService

        val queryService = ShippedProductQueryService(
            mockQueryPort
        )
        readUseCase = queryService
    }

    @Nested
    @DisplayName("출고상품을 생성할 때")
    inner class Create {

        private val testCommand = ShippedProductCreateCommand(
            name = "고추장",
            quantity = 12,
            type = ShippedProductType.INGREDIENT,
            code = "PROD-1",
            barcode = "1234",
            keywords = setOf("고추", "장"),
            expDate = LocalDateTime.now(),
        )

        @Test
        @DisplayName("dto 가 domain 으로 변환되어 Port 를 통해 저장되어야 한다")
        fun `Create shipped product should call command port with correct domain object`() {
            // when
            createShippedProductUseCase.create(testCommand)

            // then
            val productCaptor = argumentCaptor<ShippedProduct>()
            verify(mockCommandPort, times(1)).create(productCaptor.capture())

            val capturedProduct = productCaptor.firstValue
            assertEquals(testCommand.name, capturedProduct.name)
            assertEquals(testCommand.type, capturedProduct.type)
            assertEquals(testCommand.code, capturedProduct.code)
            assertEquals(testCommand.barcode, capturedProduct.barcode)
            assertEquals(testCommand.quantity, capturedProduct.shippedProductExp.first().quantity)
            assertEquals(testCommand.expDate, capturedProduct.shippedProductExp.first().expDate)
        }
    }

    @Nested
    @DisplayName("출고상품을 조회할 때")
    inner class Read {

        @Test
        @DisplayName("id로 조회 시, id 조회를 사용해야한다")
        fun `Use ID Query when query with id`() {

            // given
            val query = ShippedProductQuery(
                queryType = ShippedProductQueryType.ID,
                id = 1L,
            )

            val shippedProduct = ShippedProduct(
                id = 1,
                name = "고추장",
                type = ShippedProductType.INGREDIENT,
                code = "PROD-1",
                barcode = "1234",
                tags = setOf("고추", "장"),
                shippedProductExp = listOf(ShippedProductExp(id = 2L, quantity = 12)),
            )
            whenever(mockQueryPort.findById(any()))
                .thenReturn(
                    shippedProduct
                )

            // when
            val shippedProductDtoList = readUseCase.readShippedProduct(query)

            // then
            verify(mockQueryPort, times(1)).findById(query.id!!)

            assertEquals(query.id!!, shippedProductDtoList.first().id)
            assertEquals(shippedProduct.name, shippedProductDtoList.first().name)
            assertEquals(shippedProduct.type, shippedProductDtoList.first().type)
            assertEquals(shippedProduct.code, shippedProductDtoList.first().code)
            assertEquals(shippedProduct.barcode, shippedProductDtoList.first().barcode)
            assertEquals(
                shippedProduct.shippedProductExp.first().id,
                shippedProductDtoList.first().shippedProductExp.first().id
            )
            assertEquals(
                shippedProduct.shippedProductExp.first().quantity,
                shippedProductDtoList.first().shippedProductExp.first().quantity
            )
            assertEquals(shippedProduct.tags, shippedProductDtoList.first().tags)
        }

        @Test
        @DisplayName("타입으로 조회 시, 타입 조회를 사용해야한다")
        fun `Use Type Query when query with type`() {
            // given
            val query = ShippedProductQuery(
                queryType = ShippedProductQueryType.TYPE,
                type = ShippedProductType.INGREDIENT,
            )

            // when
            readUseCase.readShippedProduct(query)

            // then
            verify(mockQueryPort, times(1)).findByType(query.type!!)
        }
    }
}
