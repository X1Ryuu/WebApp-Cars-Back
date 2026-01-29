# WebApp-Cars-Back


## Introduction
This repository contains back-end part of my web application for managing a Car dealership, as well as some other features to be made.
This is my personal project, still in development - gradually will add planned features.

## Requirements
- For keycloak I use desktop docker
  - In docker terminal write: `docker run -p 9090:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.2.1 start-dev`
  - After the container started go to `localhost:9090` and input *admin* as username and password
  - Then click button *Create realm* and import *realm-export.json* file in `realms` directory in main project directory
- To make Spring Boot application to work properly you must enable annotation processing. After that it should work just fine

## Technologies
- Spring Boot
- Spring Security
- Spring Data JPA
- REST API
- H2 database

## Features
- Authentication and authorization via Keycloak
- Role-based access control
- REST API to manage operations between front-end and back-end
