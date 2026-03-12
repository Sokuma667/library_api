# Library API

API Spring Boot (Java 17) avec authentification JWT et accès MySQL.

**Prérequis**
- Java 17
- Maven (ou `./mvnw`)
- MySQL en local avec une base `library_db`

**Configuration**

Variables d’environnement requises:
- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET` (clé Base64, 32+ bytes pour HS256)

Exemple (Linux/macOS):
```bash
export DB_USERNAME=sensei
export DB_PASSWORD=motdepasse
export JWT_SECRET="$(openssl rand -base64 32)"
```

**Profil local**

Un fichier local non commité peut contenir une clé JWT persistante:
- `src/main/resources/application-local.properties`

Activer le profil local:
```bash
SPRING_PROFILES_ACTIVE=local ./mvnw spring-boot:run
```

**Lancer l’application**

```bash
./mvnw spring-boot:run
```

Par défaut, l’application écoute sur `http://localhost:8080`.

**Endpoints principaux**

- `POST /auth/login` : obtient un token JWT
- `GET /books` : rôle `USER` ou `ADMIN`
- `POST /books` : rôle `ADMIN`
- `PUT /books/{id}` : rôle `ADMIN`
- `DELETE /books/{id}` : rôle `ADMIN`
- `GET /users` : rôle `ADMIN`
- `POST /users` : rôle `ADMIN`
- `PUT /users/{id}` : rôle `ADMIN`
- `DELETE /users/{id}` : rôle `ADMIN`

**Authentification**

1. Login:
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"pass"}'
```

2. Utiliser le token:
```bash
curl http://localhost:8080/books \
  -H "Authorization: Bearer <TOKEN>"
```

**Notes**
- Si tu changes `JWT_SECRET`, tous les tokens existants deviennent invalides.
- Les identifiants DB ne doivent pas être commités (utiliser les variables d’environnement).
