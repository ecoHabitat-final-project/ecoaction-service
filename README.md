# Ecoaction Microservice - EcoHabitat Platform

Este microservicio forma parte del ecosistema **EcoHabitat** y es el **servicio central de catálogo ecológico**, encargado de registrar y gestionar las ecoacciones realizadas por los usuarios en sus viviendas. Su función es coordinar la información proveniente del `user-service` y del `habitat-service` y enriquecerla en una vista compuesta, actuando como **agregador de datos**.

---

## Módulo: ecoaction-service

### Funcionalidad principal

- Crear, consultar, actualizar y eliminar ecoacciones.
- Enriquecer ecoacciones con datos obtenidos vía **Feign Clients** desde `user-service` y `habitat-service`.
- Registrar nuevas viviendas y nuevos usuarios de forma indirecta.
- Actuar como **servicio de catálogo ecológico**, combinando los datos de los otros microservicios.

---

## Modelo principal

```java
public class Ecoaction {
    private long id;
    private String description;
    private String date;
    private long userId;
    private long habitatId;
}
```

---

## Endpoints REST

### ➕ Crear ecoacción

`POST /api/ecoaction`  
Crea una nueva ecoacción con ID de usuario y hábitat.

### Obtener ecoacción por ID

`GET /api/ecoaction/{ecoactionId}`  
Devuelve una `EcoactionResponseDTO` con datos enriquecidos de usuario y vivienda.

###  Obtener todas las ecoacciones

`GET /api/ecoaction`

### Obtener ecoacciones por usuario o hábitat

- `GET /api/ecoaction/user/{userId}`
- `GET /api/ecoaction/habitat/{habitatId}`

###  Actualizar ecoacción (cambio de hábitat)

`PATCH /api/ecoaction/update/{ecoactionId}`

###  Eliminar ecoacción

`DELETE /api/ecoaction/delete/{ecoactionId}`

### Crear usuario o vivienda desde el catálogo

- `POST /api/ecoaction/user`
- `POST /api/ecoaction/habitat`

---
### Tabla de Endpoints – `EcoactionController`

| Método | Ruta                                                   | Descripción                                                                 |
|--------|--------------------------------------------------------|-----------------------------------------------------------------------------|
| `GET`  | `/api/ecoaction`                                       | Obtiene todas las ecoacciones con información agregada de usuario y hábitat. |
| `POST` | `/api/ecoaction`                                       | Crea una nueva ecoacción registrando su `userId` y `habitatId`.             |
| `POST` | `/api/ecoaction/user`                                  | Registra un nuevo usuario remoto desde el catálogo.                         |
| `POST` | `/api/ecoaction/habitat`                               | Registra una nueva vivienda remota desde el catálogo.                       |
| `PATCH`| `/api/ecoaction/update/{ecoactionId}`                  | Actualiza el `habitatId` de una ecoacción ya existente.                     |
| `GET`  | `/api/ecoaction/{ecoactionId}`                         | Obtiene la ecoacción por ID con información enriquecida.                    |
| `GET`  | `/api/ecoaction/user/{userId}`                         | Busca ecoacciones asociadas a un determinado usuario.                       |
| `GET`  | `/api/ecoaction/habitat/{habitatId}`                   | Busca ecoacciones realizadas sobre un hábitat específico.                   |
| `DELETE` | `/api/ecoaction/delete/{ecoactionId}`               | Elimina una ecoacción existente por su ID.                                  |



## Estructura del proyecto

```
com.ecohabitat.ecoaction_service
│
├── clients
│   ├── HabitatFeignClient
│   └── UserFeignClient
│
├── controllers
│   └── EcoactionController
│
├── dto
│   ├── EcoactionRequestDTO / EcoactionResponseDTO
│   ├── UserResponseDTO / HabitatResponseDTO
│   └── EcoUserDTO / EcoHabitatDTO
│
├── exceptions
│   ├── EcoactionNotFoundException
│   └── GlobalExceptionHandler
│
├── models
│   └── Ecoaction
│
├── repositories
│   └── EcoactionRepository
│
├── services
│   └── EcoactionService
```

---
## Feign Clients utilizados por Ecoaction Service

El microservicio `ecoaction-service` utiliza dos clientes declarativos `@FeignClient` para comunicarse con los microservicios remotos `user-service` y `habitat-service`. A continuación se describen sus métodos y finalidad:

---

#### `UserFeignClient`

Se conecta al microservicio `user-service` mediante su nombre lógico de registro en Eureka (`user-service`). Este cliente se encarga de recuperar y registrar datos de usuarios vinculados a las ecoacciones.

| Método   | Endpoint remoto          | Descripción                                     |
|----------|---------------------------|-------------------------------------------------|
| `GET`    | `/api/user/{id}`         | Obtiene la información de un usuario por ID.    |
| `POST`   | `/api/user`              | Crea un nuevo usuario remoto desde Ecoaction.   |

---

#### `HabitatFeignClient`

Se conecta al microservicio `habitat-service` mediante el nombre lógico `habitat-service`. Este cliente facilita el acceso y el registro de viviendas (hábitats) relacionadas con ecoacciones.

| Método   | Endpoint remoto           | Descripción                                       |
|----------|----------------------------|---------------------------------------------------|
| `GET`    | `/api/habitat/{id}`       | Recupera los datos de un hábitat por ID.         |
| `POST`   | `/api/habitat`            | Crea una nueva vivienda desde Ecoaction.         |

> Ambos Feign Clients están definidos en el paquete `com.ecohabitat.ecoaction_service.clients` y permiten una integración limpia y desacoplada entre microservicios.



- Este microservicio utiliza **Spring Boot**, **Spring Data JPA**, **Feign Clients**, y **Eureka** para discovery.

- Los datos agregados del catálogo son retornados como `EcoactionResponseDTO`, conteniendo datos combinados de usuario y vivienda.

---
