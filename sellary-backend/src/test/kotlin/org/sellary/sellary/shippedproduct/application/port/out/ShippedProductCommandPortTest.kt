package org.sellary.sellary.shippedproduct.application.port.out

import jakarta.persistence.EntityManager
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertInstanceOf
import org.sellary.sellary.shippedproduct.adapter.out.persistence.ShippedProductCommandJPAPort
import org.sellary.sellary.shippedproduct.adapter.out.persistence.ShippedProductJpaRepository
import org.sellary.sellary.shippedproduct.adapter.out.persistence.ShippedProductQueryJPAPort
import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductCostEntity
import org.sellary.sellary.shippedproduct.adapter.out.persistence.entity.ShippedProductEntity
import org.sellary.sellary.shippedproduct.stub.stubShippedProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@Import(value = [ShippedProductCommandJPAPort::class, ShippedProductQueryJPAPort::class])
@DataJpaTest
@EntityScan(basePackageClasses = [ShippedProductEntity::class])
@EnableJpaRepositories(basePackageClasses = [ShippedProductJpaRepository::class])
class ShippedProductCommandPortTest {

    @Autowired
    private lateinit var commandPort: ShippedProductCommandPort

    @Autowired
    private lateinit var queryPort: ShippedProductQueryPort

    @Autowired
    private lateinit var em: EntityManager

    @Test
    @DisplayName("출고상품 저장")
    fun `create new shipped product`() {
        val shippedProduct = stubShippedProduct()

        commandPort.create(shippedProduct)

        val entity = em.createQuery("select sp from ShippedProductEntity sp where sp.code = :code")
            .setParameter("code", shippedProduct.code)
            .singleResult

        assertNotNull(entity)
        val shippedProductEntity = assertInstanceOf<ShippedProductEntity>(entity)
        assertEquals(shippedProduct.name, shippedProductEntity.name)
        assertEquals(shippedProduct.code, shippedProductEntity.code)
        assertEquals(shippedProduct.type, shippedProductEntity.type)
        assertEquals(shippedProduct.barcode, shippedProductEntity.barcode)
        assertEquals(shippedProduct.tags, shippedProductEntity.tagList.map { it.tag }.toSet())
    }

    @Test
    @DisplayName("출고상품 삭제")
    fun `delete shipped product`() {
        val shippedProduct = stubShippedProduct()
        val shippedProductEntity = ShippedProductEntity.fromDomain(shippedProduct)

        em.persist(shippedProductEntity)
        em.flush()

        val id = shippedProductEntity.id
        val costId = shippedProductEntity.shippedProductCost!!.id
        val expIdList = shippedProductEntity.shippedProductExpList.map { it.id }
        val tagIdList = shippedProductEntity.tagList.map { it.id }

        commandPort.delete(id!!)

        val entity = em.find(ShippedProductEntity::class.java, id)
        val costEntity = em.find(ShippedProductCostEntity::class.java, costId)
        val expEntityList =
            em.createQuery("select se from ShippedProductExpEntity se where se.id in :idList")
                .setParameter("idList", expIdList)
                .resultList

        val tagEntityList =
            em.createQuery("select st from ShippedProductTagEntity st where st.id in :idList")
                .setParameter("idList", tagIdList)
                .resultList

        em.flush()
        em.clear()

        assertNull(entity)
        assertNull(costEntity)
        assertTrue { expEntityList.isEmpty() }
        assertTrue { tagEntityList.isEmpty() }
    }

    @Test
    @DisplayName("출고상품 조회")
    fun `read shipped product`() {
        val shippedProduct = stubShippedProduct()
        val shippedProductEntity = ShippedProductEntity.fromDomain(shippedProduct)

        em.persist(shippedProductEntity)
        em.flush()
        em.clear()

        val entity = queryPort.findById(shippedProductEntity.id!!)

        assertNotNull(entity)
        assertEquals(shippedProduct.name, entity.name)
        assertEquals(shippedProduct.code, entity.code)
        assertEquals(shippedProduct.type, entity.type)
        assertEquals(shippedProduct.barcode, entity.barcode)
        assertEquals(shippedProduct.tags, entity.tags)
    }
}