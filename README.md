# Sistema de Registro de Personas

Aplicación web desarrollada en Java para registrar y administrar
información de personas mediante una interfaz web y una base de datos
SQLite local.

El proyecto fue desarrollado como parte de una actividad académica
relacionada con la creación y utilización de clases y objetos en Java,
y posteriormente se extendió para implementar operaciones CRUD,
persistencia de datos y una interfaz web.

---

## 🚀 Funcionalidades

La aplicación permite:

- Registrar personas.
- Consultar personas registradas.
- Editar información.
- Eliminar personas.
- Validar los datos introducidos.
- Mostrar mensajes de operación.
- Mantener la información almacenada después de reiniciar la aplicación.

Las operaciones CRUD utilizadas son:

| Operación | Método HTTP |
|-----------|-------------|
| Crear | `POST` |
| Consultar | `GET` |
| Actualizar | `PUT` |
| Eliminar | `DELETE` |

---

## 🛠️ Tecnologías utilizadas

- **Java 25**
- **Maven**
- **SQLite**
- **JDBC**
- **HTML5**
- **CSS3**
- **JavaScript**
- **HTTP**

---

## 📁 Estructura del proyecto

```text
personas-java/
├── src/
│   └── main/
│       └── java/
│           ├── Persona.java
│           ├── Database.java
│           ├── Server.java
│           └── Main.java
│
├── web/
│   ├── index.html
│   ├── style.css
│   └── script.js
│
├── database/
│   └── personas.db
│
├── Capturas/
│   └── Evidencia_5_registros.png
│
├── pom.xml
├── requisitos.md
├── .gitignore
└── README.md
```
---

## 🧩 Organización de las clases

### `Persona.java`

Representa una persona dentro de la aplicación.

Contiene los atributos:

- `id`

- `nombre`

- `apellido`

- `edad`

También proporciona constructores, getters y setters para trabajar con

los objetos `Persona`.

### `Database.java`

Se encarga de la comunicación con la base de datos SQLite.

Sus responsabilidades principales son:

- Crear la tabla `personas`.

- Insertar personas.

- Consultar personas.

- Actualizar personas.

- Eliminar personas.

### `Server.java`

Implementa el servidor HTTP de la aplicación.

Se encarga de:

- Servir la interfaz web.

- Recibir solicitudes HTTP.

- Procesar las operaciones sobre personas.

- Comunicarse con `Database`.

- Devolver respuestas al navegador.

### `Main.java`

Es el punto de entrada de la aplicación.

Se encarga de inicializar la base de datos y arrancar el servidor.

---

## 🗄️ Base de datos

El proyecto utiliza **SQLite** como base de datos local.

El archivo se encuentra en:

```text
database/personas.db
```

La tabla principal es:

```text
personas
```

con los siguientes campos:

| Campo | Tipo | Descripción |
| --- | --- | --- |
| `id` | INTEGER | Identificador único |
| `nombre` | TEXT | Nombre de la persona |
| `apellido` | TEXT | Apellido de la persona |
| `edad` | INTEGER | Edad de la persona |

---

## ▶️ Ejecución

### Requisitos

Se requiere tener instalado:

- Java 25

- Maven

Para comprobar las versiones:

```bash
java -version

mvn -version
```

### 1. Compilar el proyecto

Desde la raíz del proyecto:

```bash
mvn compile
```

### 2. Generar el classpath de las dependencias

El archivo `cp.txt` utilizado durante el desarrollo no forma parte del

repositorio, ya que es un archivo generado.

Para generar nuevamente el classpath:

```bash
mvn dependency:build-classpath -Dmdep.outputFile=cp.txt
```

### 3. Ejecutar la aplicación

Una vez compilado el proyecto y generado el classpath:

```bash
java -cp "target/classes:$(cat cp.txt)" Main
```

Si la aplicación inicia correctamente, se mostrará:

```text
Servidor iniciado en http://localhost:8080
```

### 4. Abrir la aplicación

Acceder desde el navegador a:

```text
http://localhost:8080
```

---

## 🔄 Operaciones CRUD

La aplicación implementa las operaciones básicas de persistencia:

### Crear

Permite registrar una nueva persona mediante el formulario web.

```text
POST /personas
```

### Consultar

Permite obtener las personas almacenadas en la base de datos.

```text
GET /personas
```

### Actualizar

Permite modificar los datos de una persona existente.

```text
PUT /personas/{id}
```

### Eliminar

Permite eliminar una persona existente.

```text
DELETE /personas/{id}
```

---

## 🧪 Validaciones

La interfaz web valida que los datos necesarios hayan sido introducidos

antes de enviar las operaciones al servidor.

Entre las validaciones implementadas se encuentran:

- Nombre obligatorio.

- Apellido obligatorio.

- Edad obligatoria.

- Edad mayor que cero.

- Eliminación y actualización mediante el identificador de la persona.

La aplicación también muestra mensajes al usuario para indicar el

resultado de las operaciones.

---

## 📸 Evidencia

El proyecto incluye una captura de pantalla que demuestra el registro y

visualización de cinco personas:

```text
Capturas/Evidencia_5_registros.png
```

Esta captura corresponde a uno de los requisitos de la actividad

académica.

---

## 📋 Documentación

Los requisitos y el alcance del proyecto se encuentran documentados en:

```text
requisitos.md
```

Este documento contiene:

- Requisitos académicos.

- Requisitos funcionales.

- Requisitos técnicos.

- Estructura del proyecto.

- Restricciones y alcance.

- Resultado esperado.

- Evidencias.

---

## 🎓 Contexto académico

El proyecto tiene como objetivo demostrar el uso de:

- Clases.

- Objetos.

- Constructores.

- Encapsulamiento.

- Métodos.

- Separación de responsabilidades.

- Persistencia de información.

La implementación fue extendida con una interfaz web y una base de datos

local para complementar los objetivos de la actividad.

---

## 👤 Autor

**Raúl Rojas López**

Proyecto académico — Sistema de Registro de Personas.
