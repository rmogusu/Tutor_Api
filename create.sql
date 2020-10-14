CREATE DATABASE tutor_database;
\c tutor_database;
CREATE TABLE IF NOT EXISTS tutors (id serial PRIMARY KEY,name VARCHAR,subjects VARCHAR,bio VARCHAR,phone VARCHAR,experience VARCHAR,rating VARCHAR,imageUrl VARCHAR);
CREATE DATABASE tutor_test_database WITH TEMPLATE tutor_database;