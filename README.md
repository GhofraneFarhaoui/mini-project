Aperçu du projet
Ce projet est une architecture microservices simple composée de trois services indépendants :

auth-service
Gère l’inscription, l’authentification et la génération de tokens JWT.
product-service
Permet la gestion des produits (CRUD). Accès protégé par JWT.
order-service
Permet la création de commandes associées à un utilisateur. Protégé par JWT.
Chaque service fonctionne avec Spring Boot, utilise une base de données H2 en mémoire et communique via HTTP REST.

Technologies
Java 17
Spring Boot 3
Spring Web
Spring Data JPA
Spring Security
JWT (JJWT 0.11.5)
H2 Database
Maven

Fonctionnalités principales
1. Auth-Service
Enregistrement utilisateur avec mot de passe haché BCrypt
Connexion + génération d’un Jeton JWT signé
Endpoints :
POST /api/auth/register
POST /api/auth/login

2. Product-Service
CRUD complet des produits
Protection par JWT (header Authorization: Bearer <token>)
Endpoints :
GET /api/products
POST /api/products
PUT /api/products/{id}
DELETE /api/products/{id}

3. Order-Service
Création de commandes pour un utilisateur connecté

Lecture des commandes
Endpoints :
POST /api/orders
GET /api/orders/user/{username}

                    

