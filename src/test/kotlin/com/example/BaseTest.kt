package com.example

import kotlinx.coroutines.test.runTest

abstract class BaseTest : TestCase<TestContext> {
    override fun test(block: suspend TestContext.() -> Unit) = runTest {
        block.invoke(object : TestContext { /* nothing here */ })
    }
}
