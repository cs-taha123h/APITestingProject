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
5. any defects found must be reported as a GitHub issue and be given a unique identifier so they can be referred to and where possible should also refer back to the swagger UI.

### Any defect reports must also include the following information:
- Severity: discussing how severe the defect is whether it impacts functionality and how big of an impact this is and why
- Overview: a small brief explanation of what the defect is.
- Replication: A step by step guide that can be that lead to the discovery of the defect to allow other developers to recreate the defect when needed.
- Actual result: Explanation of what the actual result is when the code is currently ran
- Expected result: Explanation of what the result should be and this should also be backed up with either the swagger UI stating that it should return X value or something else showing the current result is NOT the expected result.
- It should also have the status of the defect, whether the defect still exists or not.


