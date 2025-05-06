package org.sellary.sellary.adaptor.`in`

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.sellary.sellary.company.application.port.dto.CompanyCreateCommand
import org.sellary.sellary.company.application.port.`in`.CreateCompanyUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/company")
@RestController
@Tag(name = "회사 API", description = "회사 정보 관리를 위한 API")
class CompanyController(
    val createUseCase: CreateCompanyUseCase,
) {

    @PostMapping
    @Operation(
        summary = "회사 생성",
        description = "신규 회사 정보를 생성합니다."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "회사 생성 정보",
        required = true,
        content = [Content(
            mediaType = "application/json",
            examples = [
                ExampleObject(
                    name = "기본 예시",
                    value = """
                    {
                      "name": "셀러리 주식회사"
                    }
                    """
                )
            ],
            schema = Schema(implementation = CompanyCreateCommand::class)
        )]
    )
    fun create(@Valid @RequestBody command: CompanyCreateCommand) {
        createUseCase.create(command)
    }
}