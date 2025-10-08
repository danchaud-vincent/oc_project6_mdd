# Front : MDD APPLICATION

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 19.2.9.

## Project Description

This MVP application allows users to subscribe to programming-related topics (such as JavaScript, Java, Python, Web3, etc.).  
The news feed displays relevant articles in chronological order. Users can also write articles and post comments.

### Project Goals

The goal of this project is to develop a **Minimum Viable Product (MVP)**.  
The backend is built with **Spring Boot**, and the frontend is developed using **Angular**.

As this is an MVP, automated tests have not been implemented yet. Testing will be included in future iterations.

## Installation :

> Note: The path front is relative to the root of the project (where you cloned the repo), not your current directory.

Before running the project, make sur your environment meets the following requirements:

### Prerequisites :

- [**Java**](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) version 21
- [**Node.js**](https://nodejs.org/en) version 22
- [**Angular CLI**](https://github.com/angular/angular-cli) version 19.2
- [**MySQL**](https://www.mysql.com/) (for the backend database)

### Setps to Setup

**0. Before running the frontend**

To use the app, make sure that the backend server is started before starting the frontend.
Otherwise, the tests are available and can be execute without the backend running. [See this section](#tests)

**1. Clone the application**

```bash
git clone https://github.com/danchaud-vincent/oc_project6_mdd.git
```

**2. Go inside the front folder (from the project root)**

```bash
cd front
```

**3. Install dependencies**

```bash
npm install
```

**4. To start a local development server, run:**

```bash
npm run start
```

or

```bash
ng serve --open
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.

### Postman collection

For Postman import the collection

> ressources/postman/yoga.postman_collection.json

by following the documentation:

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman

## Author :

**Danchaud Vincent**
