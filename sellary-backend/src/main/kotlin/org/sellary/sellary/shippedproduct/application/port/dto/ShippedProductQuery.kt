package org.sellary.sellary.shippedproduct.application.port.dto

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType

data class ShippedProductQuery(
    @field:Parameter(
        name = "id",
        description = "출고 상품 ID",
        example = "1",
    )
    val id: Long?,

    @field:Parameter(
        name = "name",
        description = "출고 상품 명",
        example = "고추장",
        `in` = ParameterIn.QUERY,
        schema = Schema(description = "출고 상품 명", example = "고추장", type = "string")
    )
    val name: String?,

    @field:Parameter(
        name = "type",
        description = "출고 상품 타입",
        example = "PRODUCT",
        `in` = ParameterIn.QUERY,
        schema = Schema(allowableValues = ["INGREDIENT", "SUB_INGREDIENT", "PRODUCT"])
    )
    val type: ShippedProductType?,

    @field:Parameter(
        name = "code",
        `in` = ParameterIn.QUERY,
        schema = Schema(description = "출고 상품 코드", example = "PRD-001", type = "string")
    )
    val code: String?,

    @field:Parameter(
        name = "queryType",
        description = "검색 조건 타입",
        example = "NAME",
        `in` = ParameterIn.QUERY,
        schema = Schema(
            example = "NAME",
            allowableValues = ["ID", "NAME", "TYPE", "CODE", "MULTI_CONDITION"]
        )
    )
    val queryType: ShippedProductQueryType?
)

enum class ShippedProductQueryType {
    @field:Parameter(description = "ID 로 검색")
    ID,

    @field:Parameter(description = "name 으로 검색")
    NAME,

    @field:Parameter(description = "type 으로 검색")
    TYPE,

    @field:Parameter(description = "code 로 검색")
    CODE,

    @field:Parameter(description = "(미구현) 여러 조건으로 검색")
    MULTI_CONDITION
}