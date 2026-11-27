# API Project

## Overview

This repository contains an API framework designed to test the spartan API and it endpoints
The framework uses Java and cucumber to allow testing of the API endpoints.

## Perquisites 
- Java
- Docker
- Cucumber
- Restassured
- Junit

## Setup

step 1:

To setup the repository you must first clone the current repository using the command
``` git clone https://github.com/cs-taha123h/APITestingProject.git ```

step 3:

if you are using the docker self hosted image you must clone the api testing framework.

```git clone https://github.com/cs-taha123h/SpartanAPI.git```

following the instructions from the readme.md to setup the docker image

step 3:

Open the cloned repositry and make changes to any of the enviroment.properties that need to be changed such as the API if you are using the self hosted version or the online exisiting implementation.

step 4:

you can run individual feature files such as the Auth.feature or you can run all of them together with the use of the test runner.


## How to contribute

1. to contribute before creating an step definition check whether you can reuse an already existing one if possible.
2. avoid repeating code
3. follow common java naming conventions
4. avoid commits that include sensitive data like API keys


