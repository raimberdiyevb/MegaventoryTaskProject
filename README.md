## Megaventory Task
![Megaventory Application Screenshot](https://drive.google.com/uc?id=1VsrsFlbXQYSEo6GAyi-iTLa2-EicZaUh)
![Megaventory Application Screenshot](https://drive.google.com/uc?id=1W3rwV4zHwNhnZTWkf2DKoE_brD2xXvoI)
## Project Structure

The project structure is organized as follows:

- `src/main/java/com/example/TaskProject`: Contains the Java source code for the application.
  - `entity`: Contains entity classes representing different objects in the application domain.
  - `repo`: Contains repository classes responsible for data access.
  - `service`: Contains service classes that encapsulate business logic.
  - `controller`: Contains REST controllers for handling HTTP requests.
- `TaskProjectApplication.java`: Main class to run the Spring Boot application.
- `src/main/resources`: Contains application properties and configurations.
- `pom.xml`: Maven configuration file specifying project dependencies.

## Functionality

### Products:
- **GET** `/api/v1/raim/megaventory/products`: Retrieve all products.
- **GET** `/api/v1/raim/megaventory/products/{productId}`: Retrieve a specific product by ID.
- **POST** `/api/v1/raim/megaventory/products`: Add a new product.

### Clients:
- **GET** `/api/v1/raim/megaventory/clients`: Retrieve all clients.
- **POST** `/api/v1/raim/megaventory/clients`: Add a new client.

### Locations:
- **GET** `/api/v1/raim/megaventory/locations`: Retrieve all inventory locations.
- **POST** `/api/v1/raim/megaventory/locations`: Add a new inventory location.

## Technologies Used

- Java
- Spring Boot
- Maven
- RESTful API
- Megaventory API

## OOP Principles and Best Practices

- **Dependency Injection**: The project uses Spring's dependency injection to inject repository implementations into the service layer.
- **Single Responsibility Principle (SRP)**: Classes and methods are designed to have a single responsibility. For example, the `ApplicationService` class is responsible for handling business logic related to products, supplier clients, and inventory locations.
- **Open/Closed Principle (OCP)**: The project follows the OCP by allowing for easy extension through interfaces and abstractions. New functionality can be added by implementing the `BaseRepo` interface without modifying existing code.
- **Interface Segregation Principle (ISP)**: The `BaseRepo` interface segregates the methods `getAll()` and `add(T entity)` for common repository operations.
- **Dependency Inversion Principle (DIP)**: The project adheres to DIP by depending on abstractions rather than concrete implementations. Repository interfaces (`BaseRepo`) are used in the service layer, promoting loose coupling.
- **Encapsulation**: Data hiding and encapsulation are practiced throughout the project. Data access and manipulation are encapsulated within repository and service classes, ensuring modularity and encapsulation of behavior.
- **Don't Repeat Yourself (DRY)**: The project avoids code duplication by extracting common functionality into reusable components. For example, HTTP request handling and JSON parsing logic are encapsulated within repository implementations.
