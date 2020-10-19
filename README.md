# makar-centene-hackathon-backend

Hi!

I was tasked with developing a microservice for tracking the status of enrollees in a health care program. 

All enrollees must have an ID, name and activation status and I must be able to perform CRUD operations on all my enrollees. 

Additionally, enrollees may have dependents, and we must be able to to perform CRUD operations on them as well. 

This backend is fully functionaly by itself, all methods may be tested through Postman with proper exception handling meant to give feedback to the client when facing errors interacting with the MySQL database. However, you may find the front-end that compliments this project here: https://github.com/MakarTch/centene-hackathon-frontend. 

![alt text](https://scontent-lga3-2.xx.fbcdn.net/v/t1.15752-9/122048383_270431597569728_5160876005295887471_n.png?_nc_cat=105&_nc_sid=ae9488&_nc_ohc=D7TBb2VcjjsAX8rd309&_nc_ht=scontent-lga3-2.xx&oh=adb1562d7273b67e543dea760ad26699&oe=5FB26117)

You can find all methods in the controller of my Spring Boot application, all documented using Swagger. Once you run the app you may visit http://localhost:8080/swagger-ui.html#/ to view documentation on the controller methods and models used in the application. 

