[![Build Status](https://app.travis-ci.com/mtumilowicz/spring-boot-async.svg?branch=master)](https://app.travis-ci.com/mtumilowicz/spring-boot-async)

# spring-boot-async
The main goal of this project is to explore basic features of `@Async` 
spring annotation and compare results with non-concurrent approach.

* references
    * https://spring.io/guides/gs/async-method/  
    * http://www.baeldung.com/spring-async
    * [Concurrency in Spring Boot Applications: Making the Right Choice by Andrei Shakirin](https://www.youtube.com/watch?v=vhHDlSV_0zg)

# preface
This project shows how to create asynchronous queries using Spring.

Our approach is to run expensive jobs in the background and wait 
for the results using Java’s `CompletableFuture` interface.

* spring boot concurrency options
    * java executors and futures
    * completable futures
    * @Async annotation
    * reactive programming
    * virtual threads
    * structured concurrency

# manual
1. Enable asynchronous support:
    ```
    @Configuration
    @EnableAsync
    class AsyncConfig {
        @Bean
        Executor asyncExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(4);
            executor.setMaxPoolSize(4);
            executor.setQueueCapacity(500);
            executor.setThreadNamePrefix("EmailSender-");
            executor.initialize();
            return executor;
        }
    }
    ```
    _Remark_: `asyncExecutor()` is used to customize default behaviour
    and is not mandatory.
1. Annotate method with `@Async`, and set return type to `CompletableFuture<XXX>`
    where `XXX` is a wanted return type, for example:
    ```
    String customMethod() {
    ...
    }
    ```
    should be transformed to:
    ```
    CompletableFuture<String> customMethod() {
    ...
    }    
    ```
1. Consume it with `Completable API`, for example:
    * `CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[]{})).join();`
    * `CompletableFuture.allOf(completableFuture1, completableFuture2).join();`

1. Extract requested return (note that `get()` throws checked exception):
    * `XXX xxx = completableFuture.join()`

# project description
* `email-service` - microservice responsible for sending emails to given
    users
    * `EmailController` - REST controller; receives `login-message` map,
    then asks `slow-user-service` for `emails` and sends messages
    * in `EmailService` we have the same methods:
        * `@Async` method - `asyncSend` - concurrently sends messages
        * non-concurrent method - `send`
    * in `AppRunner` we simulate interactions and compare times
* `slow-user-service`
    * `UserController` returns `User` bean (login, name, email...)
    for given login
    * in `UserRepository` we sleep thread for `user.repository.delay.seconds`
    (configurable in `application.properties`) and then return requested user

# tests
**Coverage**: `93%`
