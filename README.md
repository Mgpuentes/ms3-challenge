# ms3-challenge

A Spring Boot application that takes a csv file and processes the data. The file "ms3Interview.csv" is included within the project.
A file named "ms3Interview-bad.csv" will be created when the application is executed successfully. Valid records will be stored in a
db file named "ms3Interview".

## Deployment

* Run command:

```
./mvnw spring-boot:run
```


## My approach

My approach to solving the challenge involved planning, research, and use of tools. After reading through the documentation I 
sketched how the project architecture would be designed. The project inculdes a repository, service, and driver layer. After choosing
my technologies that includes Spring Boot, Maven, and more, I decided to use a user interface for the project. After some thinking
and research, I decided to remove the user interface and build an application that is similar to an ETL.

## Design choices

* Having a driver, service and repository layer.

## Assumptions

* The csv file to be proccessed won't be changed.
* Data is persisted to a sqlite database.
