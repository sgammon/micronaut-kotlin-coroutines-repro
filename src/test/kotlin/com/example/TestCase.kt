package com.example

interface TestCase<Context> where Context : TestContext {
    fun test(block: suspend Context.() -> Unit)
}
