package org.sellary.sellary.sellingproduct.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.tags.Tag
import org.sellary.sellary.sellingproduct.service.SellingProductService
import org.sellary.sellary.sellingproduct.service.dto.SellingProductDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductRegisterDto
import org.sellary.sellary.sellingproduct.service.dto.SellingProductUpdateDto
import org.springdoc.core.annotations.ParameterObject
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/selling-product")
@Tag(name = "판매 상품 API", description = "판매 상품 관리를 위한 API")
class SellingProductController(
    private val sellingProductService: SellingProductService
) {

    @GetMapping
    @Operation(
        summary = "판매 상품 리스트 조회",
        description = "조건에 맞는 판매 상품 목록을 조회합니다. ID, 이름, 타입, 코드 등으로 필터링할 수 있습니다."
    )
    fun getSellingProductList(
        @ParameterObject query: SellingProductQuery
    ): List<SellingProductDto> =
        sellingProductService.getSellingProductList(query)

    @GetMapping("{id}")
    @Operation(
        summary = "판매 상품 단건 조회",
        description = "id로 판매 상품을 조회합니다."
    )
    fun getSellingProduct(@PathVariable id : Long): SellingProductDto? =
        sellingProductService.getSellingProductById(id)

    @PostMapping
    @Operation(
        summary = "판매 상품 생성",
        description = "판매 상품을 생성합니다."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "판매 상품 생성 정보",
        required = true,
        content = [
            Content(
                mediaType = "application/json",
                examples = [
                    ExampleObject(
                        name = "기본 예시",
                        value = """
                            {
                                "name": "떡볶이",
                                "code": "SELLING-PRODUCT",
                                "barcode": "SEPR-001",
                                "tags": ["고추장", "떡"],
                                "shippedProductList": []
                            }
                        """
                    )
                ]
            )
        ]
    )
    fun registerSellingProduct(@RequestBody registerDto : SellingProductRegisterDto) =
        sellingProductService.registerSellingProduct(registerDto)

    @DeleteMapping("{id}")
    @Operation(
        summary = "판매 상품 삭제",
        description = "판매 상품 단건을 삭제합니다."
    )
    fun removeSellingProduct(@PathVariable id : Long) =
        sellingProductService.removeSellingProductById(id)

    @PutMapping
    @Operation(
        summary = "판매 상품 수정",
        description = "판매 상품을 수정합니다."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "판매 상품 생성 정보",
        required = true,
        content = [
            Content(
                mediaType = "application/json",
                examples = [
                    ExampleObject(
                        name = "기본 예시",
                        value = """
                            {
                                "id": 1,
                                "name": "떡볶이",
                                "code": "SELLING-PRODUCT",
                                "barcode": "SEPR-001",
                                "tags": ["고추장", "떡"]
                            }
                        """
                    )
                ]
            )
        ]
    )
    fun updateSellingProduct(@RequestBody updateDto: SellingProductUpdateDto) =
        sellingProductService.updateSellingProduct(updateDto)
}