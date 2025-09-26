# Notes Backend API

Base URL: /api/v1/notes

Endpoints:
- POST /api/v1/notes
- GET /api/v1/notes?userId={userId}
- GET /api/v1/notes/{id}
- PUT /api/v1/notes/{id}
- DELETE /api/v1/notes/{id}?userId={userId}

Example Create:
curl -X POST http://localhost:8080/api/v1/notes \
  -H "Content-Type: application/json" \
  -d '{"userId":"user-123","title":"Grocery List","content":"- Apples\n- Bananas"}'

Swagger UI: /swagger-ui.html
H2 Console: /h2-console
