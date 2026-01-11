# Guide de Tests Postman

## Configuration de base

### Services disponibles
- **Eureka Server** : http://localhost:8761
- **SERVICE-CLIENT** : http://localhost:8081
- **SERVICE-CAR** : http://localhost:8089

---

## 📋 SERVICE-CLIENT (Port 8081)

### 1. Récupérer tous les clients
- **Méthode** : `GET`
- **URL** : `http://localhost:8081/api/clients`
- **Headers** : Aucun
- **Body** : Aucun
- **Réponse attendue** : Liste de tous les clients (Abla, Kaoutar, Hamza, Hadil seront créés automatiquement au démarrage)

### 2. Récupérer un client par ID
- **Méthode** : `GET`
- **URL** : `http://localhost:8081/api/clients/{id}`
- **Exemple** : `http://localhost:8081/api/clients/1`
- **Headers** : Aucun
- **Body** : Aucun
- **Réponse attendue** : Un objet Client avec l'ID spécifié

### 3. Créer un nouveau client
- **Méthode** : `POST`
- **URL** : `http://localhost:8081/api/clients`
- **Headers** :
  - `Content-Type: application/json`
- **Body** (JSON) :
```json
{
  "nom": "Nouveau Client",
  "age": 25.0
}
```
- **Réponse attendue** : Le client créé avec son ID généré

### 4. Supprimer tous les clients
- **Méthode** : `DELETE`
- **URL** : `http://localhost:8081/api/clients`
- **Headers** : Aucun
- **Body** : Aucun
- **Réponse attendue** : Code 200 (pas de contenu)

### 5. Supprimer un client par ID
- **Méthode** : `DELETE`
- **URL** : `http://localhost:8081/api/clients/{id}`
- **Exemple** : `http://localhost:8081/api/clients/1`
- **Headers** : Aucun
- **Body** : Aucun
- **Réponse attendue** : Code 200 (pas de contenu)

---

## 🚗 SERVICE-CAR (Port 8089)

### 1. Récupérer toutes les voitures
- **Méthode** : `GET`
- **URL** : `http://localhost:8089/api/cars`
- **Headers** : Aucun
- **Body** : Aucun
- **Réponse attendue** : Liste de toutes les voitures avec leurs clients associés

### 2. Récupérer les voitures d'un client spécifique
- **Méthode** : `GET`
- **URL** : `http://localhost:8089/api/cars/byClient/{clientId}`
- **Exemple** : `http://localhost:8089/api/cars/byClient/1`
- **Headers** : Aucun
- **Body** : Aucun
- **Réponse attendue** : Liste des voitures appartenant au client spécifié avec les informations du client

### 3. Créer une nouvelle voiture
- **Méthode** : `POST`
- **URL** : `http://localhost:8089/api/cars`
- **Headers** :
  - `Content-Type: application/json`
- **Body** (JSON) :
```json
{
  "marque": "Toyota",
  "modele": "Corolla",
  "clientId": 1
}
```
- **Réponse attendue** : La voiture créée avec son ID généré

---

## 🧪 Scénario de test complet recommandé

### Étape 1 : Vérifier les clients initiaux
1. **GET** `http://localhost:8081/api/clients`
   - Devrait retourner 4 clients : Abla, Kaoutar, Hamza, Hadil

### Étape 2 : Récupérer un client spécifique
2. **GET** `http://localhost:8081/api/clients/1`
   - Devrait retourner les informations d'Abla (ou le premier client)

### Étape 3 : Créer une voiture pour un client
3. **POST** `http://localhost:8089/api/cars`
   ```json
   {
     "marque": "Renault",
     "modele": "Clio",
     "clientId": 1
   }
   ```

### Étape 4 : Créer d'autres voitures
4. **POST** `http://localhost:8089/api/cars`
   ```json
   {
     "marque": "Peugeot",
     "modele": "208",
     "clientId": 2
   }
   ```

5. **POST** `http://localhost:8089/api/cars`
   ```json
   {
     "marque": "BMW",
     "modele": "Serie 3",
     "clientId": 1
   }
   ```

### Étape 5 : Récupérer toutes les voitures
6. **GET** `http://localhost:8089/api/cars`
   - Devrait retourner toutes les voitures avec leurs clients associés

### Étape 6 : Récupérer les voitures d'un client spécifique
7. **GET** `http://localhost:8089/api/cars/byClient/1`
   - Devrait retourner les voitures du client avec l'ID 1 (Abla)

### Étape 7 : Créer un nouveau client
8. **POST** `http://localhost:8081/api/clients`
   ```json
   {
     "nom": "Test Client",
     "age": 30.0
   }
   ```

### Étape 8 : Supprimer un client
9. **DELETE** `http://localhost:8081/api/clients/5`
   - Supprime le client créé à l'étape 7

---

## 📝 Exemples de Body JSON

### Structure Client
```json
{
  "nom": "Nom du client",
  "age": 25.0
}
```

### Structure Car
```json
{
  "marque": "Marque de la voiture",
  "modele": "Modèle de la voiture",
  "clientId": 1
}
```

---

## ⚠️ Notes importantes

1. **Clients automatiques** : Au démarrage de SERVICE-CLIENT, 4 clients sont automatiquement créés :
   - Abla (23 ans)
   - Kaoutar (23 ans)
   - Hamza (22 ans)
   - Hadil (22 ans)

2. **Ordre de démarrage recommandé** :
   - MySQL doit être démarré
   - Eureka Server (port 8761)
   - SERVICE-CLIENT (port 8081)
   - SERVICE-CAR (port 8089)

3. **Relation Car-Client** : Lors de la récupération des voitures, les informations du client associé sont automatiquement incluses grâce au service ClientApi.

4. **ID automatiques** : Ne pas inclure l'ID dans le body JSON lors de la création, il sera généré automatiquement.

---

## 🔍 Vérification Eureka

Pour vérifier que les services sont bien enregistrés dans Eureka :
- **URL** : `http://localhost:8761`
- Devrait afficher SERVICE-CLIENT et SERVICE-CAR avec le statut UP

