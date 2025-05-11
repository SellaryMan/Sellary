package org.sellary.sellary.shippedproduct.stub

import org.sellary.sellary.shippedproduct.application.domain.ShippedProduct
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductCost
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductExp
import org.sellary.sellary.shippedproduct.application.domain.ShippedProductType
import java.time.LocalDateTime

object ShippedProductStubFactory {
    fun createShippedProduct(
        id: Long? = 1L,
        name: String = "테스트 상품",
        type: ShippedProductType = ShippedProductType.PRODUCT,
        code: String = "PROD-TEST-001",
        barcode: String = "1234567890123",
        tags: Set<String> = setOf("테스트", "상품"),
        shippedProductExp: List<ShippedProductExp> = listOf(createShippedProductExp()),
        shippedProductCost: ShippedProductCost = createShippedProductCost()
    ) = ShippedProduct(
        id = id,
        name = name,
        type = type,
        code = code,
        barcode = barcode,
        tags = tags,
        shippedProductExp = shippedProductExp,
        shippedProductCost = shippedProductCost
    )

    fun createShippedProductExp(
        id: Long? = 1L,
        expDate: LocalDateTime = LocalDateTime.now().plusMonths(6),
        quantity: Long = 100L,
        manufactureDate: LocalDateTime = LocalDateTime.now(),
        lowStockThresholdDay: Long = 30L,
        noShippingThresholdDay: Long = 7L
    ) = ShippedProductExp(
        id = id,
        expDate = expDate,
        quantity = quantity,
        manufactureDate = manufactureDate,
        lowStockThresholdDay = lowStockThresholdDay,
        noShippingThresholdDay = noShippingThresholdDay
    )

    fun createShippedProductCost(
        id: Long? = 1L,
        unitPurchasePrice: Double = 1000.0,
        boxPurchasePrice: Double = 10000.0,
        palletPurchasePrice: Double = 100000.0,
        unitSellingPrice: Double = 1500.0,
        boxSellingPrice: Double = 15000.0,
        palletSellingPrice: Double = 150000.0
    ) = ShippedProductCost(
        id = id,
        unitPurchasePrice = unitPurchasePrice,
        boxPurchasePrice = boxPurchasePrice,
        palletPurchasePrice = palletPurchasePrice,
        unitSellingPrice = unitSellingPrice,
        boxSellingPrice = boxSellingPrice,
        palletSellingPrice = palletSellingPrice
    )

    fun createIngredientProduct() = createShippedProduct(
        name = "재료 상품",
        type = ShippedProductType.INGREDIENT,
        code = "ING-TEST-001"
    )

    fun createSubIngredientProduct() = createShippedProduct(
        name = "보조 재료 상품",
        type = ShippedProductType.SUB_INGREDIENT,
        code = "SUB-TEST-001"
    )

    fun createExpiredProduct() = createShippedProduct(
        name = "유통기한 만료 상품",
        shippedProductExp = listOf(
            createShippedProductExp(
                expDate = LocalDateTime.now().minusDays(1)
            )
        )
    )

    fun createLowStockProduct() = createShippedProduct(
        name = "재고 부족 상품",
        shippedProductExp = listOf(
            createShippedProductExp(
                quantity = 5L
            )
        )
    )

    fun createHighProfitProduct() = createShippedProduct(
        name = "고수익 상품",
        shippedProductCost = createShippedProductCost(
            unitPurchasePrice = 1000.0,
            unitSellingPrice = 3000.0
        )
    )
}

fun stubShippedProduct() = ShippedProductStubFactory.createShippedProduct()
fun stubShippedProductExp() = ShippedProductStubFactory.createShippedProductExp()
fun stubShippedProductCost() = ShippedProductStubFactory.createShippedProductCost()
