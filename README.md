Login : 
Method: POST
URL : http://localhost:8080/api/auth/generate-token

Register : 
Method: POST
URL : http://localhost:8080/api/auth/register

Checklist:
URL: http://localhost:8080/api/checklists
Paylod Create: {
    "title": "Checklist Title 2",
    "items": [
        {
            "name": "Buy groceries 2",
            "completed": false
        },
        {
            "name": "Walk the dog 2",
            "completed": false
        }
    ]
}

Payload Updated: {
    "id": 1,
    "title": "Checklist Title 2",
    "items": [
        {
            "name": "Buy groceries 2",
            "completed": false
        },
        {
            "name": "Walk the dog 2",
            "completed": false
        }
    ]
}

Items:
URL: http://localhost:8080/api/items/1
