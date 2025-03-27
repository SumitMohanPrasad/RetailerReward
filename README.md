# Retailer Reward Program

This is a Spring Boot REST API that calculates reward points for a retailer's loyalty program. Customers earn reward points based on their purchases:
2 points for every $1 spent over $100.
1 point for every $1 spent between $50 and $100.

Example: A $120 purchase earns 90 points:
(120 - 100) * 2 + (100 - 50) * 1 = 40 + 50 = 90 points

# Features
- REST API for calculating customer reward points
- Stores customer transactions in a Mysql database
- Handles exceptions properly

# Tech Stack  
- Java 21
- Spring Boot 3.5.0
- JUnit & Mockito (for testing)
- Mysql Database 

| Method | Endpoint          | Description                        |
|--------|------------------|------------------------------------|
| GET    | /reward/{customerId}     | Get reward points for a customer  |
| GET    | /customer       | Get all customers                 |
| GET    | /customer/{customerId}  | Get customer by ID                |
| POST   | /customer       | Add a new customer                |
| DELETE | /customer/{customerId}  | Delete a customer                 |



# Access the API
Open Postman or Browser
Test API:  http://localhost:8081/reward/1
