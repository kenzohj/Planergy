# ⚡ PLANERGY - ERP (2023)

<p align="center">
  <img src="https://github.com/kenzohj/Planergy/blob/main/login.png" alt="Login page" width="400" />
  <img src="https://github.com/kenzohj/Planergy/blob/main/home.png" alt="Home page" width="650" />
</p>

**🇫🇷 Français | [🇬🇧 English below](#-english-version-)**  

---

## 📌 À propos du projet  

**PLANERGY** est un **ERP** (Enterprise Resource Planning) développé dans le cadre de notre **2ᵉ année de BUT Informatique**. Le cahier des charges nous a été fourni, et nous avons réalisé ce projet en équipe de **6 étudiants**.  

---

## 🖥️ Back-end (API REST)  

### 🚀 Développement  

L'API REST a été développée par :  
- [Corentin Bonjour](https://github.com/corentinbjr)  
- [Kenzo Hambli](https://github.com/KenzoHJ)  

Elle permet la gestion des utilisateurs, des commandes, des stocks et d'autres fonctionnalités essentielles d'un ERP.  

---

### 🔧 Technologies utilisées  

- **Langage :** Java 17
- **Framework :** [Spring Boot](https://spring.io/projects/spring-boot)
- **Base de données :** [MongoDB](https://www.mongodb.com/)
- **Sécurité :** Hashing des mots de passe avec un salt
- **Gestion des requêtes :** Java Record

---

### 📂 Endpoints principaux  

👤 **Gestion des utilisateurs →** `/api/employee`

📍 **Gestion des clients →** `/api/customer`

📦 **Gestion des stocks →** `/api/energy` & `/api/product`

🛒 **Gestion des commandes →** `/api/restock`

> **💡 Note :** La documentation complète des endpoints est disponible via Swagger à [`http://localhost:8080/api.html`](http://localhost:8080/api.html). 

---

## 🎨 Front-end  

### 🚀 Développement  

Le **front-end** a été principalement développé par **nos 4 autres camarades**, avec quelques pages réalisées par **Corentin Bonjour et Kenzo Hambli**.  

Il communique avec l'API REST via des **requêtes AJAX**, mises en place par Kenzo et Corentin.  

---

### 🎨 Technologies utilisées  

- **HTML, CSS, JavaScript**  
- [**Bootstrap**](https://getbootstrap.com/) pour la mise en page 
- [**FontAwesome**](https://fontawesome.com/icons) pour les icônes
- [**AJAX**](https://developer.mozilla.org/en-US/docs/Glossary/AJAX) pour les requêtes API (gérées par Kenzo & Corentin)  
- [**jsPDF**](https://www.npmjs.com/package/jspdf) pour la génération de documents PDF  
- [**Google Fonts**](https://fonts.google.com/) pour les polices d'écriture  

---

## 📝 Licence  

Ce projet a été développé dans un cadre scolaire et n'est pas destiné à un usage commercial.  

---

## 🇬🇧 English Version  

## 🖥️ Back-end (REST API)  

### 🚀 Development  

The REST API was developed by:  
- [Corentin Bonjour](https://github.com/corentinbjr)  
- [Kenzo Hambli](https://github.com/KenzoHJ)  

It handles user management, orders, stock tracking, and other essential ERP functionalities.  

---

### 🔧 Technologies Used  

- **Language:** Java 17  
- **Framework:** [Spring Boot](https://spring.io/projects/spring-boot)  
- **Database:** [MongoDB](https://www.mongodb.com/)  
- **Security:** Password hashing with a salt  
- **Request Handling:** Java Record

---

### 📂 Main Endpoints  

👤 **User Management →** `/api/employee`

📍 **Customer management →** `/api/customer`

📦 **Stock Management →** `/api/energy` & `/api/product`

🛒 **Order Management →** `/api/restock` 

> **💡 Note:** Full API documentation is available via Swagger at [`http://localhost:8080/api.html`](http://localhost:8080/api.html).  

---

## 🎨 Front-end  

### 🚀 Development  

The **front-end** was mainly developed by **our 4 other teammates**, with some pages made by **Corentin Bonjour and Kenzo Hambli**.  

It interacts with the REST API through **AJAX requests**, which were handled by Kenzo & Corentin.  

---

### 🎨 Technologies Used  

- **HTML, CSS, JavaScript**  
- [**Bootstrap**](https://getbootstrap.com/) for UI design 
- [**FontAwesome**](https://fontawesome.com/icons) for icons
- [**AJAX**](https://developer.mozilla.org/en-US/docs/Glossary/AJAX) for API requests (handled by Kenzo & Corentin)  
- [**jsPDF**](https://www.npmjs.com/package/jspdf) for PDF generation  
- [**Google Fonts**](https://fonts.google.com/) for typography  

---

## 📝 License  

This project was developed for educational purposes and is not intended for commercial use.  
