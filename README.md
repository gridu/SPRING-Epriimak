#Phonebook

The application provides ability to create phonebook. \
Each phonebook record contains person's name and set of his phone numbers (set used to avoid duplicates).  

##Functionality
1. Create new phonebook record
2. Add phone number for existed person's record
3. Delete phonebook record
4. Get list of phonebook records

##How to run
1. Clone project
2. Open IntelliJ IDEA and "Project structure" section. In artifact section create "Web Application: exploded" artifact from *phoneBookREST* module 
3. Create Tomcat project configuration: 
    * in "Server" section add created artifact in "Before launch"
    * in "Development" section add same artifact and make sure Application context contains */phoneBookREST*
4. Run tuned configuration
5. Server starts, welcome message will be on *http://localhost:8080/phoneBookREST/v1/customers*

##How to use
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

##Running test
Application contains some service tests. 
For test run use this command: 
```mvn clean test```