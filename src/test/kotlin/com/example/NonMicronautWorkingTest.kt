package com.example
import io.micronaut.http.HttpRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertNotNull

class NonMicronautWorkingTest : BaseTest() {
    private val controller: TestController = TestController()

    @Test
    fun testSuspendFunction() = test {
        assertNotNull(controller.hello(HttpRequest.GET<Any>("/hi")), "should not get null response")
    }
}
