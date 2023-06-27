package com.example
import io.micronaut.http.HttpRequest
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull

@MicronautTest
class WorkingTest {
    @Inject
    lateinit var application: EmbeddedApplication<*>

    @Inject
    lateinit var controller: TestController

    @Test
    fun testItWorks() {
        Assertions.assertTrue(application.isRunning)
    }

    @Test
    fun testCoroutineRunTest() = runTest {
        assertNotNull(controller.hello(HttpRequest.GET<Any>("/hi")), "should not get null response")
    }

    @Test
    fun testCoroutineRunBlocking() = runBlocking {
        assertNotNull(controller.hello(HttpRequest.GET<Any>("/hi")), "should not get null response")
    }
}
