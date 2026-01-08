# BestShoes API
A simple REST API built with Spring Boot for managing an imaginary shoes store called BestShoes.
## Important links
<details>
  <summary>Expand the section to view the ERD: https://drawsql.app/teams/hyperprojects/diagrams/bestshoes-db</summary>
  <img width="4120" height="2760" alt="drawSQL-image-export-2026-01-08" src="https://github.com/user-attachments/assets/cef72760-9ce8-4101-9a67-2b7b9cc8630b" />
</details>
## Stack
- Java 21
- Spring Boot 4.0.0
- PostgreSQL (postgres:15-alpine)
- Git, Docker
## How to run the app
1. Clone the repo: https://github.com/KevinRebakure/BestShoes-API.git
2. With Docker installed and Java environment configured run `docker compose up -d` to spin up the PostgreSQL database
3. Run the app with `mvn spring-boot:run`
4. You can optionally skip running tests with `mvn spring-boot:run -DskipTests`
5. The app will be live at `http://localhost:8080`
