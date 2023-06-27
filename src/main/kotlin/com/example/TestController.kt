package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/hi") class TestController {
    @Get suspend fun hello(request: HttpRequest<*>): HttpResponse<*> = HttpResponse.ok(
        "Hello"
    )
}
