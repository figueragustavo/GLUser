# GLUser
Api de manejo de usuario para BCI

Proyecto Spring Boot con Swagger y Spring Security
Descripción
Este proyecto es una aplicación Spring Boot que incluye:

Endpoints de usuario: Registro y autenticación de usuarios.
Swagger: Documentación de la API.
Spring Security: Seguridad con JWT (JSON Web Token).
Tecnologías Utilizadas
Spring Boot: Framework para construir aplicaciones Java.
Spring Security: Seguridad de aplicaciones.
Swagger: Documentación de la API.
H2 Database: Base de datos en memoria para desarrollo.
Instalación
Requisitos
Java 8 o superior
Maven
Configuración del Proyecto
Clonar el repositorio:

bash
git clone https://github.com/figueragustavo/GLUser.git
cd tu_repositorio
Construir el proyecto:

bash
mvn clean install
Ejecutar la aplicación:

bash
mvn spring-boot:run

Swagger
Swagger está configurado para permitir la autenticación Bearer. Puedes acceder a la documentación de la API en:

http://localhost:8080/swagger-ui.html

Seguridad
La configuración de seguridad permite el acceso sin autenticación a Swagger y requiere autenticación para otros endpoints.

Realizar pruebas (POSTMAN)
Endpoints
Registro de Usuario
POST /api/v1/user/sign-up

Descripción: Crea un nuevo usuario.

Body:

json

{
  "email": "string",
  "name": "string",
  "password": "string",
  "phones": [
    {
      "cityCode": "string",
      "countryCode": "string",
      "number": "string"
    }
  ]
}

Inicio de Sesión
GET /api/users/login

Descripción: Obtiene información del usuario autenticado.

Headers:

Authorization: Bearer {token}

