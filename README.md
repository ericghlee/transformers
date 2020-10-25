# Transformers

This is a spring boot project using h2 as a embedded database.

## Instructions

Build the project
```
./gradlew build
```

Run the project
```
./gradlew bootRun
```

Run the tests
```
./gradlew test
```

## API

### List

List all transformers registered.

```http
GET /api/list
```

Response example:
```json
Http status: 200
{
  "transformers": [
    {
      "id": "bombshell",
      "team": "D",
      "strength": 7,
      "intelligence": 7,
      "speed": 7,
      "endurance": 7,
      "rank": 7,
      "courage": 7,
      "firepower": 7
    },
    {
      "id": "inferno",
      "team": "A",
      "strength": 7,
      "intelligence": 4,
      "speed": 4,
      "endurance": 4,
      "rank": 3,
      "courage": 7,
      "firepower": 7
    }
  ]
}
```

### Create

Create a new transformer.

```http
POST /api/create
```
Request example:
```json
{
	"id": "Soundwave",
	"team": "A",
	"strength": "8", 
	"intelligence": "9", 
	"speed": "2", 
	"endurance": "6", 
	"rank": "7", 
	"courage": "5", 
	"firepower": "6"
}
```

Response example:
```json
Http status: 201
{
  "message": "Transformer with created succesfully",
  "error": false
}
```

### Update

Update an existing transformer.

```http
PUT /api/update
```
Request example:
```json
{
	"id": "Soundwave",
	"team": "A",
	"strength": "8", 
	"intelligence": "9", 
	"speed": "2", 
	"endurance": "6", 
	"rank": "7", 
	"courage": "5", 
	"firepower": "6"
}
```

Response example:
```json
Http status: 200
{
  "message": "Transformer with updated succesfully",
  "error": false
}
```

### Delete

Delete an existing transformer.

```http
DELETE /api/delete/{id}
```

Response example:
```json
Http status: 200
{
  "message": "Transformer deleted successfully",
  "error": null
}
```

### Battle

Simulates a battle with the registered transformeres.

```http
GET /api/delete/{id}
```

Response example:
```json
Http status: 200
{
  "total_battles": 3,
  "winner": "DECEPTICONS",
  "autobots_wins": 0,
  "decepticons_wins": 2,
  "ties": 1,
  "autobots_survivors": [],
  "decepticons_survivors": [
    "dirge",
    "starscream"
  ]
}
```