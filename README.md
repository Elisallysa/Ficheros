# Ficheros
Exercise aimed at exploring file reading and writing in Java as well as the implementation of logic that controls the registering of users and change of password through activation codes sent to the personal E-Mail address the user used to sign up in the app.

## Descripción
Ejercicio de clase de programación que tiene como objetivo crear una aplicación diseñada con Window Builder de Java que funciona como buscador de películas y series de Netflix. Se podrá acceder a la aplicación con un mail y contraseña. Este ejercicio tiene los siguientes objetivos de aprendizaje:
- leer y escribir datos en Java (específicamente con formato .csv);
- generación de códigos de activación para el registro de usuarios en una app,
- envío de E-Mails de bienvenida y de códigos de activación,
- estructura de archivos .json,
- y hasheo de contraseñas para almacenarlas en la BD.

## Funcionalidades de la app
### Inicio de sesión y registro
1. Registro de usuarios y activación de cuenta mediante código enviado por mail.
2. Inicio de sesión con mail y contraseña.
3. Restauración de contraseña mediante código enviado por mail.

### Motor de búsqueda de películas y series
4. Búsqueda y visualización de películas y series con filtro por título de película, director, país y año de estreno.
5. Creación de archivos .csv donde se almacenan los datos de la/s película/s seleccionadas (selección múltiple con Ctrl+click). Los archivos se almacenarán con un nombre introducido por el usuario y se separan los datos con un separador también elegido por el usuario. Al título del archivo se le añade el nombre del usuario para que solo se pueda acceder a los archivos creados por el mismo usuario activo.
6. Visualización de los archivos creados por el usuario.

## Mejoras que implementar
- Implementación de un scrollbar en el JList donde se visualizan las películas y series. No se puede ver la información completa.
- En la creación de archivos de favoritos, si se cancela el proceso en el cuadro de diálogo donde se elige el separador de la información, salta una excepción que no se ha controlado.
- Hacer que el username, como la dirección de E-Mail, sea único en la BD, para que a la hora de crear documentos, el usuario no pueda acceder a los archivos de otro usuario que tiene el mismo nombre.
- Conseguir que en un archivo de favoritos con tabulador como separador se pueda visualizar en la vista de búsqueda (si se abre con otro editor de texto sí aparece la tabulación correctamente)
