package org.sellary.sellary.shippedproduct.application.port.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType

data class ShippedProductQuery(
    @Schema(description = "id", example = "1")
    val id: Long?,

    @Schema(description = "출고상품 명", example = "고추장")
    val name: String?,

    @Schema(
        description = "출고 상품 타입 (재료 / 부재료 / 출고 상품)",
        example = "ingredient",
        allowableValues = ["ingredient", "sub-ingredient", "product"]
    )
    val type: ShippedProductType?,

    @Schema(description = "출고상품 코드", example = "PRD-001")
    val code: String?,

    @Schema(
        description = "검색 조건",
        example = "NAME",
        allowableValues = ["ID", "NAME", "TYPE", "CODE", "MULTI_CONDITION"]
    )
    val queryType: ShippedProductQueryType?,
)

enum class ShippedProductQueryType {
    @Schema(description = "ID 로 검색")
    ID,

    @Schema(description = "name 으로 검색")
    NAME,

    @Schema(description = "type 으로 검색")
    TYPE,

    @Schema(description = "code 로 검색")
    CODE,

    @Schema(description = "(미구현) 여러 조건으로 검색")
    MULTI_CONDITION
}