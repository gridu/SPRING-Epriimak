# Phonebook

The application provides ability to create phonebook. \
Each phonebook record contains person's name and set of his phone numbers (set used to avoid duplicates).  

## Functionality
1. Create new phonebook record
2. Add phone number for existed person's record
3. Delete phonebook record
4. Get list of phonebook records

## How to run
#### Using Intellij IDEA SpringBoot configuration
1. Clone project
2. Create "SpringBoot configuration" and add *com.griddynamics.gridu.springta.phonebook.PhoneBookApplication* as Main class
3. Run created configuration

#### Using Intellij IDEA Tomcat configuration
1. Clone project
2. Open IntelliJ IDEA and "Project structure" section. In artifact section create "Web Application: exploded" artifact from *phoneBookREST* module 
3. Create Tomcat project configuration: 
    * in "Server" section add created artifact in "Before launch"
    * in "Development" section add same artifact and make sure Application context contains */phoneBookREST*
4. Run tuned configuration
5. Server starts, welcome message will be on *http://localhost:8080/phoneBookREST/v1/customers*

#### Using Tomcat server
1. Clone project
2. Run ```mvn clean install``` \
File *phoneBookREST.war* will be in *target* directory
3. Go to Tomcat installed directory and start server: \
```cd bin``` \
```./startup.sh``` \
Make sure you have credentials to manage apps \
If not, add it in *conf/tomcat-users.xml*
4. Open browser \
Tomcat server will be in *localhost:8080*
5. Deploy .war file 
    * go to *http://localhost:8080/manager*
    * in "Deploy" section select created .war file
    * click "Deploy" button
    * application is ready to use
6. Shutdown Tomcat server after running application \
```./shutdown.sh```

## How to use
Make sure server is running on *localhost:8080*
1. To create new record send POST request on *http://localhost:8080/phoneBookREST/v1/customers/addRecord* \
using JSON body format: *{
                         	"name":"Your name",
                         	"phones":"Your phone"
                         }*.
2. To add phone number for person send PUT request on *http://localhost:8080/phoneBookREST/v1/customers/updateRecordByName/PERSON_NAME?phone=PHONE_NUMBER* \
where *phone* is query parameter.
3. To delete record by specific name send DELETE request on *http://localhost:8080/phoneBookREST/v1/customers/deleteRecordByName/PERSON_NAME* \
If record with such name not exists exception will be thrown
4. To get all records send GET request on *http://localhost:8080/phoneBookREST/v1/customers/getAllRecords* \
To get record by specific name send GET request on *http://localhost:8080/phoneBookREST/v1/customers/getRecordByName/PERSON_NAME*

#### Swagger
1. Run application
2. Swagger will be on *http://localhost:8080/phoneBookREST/swagger-ui.html*


## Running test
Application contains some service tests. 
For test run use this command: 
```mvn clean test```
