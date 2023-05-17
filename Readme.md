# Sistema de Gestión de Historias Clínicas (RESTful API)

Este proyecto es un sistema RESTful de gestión de historias clínicas que permite a los usuarios gestionar pacientes, historias clínicas y archivos relacionados. Los archivos se almacenan en AWS S3 para garantizar la escalabilidad y la durabilidad.


## Características

- Gestión de pacientes: Crear, leer, actualizar y eliminar (CRUD) pacientes.
- Gestión de historias clínicas: Crear, leer, actualizar y eliminar (CRUD) historias clínicas. 
- Gestión de actualizaciones: Crear, leer, actualizar y eliminar (CRUD) actualizaciones de las historias clínicas.
- Gestión de archivos: Subir, descargar y eliminar archivos relacionados con una historia clínica.
- Acceso diferencial mediante JWT: Rol Secretaria, Médico, Administrador y User.

## Requisitos

- Java 11+
- Spring Boot 2.4.5
- AWS Account (para almacenamiento de archivos)
- PostgreSQL (para almacenamiento de metadatos)

## Configuración

1. Clona este repositorio en tu máquina local:

    ```
    git clone https://github.com/Eveprgrama/HistoriaClinicasBack.git
    cd HistoriaClinicasBack
    ```

2. Configura las variables de entorno para las credenciales de AWS:

    ```
    export AWS_ACCESS_KEY_ID=your_access_key
    export AWS_SECRET_ACCESS_KEY=your_secret_access_key
    ```

3. Configura la conexión a tu base de datos PostgreSQL en `application.properties`:

    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

4. Ejecuta la aplicación con Maven:

    ```
    ./mvnw spring-boot:run
    ```

La aplicación estará disponible en `http://localhost:8080`.

## Uso

- **Gestión de pacientes**: Las operaciones CRUD para pacientes están disponibles en los endpoints `/pacientes`.
- **Gestión de historias clínicas**: Las operaciones CRUD para historias clínicas están disponibles en los endpoints `/historiasClinicas`.
- **Gestión de actualizaciones**: Las operaciones CRUD para Actualizaciones están disponibles en los endpoints `/actualizaciones`.
- **Gestión de archivos**:
    - **Subir un archivo**: realiza una solicitud POST a `http://localhost:8080/archivos/{historiaClinicaId}` con el archivo que quieres subir en el cuerpo de la solicitud.
    - **Descargar un archivo**: realiza una solicitud GET a `http://localhost:8080/archivos/{archivoId}`.
    - **Eliminar un archivo**: realiza una solicitud DELETE a `http://localhost:8080/archivos/{archivoId}`.

# Autenticación con JWT

1. Registra un nuevo usuario a través del endpoint /auth/nuevo, proporcionando un objeto JSON con el nombre, nombre de usuario, correo electrónico, contraseña y roles. 

2. Inicia sesión con las credenciales del usuario registrado en el endpoint /auth/login, proporcionando un objeto JSON con el nombre de usuario y la contraseña.

3. Al iniciar sesión correctamente, el servidor devuelve un JWT como parte de la respuesta. Este token debe incluirse en el encabezado Authorization de las solicitudes subsiguientes que requieran autenticación y autorización.

4. El servidor valida el JWT en cada solicitud que requiere autenticación y autorización. Si el token es válido, se procesa la solicitud. En caso contrario, se devuelve un error de autenticación.

5. Los roles de usuario se almacenan en la base de datos y se utilizan para la autorización en el sistema. Los roles disponibles son ROLE_ADMIN, ROLE_USER, ROLE_MEDICO y ROLE_SECRETARIA. Asegúrate de asignar los roles apropiados a los usuarios durante el registro.

## Licencia

Este proyecto está bajo la licencia MIT.
