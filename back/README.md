# BACK : MDD APPLICATION

This backend project was generated using [Spring Initializr](https://start.spring.io/).
It uses Spring Boot for application configuration and management, Maven for dependency management, and Java 21 as the runtime environment.

## Project Description

This MVP application allows users to subscribe to programming-related topics (such as JavaScript, Java, Python, Web3, etc.).  
The news feed displays relevant articles in chronological order. Users can also write articles and post comments.

### Project Goals

The goal of this project is to develop a **Minimum Viable Product (MVP)**.  
The backend is built with **Spring Boot**, and the frontend is developed using **Angular**.

As this is an MVP, automated tests have not been implemented yet. Testing will be included in future iterations.

## Installation

> Note: The path back is relative to the root of the project (where you cloned the repo), not your current directory.

Before running the project, make sur your environment meets the following requirements:

### Prerequisites :

- [Java](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) version 21
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)
- [Node.js](https://nodejs.org/en) version 22

### Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/danchaud-vincent/oc_project6_mdd.git
```

**2. Create MySQL Database**

```bash
create database DB_NAME;
```

**3. Setup application.properties**

- Open `back/src/main/resources/application.properties`
- Change the following environment variables by your information:
  - spring.datasource.url=jdbc:mysql://`${DB_HOST}`:`${DB_PORT}`/`${DB_NAME}`
  - spring.datasource.username=`${DB_USERNAME}`
  - spring.datasource.password=`${DB_PASSWORD}`
  - oc.app.jwtSecret=`${JwtKey}`

or set the env variables in your console or in a run.bat file before running mvn :

**using bash:**

```bash
export DB_HOST=your_db_host
export DB_PORT=your_db_port
export DB_NAME=your_db_name
export DB_USERNAME=your_database_username
export DB_PASSWORD=your_database_password
export JwtKey=your_jwtKey
```

**using cmd:**

```cmd
set DB_HOST=your_db_host
set DB_PORT=your_db_port
set DB_NAME=your_db_name
set DB_USERNAME=your_database_username
set DB_PASSWORD=your_database_password
set JwtKey=your_jwtKey
```

**4. Build and run the app using maven**

- Open the folder `back`:

```bash
cd back
```

- Install dependencies:

```bash
mvn clean install
```

- Launch the backend:

```bash
mvn spring-boot:run
```

## Ressources

### Postman collection

For Postman import the collection

> ressources/postman/MDD.postman_collection.json

by following the documentation:

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman

## Technologies used in this project :

- Java version 21
- Node.js version 22
- Maven
- MySQL
- Postman

## Author :

**Danchaud Vincent**
