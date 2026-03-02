# Sakila Spring Boot Backend

A RESTful API backend for the Sakila film rental application, built with Java Spring Boot. This is a full migration of the original Python Flask backend to Spring Boot, maintaining all existing API endpoints to ensure seamless compatibility with the React frontend.

## Tech Stack

- **Java 17**
- **Spring Boot** — REST API framework
- **Spring Data JPA / Hibernate** — ORM and database interaction
- **MySQL** — Database (Sakila)
- **Lombok** — Boilerplate reduction
- **Maven** — Dependency management

## Project Structure

```
src/main/java/com/bbllc/sakilaspring/
    model/          # JPA entities representing database tables
    repository/     # Spring Data JPA repositories
    service/        # Business logic layer
    controller/     # REST API endpoints
src/main/resources/
    application.yml # Database and server configuration
```

## Getting Started

### Prerequisites

- Java 17+
- MySQL 8.0+
- Maven

### Setup

1. Clone the repository:

```bash
git clone https://github.com/TheHungryGuy/sakila-spring.git
```

2. Import the Sakila database into MySQL if you haven't already.

3. Configure your database credentials in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sakila
    username: your_username
    password: your_password
```

4. Run the application:

```bash
mvn spring-boot:run
```

The API will start on `http://localhost:5000`

## API Endpoints

### Films

| Method | Endpoint                             | Description                                |
| ------ | ------------------------------------ | ------------------------------------------ |
| GET    | `/all_films`                         | Get all films                              |
| GET    | `/movie_details/{title}`             | Get details for a specific film            |
| GET    | `/top_rented_movies`                 | Get top 5 most rented films                |
| GET    | `/movie_info?movie_id={id}`          | Get inventory info for a film or all films |
| GET    | `/movie_copies_info`                 | Get copy counts per film                   |
| GET    | `/films_by_genre?genre_name={genre}` | Search films by genre                      |
| GET    | `/films_by_actor?actor_name={name}`  | Search films by actor name                 |
| GET    | `/films_by_title?title={title}`      | Search films by title                      |
| GET    | `/remaining_inventory/{film_id}`     | Get available copies for a film            |

### Actors

| Method | Endpoint                           | Description                          |
| ------ | ---------------------------------- | ------------------------------------ |
| GET    | `/top_actors`                      | Get top 5 actors by film count       |
| GET    | `/top_movies_for_actor/{actor_id}` | Get top 5 films for a specific actor |

### Customers

| Method | Endpoint                         | Description                             |
| ------ | -------------------------------- | --------------------------------------- |
| GET    | `/customers`                     | Get all customers with location details |
| GET    | `/check_customer/{customer_id}`  | Check if a customer exists              |
| POST   | `/add_customer`                  | Add a new customer                      |
| PUT    | `/update_customer/{customer_id}` | Update customer details                 |
| DELETE | `/delete_customer/{customer_id}` | Delete a customer                       |

### Rentals

| Method | Endpoint                                   | Description                          |
| ------ | ------------------------------------------ | ------------------------------------ |
| POST   | `/rent_movie/{inventory_id}/{customer_id}` | Rent a film to a customer            |
| GET    | `/customer_rentals/{customer_id}`          | Get rental history for a customer    |
| PUT    | `/update_return_date/{rental_id}`          | Mark a rental as returned            |
| GET    | `/check_movie_availability/{film_id}`      | Check if a film is available to rent |

## Repositories

- **Frontend Repository:** [TheHungryGuy/sakila-frontend](https://github.com/TheHungryGuy/sakila-frontend)
- **Original Flask Backend:** [TheHungryGuy/sakila-backend](https://github.com/TheHungryGuy/sakila-backend)
- **Spring Boot Backend:** [TheHungryGuy/sakila-spring](https://github.com/TheHungryGuy/sakila-spring)

## Migration Notes

This backend is a full port of the original Python Flask + SQLAlchemy backend to Java Spring Boot. All API endpoints are preserved with identical URL structures to maintain full compatibility with the existing React frontend. Key architectural changes include:

- Flask route functions → Spring `@RestController` classes
- SQLAlchemy models → JPA `@Entity` classes with Hibernate
- SQLAlchemy queries → Spring Data JPA repositories with native SQL `@Query` annotations
- Flask `db.session` → Spring `JpaRepository` save/delete methods
