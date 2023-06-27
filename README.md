## Micronaut/Coroutines Test Bug

This repository reproduces [a bug](https://github.com/micronaut-projects/micronaut-core/issues/9514) between
Micronaut v4, Kotlin Coroutines, and test environments.

When running the tests with:
```
./gradlew test
```

You will notice the following test fails:
```
com.example.FailingTest.testSuspendFunction
```

With the following exception:
```
Caused by: java.lang.ClassNotFoundException: kotlin.coroutines.SuspendFunction1
    at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
    at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
    at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:520)
```

This is strange, because the other two tests pass:
```
com.example.NonMicronautWorkingTest.*
com.example.WorkingTest.*
```

### Differences between failing/non-failing tests

The only difference between the failing test and the non-failing tests is that the failing test is annotated with
`@MicronautTest` and processed by Micronaut's KSP processor. Otherwise:

- `NonMicronautWorkingTest`: inherits from `BaseTest`, uses the `TestContext` via the `this.test` method. The `test`
  method accepts a `suspend TestContext.() -> Unit` which executes the test, but with a convenient test API present
  as the receiver context.

- `WorkingTest`: does not inherit from `BaseTest`, uses `runTest` / `runBlocking` directly from Kotlin Coroutines

- `FailingTest`: identical to `NonMicronautWorkingTest`, but does not have `@MicronautTest` annotation
