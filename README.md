# Reference Documentation
* ###### how to test accounts-service

1. ./docker-compose-up.sh 
2. mvn flyway:migrate -Dflyway.url=jdbc:postgresql://localhost:5432/account_service -Dflyway.user=account-service -Dflyway.password=account-s3rv1c3
3. Execute curl's commands listed below. 
    
* ###### how to run intetration-test accounts-service

1. ./docker-compose-only-db-up.sh
2. mvn flyway:migrate -Dflyway.url=jdbc:postgresql://localhost:5432/account_service -Dflyway.user=account-service -Dflyway.password=account-s3rv1c3
3. 
### Endpoints

|Method | 	Url		| 	Description |
|-------| ------- | ----------- |
|POST| 	/accounts	| 	create an account with these required parameters. <br/>Ex request body: <br/> <br/> {  <br/> document_number: '12345678900' , <br/> mobile: '11985733234', <br/> email: 'john@gmail.com' <br/> } |
|GET|   /accounts/{id}| 	get account by id|
|POST| /transactions |	create a transaction with these required parameters. <br/>Ex request body: <br/> <br/> {  <br/> account_id: 1 , <br/> operation_type_id: 4, <br/> amount: 123.45 <br/> } |

### Curl comands

1- Create Account
curl -d '{ "document_number": "12345678900" , "mobile": "11985733234", "email": "john@gmail.com" }' -H 'Content-Type: application/json' http://localhost:8300/accounts-service/accounts


2- Get Account By Id
curl -v http://localhost:8300/accounts-service/accounts/5


3- Create Transaction
curl -d '{ "account_id": 5 , "operation_type_id": 4, "amount": 123.45 }' -H 'Content-Type: application/json' http://localhost:8300/accounts-service/transactions



### Docker - generate new image

mvn spring-boot:build-image -DskipTests    

### Comandos para migração de dados com Flyway

<pre>
mvn flyway:migrate -Dflyway.url=jdbc:postgresql://localhost:5432/account_service -Dflyway.user=account-service -Dflyway.password=account-s3rv1c3
</pre>

    1) Primeira vez rode:
        mvn compile flyway:migrate
    
    2) Para as proximas vezes rode    
        mvn clean flyway:migrate

### hot swap dev environment for intellij


<pre>
    A solution that uses devTools works :
    
    1 - Adding devtools to your project
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
    2- Enabling automatic build
    Open the Settings --> Build-Execution-Deployment --> Compiler and enable :
    
    Build Project Automatically.
    3- Update the value of compiler.automake.allow.when.app.running
    press ctrl+shift+A and search for the registry. In the registry, enable :
    
    compiler.automake.allow.when.app.running
 </pre>   
