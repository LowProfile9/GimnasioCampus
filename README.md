<div align="center">
<img src="https://dummyimage.com/900x200/2c3e50/ffffff&text=Gimnasio+Campus" width="80%" />

<br><br>

# <strong style="font-size:32px;">SISTEMA DE GESTIÓN DE GIMNASIO</strong>
# API REST para administración de clientes y rutinas
</div>

<br>

<div align="center">

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.x-blue?style=for-the-badge&logo=mysql)
![Status](https://img.shields.io/badge/Status-Activo-success?style=for-the-badge)

</div>

##  Descripción del Proyecto

**Gimnasio Campus** es una API REST desarrollada en Spring Boot para gestionar clientes y rutinas de entrenamiento. El sistema utiliza JPA con MySQL, validaciones de datos y manejo de errores HTTP. Además, incluye documentación automática con Swagger UI.

## Video de sustentacion del proyecto

https://drive.google.com/file/d/1Kzjk3etQ0TabuBLC44Y0Si01EixXpw4l/view?usp=sharing

### Características principales:

- **Gestión de Clientes**: CRUD completo con validación y control de duplicados
- **Gestión de Rutinas**: CRUD completo con reglas de unicidad
- **Asignación de Rutinas**: Asociación y desasignación cliente-rutina
- **Consultas Relacionales**: Rutinas por cliente y clientes por rutina
- **Documentación API**: Swagger UI habilitado

##  Arquitectura del Proyecto

La aplicación usa una arquitectura en capas basada en componentes Spring:

| Componente | Descripción |
|------------|-------------|
| **@RestController** | Exposición de endpoints REST |
| **@Service** | Lógica de negocio para clientes y rutinas |
| **@Repository** | Acceso a datos mediante JPA |
| **@Entity** | Modelos persistentes (Cliente y Rutina) |
| **@RestControllerAdvice** | Manejo centralizado de errores |
| **Spring Data JPA** | ORM para MySQL |

##  Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|------------|---------|-----|
| **Java** | 17+ | Lenguaje base del sistema |
| **Spring Boot** | 4.x | Framework principal |
| **Spring Web MVC** | 6.x | API REST |
| **Spring Data JPA** | 4.x | Persistencia y ORM |
| **MySQL** | 8.x | Base de datos |
| **Maven** | 3.8+ | Gestión de dependencias |

##  Estructura del Proyecto

```
gimnasiocampus/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── gimnasiocampus/
    │   │       ├── controller/
    │   │       │   ├── ClienteController.java
    │   │       │   └── RutinaController.java
    │   │       ├── entity/
    │   │       │   ├── Cliente.java
    │   │       │   └── Rutina.java
    │   │       ├── exception/
    │   │       │   ├── AppExceptionHandler.java
    │   │       │   ├── ConflictDbException.java
    │   │       │   ├── MyError.java
    │   │       │   └── RegistroNoEncontradoException.java
    │   │       ├── repository/
    │   │       │   ├── ClienteRepository.java
    │   │       │   └── RutinaRepository.java
    │   │       ├── service/
    │   │       │   ├── ClienteService.java
    │   │       │   └── RutinaService.java
    │   │       └── GimnasioApplication.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/
            └── gimnasiocampus/
                └── com/
                    └── gimnasiocampus/
                        └── GimnasiocampusApplicationTests.java
```

##  Estructura del Sistema

### Módulos Funcionales

| Módulo | Funciones Disponibles |
|--------|----------------------|
|  **Clientes** | • Crear, listar, actualizar y eliminar<br>• Validación de documento único |
|  **Rutinas** | • Crear, listar, actualizar y eliminar<br>• Validación de nombre único |
|  **Relaciones** | • Asignar y quitar rutinas a clientes<br>• Consultas cruzadas |
|  **Documentación** | • Swagger UI y API Docs |

### Modelo de Datos

El sistema gestiona las siguientes entidades principales:

**Cliente**
- Id (autogenerado)
- Nombre
- Documento (único)
- Activo
- Rutinas (relación many-to-many)

**Rutina**
- Id (autogenerado)
- Nombre (único)
- Nivel
- Clientes (relación many-to-many)

##  Instalación y Ejecución

### Prerrequisitos

- JDK 17 o superior instalado
- Maven 3.8+ instalado
- MySQL instalado
- Git instalado

### Pasos de instalación

```bash
# 1. Clonar el repositorio
git clone <URL_DEL_REPOSITORIO>
cd gimnasiocampus

# 2. Crear la base de datos
mysql -u root -p -e "CREATE DATABASE gimnasio_db;"

# 3. Ejecutar la aplicación
./mvnw spring-boot:run
```

### Ejecución desde IDE

1. Importar el proyecto como proyecto Maven
2. Ejecutar la clase principal: `GimnasioApplication.java`
3. La aplicación iniciará en `http://localhost:8080`

##  Uso del Sistema

### Documentación Swagger

Acceso a Swagger UI:

```
http://localhost:8080/doc/swagger-ui.html
```

### Endpoints principales

```
GET    /api/clientes
GET    /api/clientes/{id}
POST   /api/clientes
PUT    /api/clientes/{id}
DELETE /api/clientes/{id}
POST   /api/clientes/{clienteId}/rutinas/{rutinaId}
GET    /api/clientes/{clienteId}/rutinas
DELETE /api/clientes/{clienteId}/rutinas/{rutinaId}

GET    /api/rutinas
GET    /api/rutinas/{id}
POST   /api/rutinas
PUT    /api/rutinas/{id}
DELETE /api/rutinas/{id}
GET    /api/rutinas/{rutinaId}/clientes
```

##  Conceptos de Spring Aplicados

### 1. Inyección de Dependencias por Constructor

```java
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final RutinaRepository rutinaRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          RutinaRepository rutinaRepository) {
        this.clienteRepository = clienteRepository;
        this.rutinaRepository = rutinaRepository;
    }
}
```

### 2. Validaciones con Bean Validation

```java
@NotBlank
private String nombre;
```

### 3. Manejo Global de Errores

```java
@RestControllerAdvice
public class AppExceptionHandler {
    // Manejo de errores 404, 409, 500
}
```

##  Ventajas de Spring Framework

| Aspecto | Java Tradicional | Con Spring Boot |
|---------|-----------------|-----------------|
| **Creación de objetos** | Manual con `new` | Automática por Spring |
| **Gestión de dependencias** | Manual y propensa a errores | Inyección automática |
| **Configuración** | Larga y repetitiva | Anotaciones simples |
| **Ciclo de vida** | Control manual | Gestionado por contenedor |
| **Acoplamiento** | Alto | Bajo (Inversión de Control) |

##  Equipo de Desarrollo

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/identicons/deibys.png" width="100px;" alt="Deibys Bermúdez"/>
      <br />
      <sub><b>Deibys Sebastián Bermúdez Orduz</b></sub>
      <br />
      <sub>Backend Developer</sub>
    </td>
    <td align="center">
      <img src="https://github.com/identicons/pamela.png" width="100px;" alt="Pamela Galvis"/>
      <br />
      <sub><b>Pamela Michell Galvis Álvarez</b></sub>
      <br />
      <sub>Backend Developer</sub>
    </td>
    <td align="center">
      <img src="https://github.com/identicons/andres.png" width="100px;" alt="Andres Mendoza"/>
      <br />
      <sub><b>Andres Felipe Mendoza Gomez</b></sub>
      <br />
      <sub>Backend Developer</sub>
    </td>
  </tr>
</table>

---

<div align="center">
  <sub>Desarrollado con disciplina por el equipo de Gimnasio Campus</sub>
</div>
