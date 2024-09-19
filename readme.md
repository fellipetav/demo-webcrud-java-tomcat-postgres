## Project
This is a demo project for study purposes. It consists of a simple CRUD comprising a Java web service as backend plus a Bootstrap frontend built with Spring Boot, Apache TomCat and PostgresSQL as database.

## How to use
### About the Postgres database
No need to install a Postgres on your local machine. Simply use the provided docker-compose.yaml file. Change the database properties, volume, port and other settings as per your needs and run `docker-compose up -d` to start the database.

Using a database admin, such as Dbeaver (like I did) you can connect to the database pointing to the database port (using user and password etc.) as you set in the `docker-compose.yaml` file.

## Running the project
Execute the DemoApplication.java on your IDE and go to http://localhost:8080 to see the demo.