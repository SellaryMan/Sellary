package org.sellary.sellary.shippedproduct.adapter.`in`.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.tags.Tag
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductCreateCommand
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductDto
import org.sellary.sellary.shippedproduct.application.port.dto.ShippedProductQuery
import org.sellary.sellary.shippedproduct.application.port.`in`.CreateShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.`in`.DeleteShippedProductUseCase
import org.sellary.sellary.shippedproduct.application.port.`in`.ReadShippedProductUseCase
import org.springdoc.core.annotations.ParameterObject
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shipped-product")
@Tag(name = "출고 상품 API", description = "출고 상품 관리를 위한 API")
class ShippedProductController(
    private val createUseCase: CreateShippedProductUseCase,
    private val readUseCase: ReadShippedProductUseCase,
    private val deleteUseCase: DeleteShippedProductUseCase,
) {
    @PostMapping
    @Operation(
        summary = "출고 상품 생성",
        description = "신규 출고 상품을 생성합니다."
    )
    @RequestBody(
        description = "출고 상품 생성 정보",
        required = true,
        content = [Content(
            mediaType = "application/json",
            examples = [
                ExampleObject(
                    name = "기본 예시",
                    value = """
                    {
                      "name": "고추장",
                      "quantity": 100,
                      "type": "PRODUCT",
                      "code": "PRD-001",
                      "barcode": "1234567890123",
                      "keywords": ["매운맛", "장류"],
                      "expDate": "2025-12-31T00:00:00",
                      "expQuantity": 100,
                      "manufactureDate": "2025-01-01T00:00:00",
                      "lowStockThresholdDay": 30,
                      "noShippingThresholdDay": 15,
                      "unitPurchasePrice": 5000.0,
                      "boxPurchasePrice": 45000.0,
                      "palletPurchasePrice": 400000.0,
                      "unitSellingPrice": 8000.0,
                      "boxSellingPrice": 75000.0,
                      "palletSellingPrice": 700000.0
                    }
                    """
                ),
                ExampleObject(
                    name = "최소 필수 정보",
                    value = """
                    {
                      "name": "고추장",
                      "quantity": 100,
                      "type": "PRODUCT",
                      "code": "PRD-001"
                    }
                    """
                )
            ],
            schema = Schema(implementation = ShippedProductCreateCommand::class)
        )]
    )
    fun createShippedProduct(@org.springframework.web.bind.annotation.RequestBody createShippedProductCommand: ShippedProductCreateCommand) =
        createUseCase.create(createShippedProductCommand)

    @GetMapping
    @Operation(
        summary = "출고 상품 조회",
        description = "조건에 맞는 출고 상품 목록을 조회합니다. ID, 이름, 타입, 코드 등으로 필터링할 수 있습니다."
    )
    fun getShippedProduct(
        @ParameterObject query: ShippedProductQuery
    ): List<ShippedProductDto> =
        readUseCase.readShippedProduct(query)


    @Operation(
        summary = "출고 상품 삭제",
        description = "id에 해당하는 출고상품을 삭제 합니다."
    )
    @DeleteMapping("{id}")
    fun deleteShippedProduct(@PathVariable id: Long) =
        deleteUseCase.delete(id)
}