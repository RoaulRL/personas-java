# Sistema de Registro de Personas

## 1. Descripción

El proyecto consiste en implementar un sistema para registrar y administrar
información de personas utilizando el lenguaje de programación Java.

El proyecto fue desarrollado como parte de una actividad académica
relacionada con la creación y utilización de clases y objetos en Java.

Como extensión de la actividad, se desarrolló una aplicación web sencilla
que permite registrar, consultar, editar y eliminar personas mediante una
interfaz web.

La información se almacena de forma persistente en una base de datos
SQLite local.

---

## 2. Objetivo

Implementar y demostrar el uso de clases y objetos en Java mediante el
registro y administración de información de personas.

Como extensión de la actividad académica, el proyecto integra:

- Una aplicación Java.
- Una base de datos SQLite.
- Un servidor HTTP.
- Una interfaz web.
- Operaciones CRUD sobre las personas registradas.

El objetivo es demostrar la separación de responsabilidades entre las
diferentes clases que componen la aplicación y el uso de una base de datos
para conservar la información.

---

## 3. Requisitos académicos

### RA-01 — Implementación de la clase Persona

El proyecto deberá implementar una clase `Persona` para representar la
información de una persona.

La clase contiene los siguientes atributos:

- `id`
- `nombre`
- `apellido`
- `edad`

La clase utiliza constructores para inicializar objetos y métodos de
acceso (getters y setters) para consultar y modificar sus atributos.

### RA-02 — Creación de objetos

El proyecto deberá crear objetos utilizando la clase `Persona`.

Los objetos `Persona` son utilizados para representar los datos capturados
desde la interfaz web y los registros recuperados desde la base de datos.

### RA-03 — Captura de información

El sistema deberá permitir capturar la información correspondiente a una
persona mediante un formulario web:

- Nombre.
- Apellido.
- Edad.

### RA-04 — Uso de métodos

El proyecto deberá utilizar los métodos definidos en la clase `Persona`
para acceder y modificar la información de los objetos.

### RA-05 — Registro de cinco personas

El proyecto deberá permitir registrar cinco personas como evidencia del
funcionamiento de las clases, objetos y almacenamiento de información.

### RA-06 — Evidencia

Se deberán generar capturas de pantalla que demuestren el registro y
visualización de las cinco personas.

---

## 4. Requisitos funcionales

Los siguientes requisitos corresponden a las funcionalidades implementadas
en el proyecto para complementar la actividad académica.

### RF-01 — Registro de personas

El sistema deberá proporcionar una interfaz web que permita registrar una
persona.

### RF-02 — Captura de datos

El formulario web deberá permitir ingresar:

- Nombre.
- Apellido.
- Edad.

Los campos deberán validarse antes de enviar la información al servidor.

### RF-03 — Creación de objetos Persona

Los datos proporcionados mediante la interfaz web deberán utilizarse para
crear objetos de la clase `Persona`.

### RF-04 — Almacenamiento

El sistema deberá almacenar la información de las personas en una base de
datos SQLite local.

### RF-05 — Consulta de personas

El sistema deberá permitir consultar las personas almacenadas en la base
de datos.

### RF-06 — Visualización

El sistema deberá mostrar las personas registradas mediante una interfaz
web.

### RF-07 — Persistencia

La información registrada deberá permanecer almacenada después de cerrar
y volver a ejecutar la aplicación.

### RF-08 — Edición de personas

El sistema deberá permitir modificar los datos de una persona previamente
registrada.

La actualización deberá modificar el registro correspondiente en la base
de datos.

### RF-09 — Eliminación de personas

El sistema deberá permitir eliminar una persona previamente registrada.

La eliminación deberá realizarse sobre el registro correspondiente en la
base de datos.

### RF-10 — Mensajes de operación

El sistema deberá mostrar mensajes al usuario para indicar el resultado
de las operaciones principales, incluyendo:

- Registro exitoso.
- Actualización exitosa.
- Eliminación exitosa.
- Errores durante las operaciones.

---

## 5. Requisitos técnicos

### RT-01 — Lenguaje de programación

La lógica principal del proyecto deberá implementarse utilizando Java.

### RT-02 — Clases Java

El proyecto deberá utilizar clases independientes para separar las
responsabilidades principales del sistema.

Las clases utilizadas son:

- `Persona.java` — Representa la información de una persona.
- `Database.java` — Gestiona la conexión con SQLite y las operaciones
  sobre la base de datos.
- `Server.java` — Gestiona el servidor HTTP y las solicitudes de la
  aplicación web.
- `Main.java` — Punto de entrada de la aplicación e inicialización de los
  componentes principales.

### RT-03 — Interfaz web

La interfaz web utilizará:

- HTML para la estructura.
- CSS para la presentación visual.
- JavaScript para la interacción con el usuario y la comunicación con
  el servidor Java.

### RT-04 — Base de datos

Se utilizará SQLite como sistema de base de datos local.

La base de datos contiene una tabla denominada `personas` con los campos:

- `id`
- `nombre`
- `apellido`
- `edad`

### RT-05 — Comunicación entre frontend y backend

La interfaz web se comunicará con el servidor Java mediante solicitudes
HTTP.

Las operaciones principales utilizan los siguientes métodos:

- `GET` — Consultar personas.
- `POST` — Registrar una persona.
- `PUT` — Actualizar una persona.
- `DELETE` — Eliminar una persona.

### RT-06 — Persistencia

La información deberá almacenarse físicamente en el archivo local:

`database/personas.db`

### RT-07 — Gestión del proyecto

El proyecto utiliza Maven para la gestión de compilación y dependencias.

La configuración del proyecto se encuentra en:

`pom.xml`

### RT-08 — Documentación

El proyecto deberá incluir documentación sobre:

- Objetivo del proyecto.
- Requisitos.
- Estructura del proyecto.
- Clases utilizadas.
- Base de datos.
- Procedimiento de ejecución.
- Evidencias de funcionamiento.

---

## 6. Estructura del proyecto

La estructura principal del proyecto es:

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
