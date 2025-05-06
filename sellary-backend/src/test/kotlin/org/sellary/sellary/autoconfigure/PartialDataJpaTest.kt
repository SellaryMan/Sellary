package org.sellary.sellary.autoconfigure

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.core.annotation.AliasFor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.TestPropertySource
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@Inherited
@Target(allowedTargets = [(AnnotationTarget.CLASS)])
@Retention(AnnotationRetention.RUNTIME)
@DataJpaTest(properties = ["spring.jpa.hibernate.ddl-auto=create"])
@TestPropertySource(properties = ["spring.jpa.hibernate.ddl-auto=create-drop"])
@EntityScan
@EnableJpaRepositories
annotation class PartialDataJpaTest(
    @get:AliasFor(annotation = EntityScan::class, attribute = "basePackageClasses")
    val entityPackageClasses: Array<KClass<*>> = [],

    @get:AliasFor(annotation = EnableJpaRepositories::class, attribute = "basePackageClasses")
    val repositoryPackageClasses: Array<KClass<*>> = []
)