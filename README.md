# Microservice oriented blog application
This application uses small REST services (microservices) together with a front-end written in Angular 2. The goal of this application is to create a simple blog application using microservices.

## Microservices
The application contains the following microservices.

### blog-service
This is the microservice providing all articles that should be visible on the blog application.

### config-service
This microservice provides application configuration to all the other microservices. Information like port numbers, context paths, ... can be found here.
The configuration is also served using Git, more precisely in [this separate repository](https://github.com/g00glen00b/microservice-demo-config).

### discovery-service
To be able to run multiple instances of th same microservice, or to be able to "abstract" port numbers we use a discovery service ([Eureka](https://github.com/Netflix/eureka)).
Every microservice registers itself on this discovery service and when another microservice needs to call it, they look up the hostname and port number by checking the discovery service.
This means that we no longer have to hardcode all ports, but only one (the discovery service itself).

### gateway-service
On the front-end it's a lot more difficult to use Eureka, even though there are some ports available already.
To solve this, we use a gateway service ([Zuul](https://github.com/Netflix/zuul)).
This microservice will proxy all calls to the target microservice. We also apply cross-cutting concerns here. For example, to solve CORS requests we enabled CORS headers on the proxy only.
Zuul easily integrates with Eureka (discovery-service), so that makes it really easy to configure.

### profile-service
Articles are written by users. Users have information like their bio, firstname, lastname, ... . All of this is provided by the profile service.
The profile-service is, next to the blog-service the only microservice that "really delivers content".

### uaa-service
The final microservice we will be using is the uaa-service. The letters UAA stand for User Account & Authentication, so basically this microservice handles the security of the application.
It has two main endpoints, a `/token` endpoint to retrieve a token and a `/user` endpoint to validate a token and retrieve the user and its roles. The tokens in this case are long-lived, which may not be the best practice, but it makes it easy to set up.
All microservices only allow POST, PUT, DELETE calls to be executed if a token is provided as a header. GET calls are permitted for anonymous calls.

### ???
Basically this set up allows you to create more microservices. Possible expansions are a tag service, a media service to provide images, ... .
