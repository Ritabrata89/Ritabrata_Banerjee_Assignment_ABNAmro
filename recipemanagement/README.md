#Recipe Management API

#### API know how
This application Web Service runs on a docker container using embedded tomcat in spring boot
and uses Postgresql which also runs on a docker container.
The application is developed following domain driven design approach. 
It maintains **Single-Responsibility Principle** and **Liskov Substitution Principle**.

The application has its API document, and it can be viewed using this url 
http://localhost:8081/recipe-api-docs after application startup.

* This API is safe from CSRF and XSS attack.
* This API validates the category of the recipes and accepts only *Veg* and *Nonveg* as valid.
* This API validates the instruction of the recipes and only accepts alpha-numeric values. 
It ensures that unnecessary scripts (Example: ```<script>alert(1)</script>```) can not be injected.
* This API validates the serving capacity of the recipes and accepts values between 2-8.

#### How to build the project:
- Clone the project from git repository.
- Import as a maven project in IDE using pom.xml of the application
- Do a maven build with goal "clean install"

#### How to run the application and Postgresql in Docker
(Assumption- Docker is running on system)
- Open a terminal.
- Navigate to the project base path which contains the docker-compose.yml.
- Run the below command

	```$ docker-compose up```


#### Quick test in postman
(Assumption- Interceptor is enabled in postman and capture cookies is turned on)
##### Scenario 1:> To fetch all available recipes:

- Select method as *GET*
- Request url: localhost:8081/api/recipes
- As database is empty, we receive an empty list.

- Sample response JSON:
```
  []
```
- Select cookies tab.
- Find the cookie named **XSRF-TOKEN**.
- Copy the value of the token and paste in a text editor for future use.
- Sample **XSRF-TOKEN** value is *46fb20c3-4b03-4327-9ce5-466e7d4f9fc2*.
- Sample response JSON when database is not empty:
```
[
    {
        "id": 1,
        "lastUpdated": "14-03-2022 01:27",
        "category": "Nonveg",
        "serves": 6,
        "ingredients": [
            {
                "name": "Potato",
                "id": 1
            },
            {
                "name": "Chicken",
                "id": 2
            },
            {
                "name": "Onion",
                "id": 3
            }
        ],
        "instruction": "Chicken curry"
    },
    {
        "id": 2,
        "lastUpdated": "14-03-2022 01:28",
        "category": "Veg",
        "serves": 4,
        "ingredients": [
            {
                "name": "Potato",
                "id": 4
            },
            {
                "name": "Cumin",
                "id": 5
            },
            {
                "name": "Oil",
                "id": 6
            }
        ],
        "instruction": "Potato curry"
    }
]
```

##### Scenario 2:> To save a new recipe

- Select method as *POST*
- Request url: localhost:8081/api/recipes
- Headers: **KEY** : *X-XSRF-TOKEN* and **VALUE** : *46fb20c3-4b03-4327-9ce5-466e7d4f9fc2*.
- Replace the value of XSRF-TOKEN with the value you pasted in text editor earlier.
- Sample request body is below:
```
  {
    "category": "Veg",
    "serves": 6,
    "ingredients": [
        {
            "name": "Paneer"
        },
        {
            "name": "onion"
        },
        {
            "name": "Cream"
        }
    ],
    "instruction": "Paneer curry"
}
```
- Sample response is as below:
```
    {
    "id": 3,
    "lastUpdated": "14-03-2022 01:30",
    "category": "Veg",
    "serves": 6,
    "ingredients": [
        {
            "name": "Paneer",
            "id": 7
        },
        {
            "name": "onion",
            "id": 8
        },
        {
            "name": "Cream",
            "id": 9
        }
    ],
    "instruction": "Paneer curry"
    }
```

##### Scenario 3:> To update a recipe

- Select method as *PUT*
- Request url: localhost:8081/api/recipes/1
  Here 1 is a path variable which is the recipe id to be supplied.
- Headers: **KEY** : *X-XSRF-TOKEN* and **VALUE** : *46fb20c3-4b03-4327-9ce5-466e7d4f9fc2*.
- Replace the value of XSRF-TOKEN with the value you pasted in text editor earlier.
- Sample request body is as below:
```
  {
        "category": "Nonveg",
        "serves": 8,
        "ingredients": [
            {
                "name": "Potato",
                "id": 1
            },
            {
                "name": "Chicken",
                "id": 2
            },
            {
                "name": "Onion",
                "id": 3
            },
            {
                "name": "Garlic"
            }
        ],
        "instruction": "Chicken Garlic curry"
    }
```

- Sample response is as below:
```
    {
    "id": 1,
    "lastUpdated": "14-03-2022 01:33",
    "category": "Nonveg",
    "serves": 8,
    "ingredients": [
        {
            "name": "Potato",
            "id": 1
        },
        {
            "name": "Chicken",
            "id": 2
        },
        {
            "name": "Onion",
            "id": 3
        },
        {
            "name": "Garlic",
            "id": 10
        }
    ],
    "instruction": "Chicken Garlic curry"
}
```

##### Scenario 3:> To delete a recipe

- Select method as *DELETE*
- Request url: localhost:8081/api/recipes/1
  Here 1 is a path variable which is the recipe id to be supplied.
- Headers: **KEY** : *X-XSRF-TOKEN* and **VALUE** : *46fb20c3-4b03-4327-9ce5-466e7d4f9fc2*.
- Replace the value of XSRF-TOKEN with the value you pasted in text editor earlier.
- Sample response is as below:

``Recipe has been deleted successfully``

#### Improvements to make

- Authentication process could be implemented.
- Payload can be encrypted.
- Code coverage for unit tests could be improved so that it covers all possible cases.
- We can implement caching so that the application performs faster.
- We can add capability to search by recipe id.
- We can implement a DTO class.

Note: This application uses LOMBOK annotations so please enable option for Lombok annotations in the IDE if required.