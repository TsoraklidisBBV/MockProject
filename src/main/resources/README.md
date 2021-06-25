**Ways to run the Code:** 
mvn spring-boot:run

**URLS to be used in POSTMAN:**
localhost:8080/evaluation?url=http%3A%2F%2Flocalhost%3A8080%2Freport%2FMockData
localhost:8080/evaluation?url=http%3A%2F%2Flocalhost%3A8080%2Freport%2FMockData2

**URL with both CSV files:**
localhost:8080/evaluation?url=http%3A%2F%2Flocalhost%3A8080%2Freport%2FMockData&url=http%3A%2F%2Flocalhost%3A8080%2Freport%2FMockData2

**Things to be expanded:** 
The RestApi Controllers can have better Exeption handling by using ControllerAdvice.
The tests can be expanded to include Integration tests for the Controller by the use of Wiremocks. 
If the project uses CI/CD techniques, Docker, Kubernetes and other CI/CD pipeline elements can be added to the .pom

**About CsvMockController:** 
For the call of an external URL, it was decided to use a secondary controller in order to keep the Example contained. 