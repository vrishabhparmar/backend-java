# Microservices 

## What are microservices?

Microservices are an architectural approach to develop  software applications as a collection of small, independent services that
communicates with each other over a network. 

## How do microservices differ from the Monolithic architecture?

Instead of building a monolithic application which tightly couples everything into a single codebase, microservices breaks down
it into small independent services which communicates with each other.

## How does Microservices work?

Each microservice handles a particular business feature like user authentication, product management, allowing for specialized
development. 

Services communicates via APIs, facilitating standardized information exchange and integration.

Different technology can be used for different system, enabling teams to select best tools for their needs.

Microservices can be updated independently, reducing risks during changes and enhancing system resilience

## What are the key features of Microservices?

- Accelerate scalability
- Improve fault tolerance 
- Enhance Team productivity 

- Quicker deployment time: 

- Increased cost-effectively: 
    Microservices architecture optimizes resource allocation and maintenance because teams works on small, well-defined service. 
    Efforts are localized to specific services, reducing overall development and system maintenance cost.


## How do Microservices communicate with each other?

Microservices communicates with each other using various established methods, mainly categorized as synchronous and asynchronous
communication. The choices of the communications style depends on the requirements for speed, reliability, coupling and scalability. 

### Synchronous communication 

Microservices often use synchronous protocol like HTTP or HTTPS, typically through REST-ful APIs. 

In the synchronous communication, one services sends a request and waits for response from the service before proceeding, 
this is common  for queries or immediate data fetches. 

Popular synchronous technologies include REST, gRPC(uses HTTP/2 and protocol buffers for high performance), and GraphQL. 

Pros:
1. Real time communication.
2. Simple to implement and debug

Cons
1. Tight coupling 
2. Can block resources during wait.


### Asynchronous communication 

In asynchronous communication, one service sends a request and does not wait for the response. The main thread delegates that operation 
to message queues or threads. 

Microservices often utilizes message brokers (such as RabbitMQ, Apache Kafka, AWS SQS) or event buses to send and receive
messages without waiting for response.

This method is well suited for tasks which do not require immediate results, such as notifications or background processing.
Communication can be event-driven, where services publish and respond to events that signify state change. 

Pros:
1. Decouples services, increasing reliability and scalability. 
2. Better suited for  distributed, large-scale systems.

Cons:
1. More complex to set up and maintain. 
2. Delivery and ordering guarantees could require additional configuration.

## What are the main components of Microservices?



