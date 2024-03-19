## [JiraRush project](http://localhost:8080/doc)

JiraRush is a task board like Jira or Trello

### Technologies and Frameworks:

- Spring Framework (Boot, Data Jpa, Security, MVC, Test)
- Hibernate
- PostgreSQL
- Liquibase
- Thymeleaf
- jQuery
- Swagger
- Caffeine
- JUnit
- Docker
- Nginx
- Lombock

### Installation
1. Clone the project
2. Execute command 'docker-compose up' in project folder. The network of 3 containers will be created: Postgres, Nginx and application
3. Database will be created and populated automatically
4. A running application is available  at http://localhost:8080
5. Credentials are available at http://localhost:8080/swagger-ui/index.html


### List of completed tasks:
1. Understand the structure of the project
2. Delete social networks: vk, yandex
3. Move sensitive information to a separate property file
4. Make the tests use the H2 database, not Postgres
5. Write tests for all public methods of the ProfileRestController
6. Refactor com.javarush.jira.bugtracking.attachment.FileUtil#upload method so that it uses a modern approach to work with the file system
7. Add new functionality: adding tags to a task (REST API + implementation on the service)
8. Add a count of how long the task was in operation and testing
9. Write a Dockerfile for the application
10. Write a docker-compose file to run the application container along with the database and nginx