# Java Spring REST API Design Doc

## Requirement

**User**
* id (auto-generated)
* firstName (must have value)
* lastName (must have value)
* middleName
* dob (date of birth, must have value)

## REST API

### POST

**Request url:** localhost:8080/userinfo

**Request body:** raw json format
```json
{
    "provider" : {
        "firstName": "Kobe",
        "lastName": "Bryant",
        "middleName": "Bean",
        "dob": "1978-08-23"
    }
}
```
**Respond Code:** 201 CREATED

**Respond:**
```json
{
  "firstName": "Kobe",
  "lastName": "Bryant",
  "middleName": "Bean",
  "dob": "1978-08-23"
}
```

**Request body:** raw json format
```json
{
    "provider" : {
        "firstName": "Jeremy",
        "lastName": "Lin",
        "dob": "1978-08-23"
    }
}
```
**Respond Code:** 201 CREATED

**Respond:**
```json
{
  "firstName": "Jeremy",
  "lastName": "Lin",
  "middleName": null,
  "dob": "1978-08-23"
}
```

**Request body:** raw json format
```json
{
    "provider" : {
        "firstName": "",
        "lastName": "",
        "middleName": ""
    }
}
```
**Respond Code:** 400 Bad Request

**Respond:** (empty)

**Request body:** raw json format
```json
{
  "firstName": "Kevin",
  "lastName": "Durant",
  "middleName": "Wayne",
  "dob": "1988-09-29"
}
```
**Respond Code:** 400 Bad Request

**Respond:** (empty)


### GET

**Request url:** localhost:8080/userinfo

**Request body:** none

**Respond code:** 200 OK

**Respond:**
```json
{
  "userList": [
    {
      "firstName": "Kobe",
      "lastName": "Bryant",
      "middleName": "Bean",
      "dob": "1978-08-23"
    },
    {
      "firstName": "Jeremy",
      "lastName": "Lin",
      "middleName": null,
      "dob": "1988-08-23"
    }
  ]
}
```

