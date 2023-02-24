### Rewards-Calculator
This application is a simple rewards program that calculates reward points for customers based on their purchases. The program is built using Java and Spring Boot, and uses a H2 in-memory database to store customer and transaction data.

### Requirements
To build and run this application, you need to have the following software installed on your machine:

- Java 8 or later
- Maven

### Getting Started
To run the application, follow these steps:

- Clone this repository to your local machine.
- Update the application.properties file with the correct database username and password.
- Open a terminal in the root directory of the project and run the command mvn spring-boot:run.
- The application will start, and the REST endpoint will be available at `GET http://localhost:8080`.

**REST API**
The application provides a REST endpoint that allows customers to see their reward points earned and all customer rewards points by months.

The following endpoints are available:

- `GET /rewards`: Get all the customer's name, their transactions and the reward points earned for each customer per month and total.
- `GET /rewards/{id}`: Get the particular customer details, all transaction detail, and the reward points earned by month and total rewards too.

You can simply use GET requests either from postman or browser to see the results.
Example URI:
- http://localhost:8080/rewards --- gets all rewards details for each customer
- http://localhost:8080/rewards/2 --- gets the rewards details for customer ID: 2

### NOTE:
Once you run the application in your local, it will load some dummy data to H2 database. 
The DataLoaderService class will load the random customer, and their random transaction details.

### Testing
To run the JUnit tests, open a terminal in the root directory of the project and run the command mvn test. This will run all the tests and generate a test report.

### Future Improvements
Here are some potential improvements that could be made to the application:

- Improved validation: The input data could be further validated to ensure that it is correct and consistent, which would help to prevent errors and inconsistencies in the data.
-
- More detailed reports: The reward points earned by customers could be displayed in more detail, such as by transaction, date, or product category.
-
- Additional features: The application could be extended to include additional features, such as a loyalty program that offers additional rewards and discounts to frequent customers.
-
- Security: The application could be secured by implementing authentication and authorization features to protect customer data.
-
- Performance optimization: The application could be optimized to improve performance, such as by implementing caching or using a different database technology.

### Conclusion
This application is a simple example of a rewards program that uses Spring Boot and H2 to store customer and transaction data. It provides a REST API that allows customers to see their reward points earned for a three-month period. The application can be extended and modified to suit the needs of different businesses and use cases.