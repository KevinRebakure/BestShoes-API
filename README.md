# BestShoes API
A simple REST API built with Spring Boot for managing an imaginary shoes store called BestShoes.
## Important links
- ERD: https://drawsql.app/teams/hyperprojects/diagrams/bestshoes-db
## Stack
- Java 21
- Spring Boot 4.0.0
- PostgreSQL (postgres:15-alpine)
- Docker
## How to run the app
1. Clone the repo: https://github.com/KevinRebakure/BestShoes-API.git
2. With Docker installed and Java environment configured run `docker compose up -d` to spin up the PostgreSQL database
3. Run the app with `mvn spring-boot:run`
4. You can optionally skip running tests with `mvn spring-boot:run -DskipTests`
5. The app will be live at `http://localhost:8080`