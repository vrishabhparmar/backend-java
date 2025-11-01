# Java Spring Boot questions

## What is Spring boot?


## Diff between Spring and Spring Boot?

## Dependency Injection and how it is implemented?

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






