# Java Spring Boot questions

## What is Spring boot?
Spring Boot is an open-source, production-ready Java framework designed to simplify the development of 
enterprise and web applications by reducing boilerplate configuration and providing out-of-the-box features like embedded servers, 
autoconfiguration, and rapid deployment

### Key Features
- `Auto-Configuration` : Spring Boot automatically configures your application settings and selected components (such as Hibernate, JPA, etc.) by scanning the added dependencies, eliminating the need for manual XML configuration
- `Embedded Servers:` :It comes with embedded Tomcat, Jetty, or Undertow servers, letting you create standalone applications that run with a single command and don't require an external server installation
- `Minimal Setup` : You can create a Spring boot project using spring initializer and add required dependencies. Download the zip file and run it. 
- `Microservices-Friendly`:  Designed to be scalable, Spring Boot is ideal for building microservices architectures, making applications modular, maintainable, and suitable for cloud-native deployment
- `Production-Readiness` : Provides built-in monitoring, health checks, and metrics (through Spring Boot Actuator), aiding with application maintenance and diagnostics in production environments
- `Layered Approach` : Typically Spring Boot application follows - Controller (handles HTTP requests), Service (business logic), Repository (data access), Model (domain entities), and Database. This separation maintains code quality and scalability

Request Flow -> User sends an HTTP request → Controller → Service → Repository → Database → Response, following the MVC pattern and adhering to best practices for scalable enterprise apps

## Diff between Spring and Spring Boot?

Spring is the core framework offering all foundational features but with more setup complexity, while Spring Boot builds on Spring, providing a faster and easier way to create Spring applications with minimal configuration and embedded servers for standalone deployment. Spring Boot is best suited for microservices and quick development cycles, whereas Spring is preferred when full control and customization are needed.

## Dependency Injection and how it is implemented?

### Let's consider a scenario where we need to build a Message service

```java
package javaspringboot;

/**
 * Concrete Class which gives email service
 */
class EmailService{

    public void sendMessage(String msg)
    {
        System.out.println("Message was send via Email: " + msg);
    }
}

/**
 * Concrete class which gives sms service
 */
class SmsService{

    public void sendMessage(String msg)
    {
        System.out.println("Message was send via SMS: " + msg);
    }
}

/**
 * If you want to add a new Service we need to create a new concrete class like for facebook
 */
//*** New Class To Add a new Message Service ***//

/*
  This is the consumer class "MyApplication" which is responsible to use services
* */
class MyApplication {

    // Initialize each services if you want to use that service
    // Whenever a new service is added it rejects the Open for extension and close for modification solid principle
    EmailService emailService = new EmailService();
    SmsService smsService = new SmsService();
    // Add new Service like Facebook message

    public void process(String message, String msgService)
    {
        if(msgService.equals("SMS")) smsService.sendMessage(message);
        else if (msgService.equals("EMAIL")) {
            emailService.sendMessage(message);
        }
        // Add new Service like Facebook message
    }
}


class WithOutDependencyInjection{


    public static void main(String[] args) {

        MyApplication application = new MyApplication();

        application.process("Hello from Normal Classs", "EMAIL");
        application.process("Hello from Normal Classs", "SMS");

    }
}


```

In the above example, because dependencies (EmailService, SmsService) are hard-coded inside MyApplication. There’s no flexibility to:
- Pass different implementations at runtime.
- Mock or replace them for testing.
- Add new message services without changing code.
- MyApplication creates instances of EmailService and SmsService inside itself:
- If tomorrow you add a new service (e.g., WhatsAppService or FacebookService),
  you’d have to modify MyApplication — violating the Open/Closed Principle (class should be open for extension but closed for modification).

Now let's write the same class using dependency injection principle.

```java
package javaspringboot;

/**
 * Service Interface
 */
interface MessageService{
    void sendMessage(String msg);
}

class FaceBookService implements MessageService{

    @Override
    public void sendMessage(String msg) {
        System.out.println("Message was send via FaceBook: " + msg);
    }
}

class EmailService2 implements MessageService{

    public void sendMessage(String msg)
    {
        System.out.println("Message was send via Email: " + msg);
    }
}

class SmsService2 implements MessageService{

    public void sendMessage(String msg)
    {
        System.out.println("Message was send via SMS: " + msg);
    }
}

/**
 * Consumer Interface
 */
interface MessageServiceBuilder{
     void process(String message);
     void setMessageService(MessageService messageService);
}

class MyApplication2 implements MessageServiceBuilder{

    MessageService messageService;

    public MyApplication2(){}

    public MyApplication2(MessageService messageService)
    {
        this.messageService = messageService;
    }

    public void process(String message)
    {
        messageService.sendMessage(message);
    }

    // Setter based injection
    @Override
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}

/**
 * Injector Interface
 */
interface MessageServiceInjector{
    MessageService createService();
}

class FacebookInjectorClass implements MessageServiceInjector{

    @Override
    public MessageService createService() {
        return new FaceBookService();
    }
}

class MyEmailServiceInjector implements MessageServiceInjector{
    @Override
    public MessageService createService() {
        return new EmailService2();
    }
}

class SmsServiceInjector implements MessageServiceInjector{
    @Override
    public MessageService createService()
    {
        return new SmsService2();
    }
}

public class DependencyInjectionDemo{

    public static void main(String[] args) {

        // Send Email
        // Injector Creates a Service which you need
        MessageServiceInjector messageServiceInjector = new MyEmailServiceInjector();

        // Injector creates the required service
        MessageService service = messageServiceInjector.createService();

        // Consumer uses the service
        MyApplication2 myApplication2 = new MyApplication2(service);
        myApplication2.process("Hello");

        // Send SMS
        MessageServiceInjector messageServiceInjector2 = new SmsServiceInjector();
        MessageService service2 = messageServiceInjector2.createService();
        MyApplication2 myApplication21 = new MyApplication2();
        myApplication21.setMessageService(service2); // Setter based injection
        myApplication21.process("Hello");

        // Send Message via Facebook
        MessageServiceInjector facebookInjectorClass = new FacebookInjectorClass();
        MessageService facebookService = facebookInjectorClass.createService();
        MyApplication2 consumer = new MyApplication2(facebookService);
        consumer.process("Hello");

    }
}

```
The above code is divided into three layers - Service, Consumer and Injector. Since we need to
maintain the loose coupling we will be using interface to declare each method

The service is responsible to declare different services which we will be using like Email, Sms.

The Consumer will be using that services to perform operation 

The Injector is responsible to create instances of the different services which will be injected. 

In the above code we can add new Services without modifying any other code. Hence, follows the Open Close Principle. The code also follows the single responsibility principle.


Dependency Injection (DI) is a software design pattern where an object or class receives ("is injected with") other objects it depends on (its dependencies) from an external source rather than creating them internally. This approach promotes loose coupling, making code more modular, flexible, testable, and maintainable.

### Implementation 

1. `Constructor Injection`: Dependencies are provided through the class constructor. Email is using Constructor based injection
2. `Setter Injection`: Dependencies are provided through the setter methods. SMS is using setter based injection 
3. `Field Injection`: Dependencies are provided through the fields (Not used that often).


## All keywords in Java:

1. Access Modifier

| Keyword                  | Use                                                               |
| ------------------------ | ----------------------------------------------------------------- |
| `public`                 | Accessible from anywhere.                                         |
| `private`                | Accessible only within the same class.                            |
| `protected`              | Accessible within the same package and by subclasses.             |
| `default` *(no keyword)* | If no modifier is specified — accessible within the same package. |

2. Class, Objects & Interface related

| Keyword      | Use                                                                    |
| ------------ | ---------------------------------------------------------------------- |
| `class`      | Defines a class.                                                       |
| `interface`  | Defines an interface (a contract of methods).                          |
| `abstract`   | Defines an abstract class or method (cannot be directly instantiated). |
| `extends`    | Used for inheritance between classes.                                  |
| `implements` | Used when a class implements an interface.                             |
| `this`       | Refers to the current object.                                          |
| `super`      | Refers to the parent (superclass) object.                              |
| `new`        | Creates a new object.                                                  |
| `enum`       | Defines a set of named constants.                                      |

3. Data Types & Variable Handling

| Keyword            | Use                                                                  |
| ------------------ | -------------------------------------------------------------------- |
| `int`              | Integer type (4 bytes).                                              |
| `long`             | Long integer type (8 bytes).                                         |
| `float`            | Floating-point type (4 bytes).                                       |
| `double`           | Double-precision floating-point (8 bytes).                           |
| `byte`             | 1-byte integer type.                                                 |
| `short`            | 2-byte integer type.                                                 |
| `char`             | Single 16-bit Unicode character.                                     |
| `boolean`          | True or false values.                                                |
| `void`             | No return value (method returns nothing).                            |
| `var` *(Java 10+)* | Local variable type inference.                                       |
| `static`           | Belongs to the class, not to an instance.                            |
| `final`            | Makes variables constant, prevents method overriding or inheritance. |

4. Inheritance, Polymorphism, and Object Features

| Keyword        | Use                                                                                                       |
| -------------- |-----------------------------------------------------------------------------------------------------------|
| `instanceof`   | Tests whether an object is an instance of a class.                                                        |
| `native`       | Specifies that a method is written in native code (e.g., C/C++).                                          |
| `synchronized` | Prevents concurrent access to a method/block by multiple threads.                                         |
| `transient`    | Prevents serialization of a field.                                                                        |
| `volatile`     | Tells JVM a variable may be changed by multiple threads. Guarantees visibility of changes across threads. |


## Situation where constructor injection is preferred over setter?

## @SpringBootApplication, Explain the internals?

## Spring Profiler 

## Database Connection in Spring Boot?

## Singleton Pattern? Where it is used?

## @Transaction

## If we mention @Transaction at both class level and method level. What issues will arise?

## How to handle a scenario where a transaction needs to be rolled back due to operation on Remote service failure?

## How to implement Notification system? What us Java Email?

## How do you perform deployment ?

## How does Spring Boot simplifies Web development?

## Servers in Spring Boot?

## How to replace default server in Spring Boot?

## Database Migration in Spring Boot?

## Spring Boot Actuators ?

## JPA (Java Persistent API) vs Spring Boot Data JPA

## If you want to dynamically fetch data with variable search criteria. How will you implement it using Spring Data?

## How to write custom queries in Spring Boot?

## Repository in Spring Data JPA and its limitations?

## How do you handle transactions in Spring Data JPA?

## If you have long batch operations then how can you manage transactions to optimize performance and consistency 

## Diff between == vs .equals()

## How would you handle a situation where you need to compare the content equality of two custom Object instance?

## Java Memory Model?

## Final Keywords?

## HashMaps working

## If you are storing users sessions data in an HashMap how will you make sure thread safety?

## Exception Handling (Checked and Unchecked)

## How would you handle a scenario where a method throws multiple types of exception 

## JUnit

## Maven

## Maven clean vs Maven install

## With Git commands how to resolve a conflict 

## Git rebase vs Git merger

## Git cherry pick 

## Best Practice while writing a REST API

## Lambda expression and its benefits

## This keyword

## Functional Interface (Predicate, Consumer, Functional)

## Can you add another method to a Functional Interface?

## Options class in Java 8 and what problems does it solve?

## Java 8 features

## If two interface provide some method and your class implements them. How do you resolve the conflict

## Map vs flatMap

## Java time API 

## What makes a class immutable 

## If a class contains date then how do you insure immutability 

## ENUM and its benefits

## Can ENUM implement interface?

## Lets say you are working with a payment system then how will you make use of ENUM?

## SOLID principles 

## Why do we need design pattern 

## @RequestMapping vs @Get vs @Post

## @Valid vs @Validated

## @ControllerAdvice

## Diff between returning @ResponseEntity vs Returning n Object?

## @Autowired internal working

## Top 5 annotation

## Least Frequently used annotation 

## Project Lombok

## Agile vs Waterfall 

## Spring Boot version specific questions

## What Spring boot version you are using?

## SpringBoot3 

## Spring Boot Tutorial

### Building a REST-full Webservice

## ORM (Object relational Model)

      ORM is a technique used to map Java Objects to database tables; It allows developers to work with databases using OOPS
      concepts, making it easier to interact with relatinal databases. 
      ORM frameworks like Hibernate can map the fields in the User class to coulumns in the user table. making it 
      easies for CRUD operations

## JPA (Java Persistence API)

      A way to acheive ORM, includes interfaces and annotations that you use in your Java classes, requires a persistance provider
      (ORM tools) for implementation 
      To use JPA, you need a persistance provider. A persistence provider is a specific implementation of the JPA implementation.
      Example of JPA persistence provider includes Hibernate, EclipseLink, and OpenJPA. 
      These providers implement the JPA interface and provide the underlying functionality to interact with the databases.

## Spring Data JPA

      Spring Data JPA is a built on top of the JPA specification, but not JPA implentation itself. Instead it simplefies working with JPA
      by providing higher level abstraction and utilities. However, to use Spring Data JPA effectively,  you still need a JPA implementation
      , such as Hibernate, EclipseLink, or another JPA-compliant provider, to handle the actual database interations. 

      In case of MongoDB, you don't have traditional JPA persistence provider. MongoDB is a NoSql database., and Spring Data MongoDB 
      serves as a "persistance provider" for MongoDB
      
      It provides the necessary abstrctions and implementations to work with the MongoDB in a Spring Application.

Query Method DSL and Criteria API are two different ways to interact with the database when using Spring Data JPA for relational databases
and Spring Data Mongo DB for MongoDB databases.

**Query Method DSL** is a simple and convenient way to create queries based on method naming conventions, while the **Criteria API** offers a more dynamic and programmatic
approach for building complex and custom queries

## Connecting MongoDB using Spring Data MongoDB

## Status codes

## Project Lombok

## MongoDB Relationships in Spring Boot

## How to make a class immutable

Steps to make a class immutable 
1. Mark the class as final - so no subclass can modify its behavior 
2. Make all fields private and final - so all values can be assigned only once
3. Initialize all fields via constructor - No setters allowed.
4. Do not provide any setters - So state can’t change after creation.
5. Return deep copies of mutable objects (if any) - If a field is mutable (like a Date or a List), return a new copy in the getter.

```java
import java.util.Date;

public final class Employee {
    private final String name;
    private final Date joiningDate; // Mutable field

    public Employee(String name, Date joiningDate) {
        this.name = name;
        // Create a copy to prevent external modification
        this.joiningDate = new Date(joiningDate.getTime());
    }

    public String getName() {
        return name;
    }

    public Date getJoiningDate() {
        // Return a copy, not the original object
        return new Date(joiningDate.getTime());
    }
}

```







