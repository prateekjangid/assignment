-1st service- Profile  
-2nd Service- Assignment service  
-Features used:-Spring Security,zull api gateway,Kafka,H2 db,REST controllers.  

1. Start the "Profile" service first since it has the db attached(as mentioned only 2 services can be created) for both services.
    - default port 8085.
2. Assignment microservice can be run at 9090 port with h2 db directing toward Profile service. H2 db is attached at localhost:8085/h2-console.
3. Kafka server is running at default port(9092 nd 2181) with one broker and one partition.
4. Test cases has been added for each.(Profile services is required as db is attached with the service.)
5. urls can be checked via postman:
    a). login url:-http://localhost:9090/assignment/  -post method  
        -Body:
        {
            "username":"testuser",
            "password":"1234"
        }
        it will fetch the user info.
    
    b). http://localhost:9090/assignment/save    -post method  
       -Body:
       {
            "userName":"prateek",
            "password":"1234",
            "address":"abcd",
            "phone":"9123729312"
        }
        -It will create new user.

    c). http://localhost:9090/assignment/delete-   -delete method  
        -purge the current user.

    d).http://localhost:9090/assignment/update-   -put method  
       -Body:
       {
            "address":"abcd",
            "phone":"9123729312"
        }
        -Update the details.

6. Delete and put method will be done via kafka async.
7. Required port:8085(1st service),9090(2nd service),9091(h2-db),2181(zookeper),9092  (kafka server).
8. Profile service port is to be changed in assignment service too. Similarly,H2-db port is also need to be changed in 2nd service.
9. Kafka and zookeper info is to be changed in application.yml of respective project.
  
