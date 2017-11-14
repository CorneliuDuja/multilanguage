Configuration:

src/main/resources/application.properties


Commands:

build: mvn clean install
run:   mvn spring-boot:run

Remarks:

On deploying make sure that clients are in the same time zone as server or deploy it on server with time zone UTC and consider this on client side for timestamp data
Tested for MySQL 5.5-5.7 and PostgreSQL 9.6