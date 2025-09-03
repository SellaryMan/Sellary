package org.sellary.sellary.sellingproduct.controller

import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema

data class SellingProductQuery(
    @field:Parameter(
        name = "id",
        description = "판매 상품 ID",
        example = "1",
    )
    val id: Long? = null,

    @field:Parameter(
        name = "name",
        description = "판매 상품 명",
        example = "고추장",
        `in` = ParameterIn.QUERY,
        schema = Schema(description = "판매 상품 명", example = "고추장", type = "string")
    )
    val name: String? = null,

    @field:Parameter(
        name = "code",
        `in` = ParameterIn.QUERY,
        schema = Schema(description = "판매 상품 코드", example = "SELLING-PRODUCT", type = "string")
    )
    val code: String? = null,

    @field:Parameter(
        name = "queryType",
        description = "검색 조건 타입",
        example = "NAME",
        `in` = ParameterIn.QUERY,
        schema = Schema(
            example = "NAME",
            allowableValues = ["ID", "NAME", "CODE", "MULTI_CONDITION"]
        )
    )
    val queryType: SellingProductQueryType? = null
)

enum class SellingProductQueryType {
    @field:Parameter(description = "ID 로 검색")
    ID,

    @field:Parameter(description = "name 으로 검색")
    NAME,

    @field:Parameter(description = "code 로 검색")
    CODE,

    @field:Parameter(description = "(미구현) 여러 조건으로 검색")
    MULTI_CONDITION
}