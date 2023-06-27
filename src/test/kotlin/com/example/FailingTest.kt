package com.example
import io.micronaut.http.HttpRequest
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertNotNull

@MicronautTest
class FailingTest : BaseTest() {
    @Inject
    lateinit var application: EmbeddedApplication<*>

    @Inject
    lateinit var controller: TestController

    @Test
    fun testItWorks() {
        Assertions.assertTrue(application.isRunning)
    }

    @Test
    fun testSuspendFunction() = test {
        assertNotNull(controller.hello(HttpRequest.GET<Any>("/hi")), "should not get null response")
    }
}
