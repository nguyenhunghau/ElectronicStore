# Electronic Store

## Project Structure

### Modules/Folders

- **src/main/java/com/ElectronicStore**: Contains the main application code.
  - **config**: Configuration classes for the application.
  - **controller**: REST controllers for handling HTTP requests.
  - **dto**: Data Transfer Objects (DTOs) for transferring data between layers.
  - **entity**: JPA entities representing the database tables.
  - **repositories**: Spring Data JPA repositories for data access.
  - **service**: Service classes containing business logic.
- **src/main/resources**: Contains application resources.
  - **static**: Static files such as HTML, CSS, and JavaScript.
  - **templates**: Template files for server-side rendering.
  - **application.properties**: Configuration properties for the application.
- **src/test/java/com/ElectronicStore**: Contains test classes.

## API Endpoints

### Admin Product Management

#### Create a new product

- **URL**: `/admin/products`
- **Method**: `POST`
- **Request Body**: `ProductDTO`
- **Response**: `ProductDTO`

#### Remove a product

- **URL**: `/admin/products/{productId}`
- **Method**: `DELETE`
- **Response**: `void`

#### Apply a discount deal to a product

- **URL**: `/admin/products/{productId}/discount/{discountId}`
- **Method**: `POST`
- **Response**: `ProductDTO`

### Basket Management

#### Add a product to the basket

- **URL**: `/basket`
- **Method**: `POST`
- **Request Body**: `BasketItemDTO`
- **Response**: `BasketDTO`

### Discount Deal Management

#### Create and save a discount deal

- **URL**: `/discount-deals`
- **Method**: `POST`
- **Request Body**: `DiscountDealDTO`
- **Response**: `DiscountDealDTO`

#### Get a discount deal by ID

- **URL**: `/discount-deals/{id}`
- **Method**: `GET`
- **Response**: `DiscountDealDTO`

## How to Use


## Authentication

Before calling any API, you need to log in as a user (customer or admin) to obtain a JWT token. Use the following endpoints to authenticate:

### User Login

#### Login as a customer or admin

- **URL**: `/login`
- **Method**: `POST`
- **Request Body**: `LoginRequest`
- **Response**: `LoginResponse`

#### Login as customer role

```json
{
  "username": "customer",
  "password": "password123"
}
```


#### Login as admin role

```json
{
  "username": "admin",
  "password": "password123"
}
```

Then we can get the token and using for below requests

1. **Create a new product**:
   - Send a `POST` request to `/admin/products` with a `ProductDTO` in the request body.
   - Example:
     ```json
     {
       "name": "New Product",
       "price": 99.99
     }
     ```

2. **Remove a product**:
   - Send a `DELETE` request to `/admin/products/{productId}`.

3. **Apply a discount deal to a product**:
   - Send a `POST` request to `/admin/products/{productId}/discount/{discountId}`.

4. **Add a product to the basket**:
   - Send a `POST` request to `/basket` with a `BasketItemDTO` in the request body.
   - Example:
     ```json
     {
       "productId": 1,
       "quantity": 2
     }
     ```

5. **Create and save a discount deal**:
   - Send a `POST` request to `/discount-deals` with a `DiscountDealDTO` in the request body.
   - Example:
     ```json
     {
       "name": "Holiday Sale",
       "discountPercentage": 20
     }
     ```

6. **Get a discount deal by ID**:
   - Send a `GET` request to `/discount-deals/{id}`.

#### Docker Guide
#### Prerequisites
Docker installed on your machine.
Docker Compose installed on your machine.
Building and Running the Application
Build the Docker image:  
Navigate to the project directory and run the following command:
    
    docker-compose build

Start the application:  
Run the following command to start the application:

    docker-compose up

Access the application:  
The application will be available at http://localhost:8080.

## Dependencies

- Spring Boot
- Spring Data JPA
- Spring Security
- ModelMapper
- H2 Database
- JWT (JSON Web Token)
- Lombok
- Swagger (for API documentation)