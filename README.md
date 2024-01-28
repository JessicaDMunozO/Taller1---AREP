# Taller 1 - AREP
Para este taller se implementó un servidor web en Java que, recibe solicitudes relacionadas con el nombre de una película, con el fin de mostrar la información correspondiente a dicha búsqueda. Los datos de la película fueron obtenidos del API gratuito de OMDb. Además, cuenta con un Caché para evitar hacer consultas repetidas al API externo.
## Empezando
Las siguientes instrucciones permiten que obtenga una copia del proyecto en funcionamiento.
### Prerrequisitos
1. Debe contar con un IDE (entorno de desarrollo integrado) para facilitar la ejecución del código.
2. Debe tener Maven y JDK para compilar y ejecutar el proyecto.
3. Verificar que el puerto 35000 esté disponible para que el servidor web lo pueda usar sin inconvenientes.
4. Tener conexión a internet.
### Instalación
Ahora bien, para obtener el proyecto y ejecutarlo debe ser descargado en formato zip o puede ser clonado desde el repositorio de GitHub. Con el proyecto en su máquina, en su IDE de preferencia, debe seleccionar el directorio que contiene el proyecto y abrirlo. Luego, desde la terminal, debe descargar las dependencias del proyecto, para esto ejecute el comando `mvn install`. Después, para compilar ejecute el comando `mvn package` y por último ejecute el comando `java -cp .\target\classes\ edu.escuelaing.arem.ASE.app.HttpServer`.
## Despliegue
Con el proyecto corriendo debe abrir en un navegador la sigueinte dirección: http://localhost:35000/ allí podrá ingresar el nombre de la película sobre la cual quiere obtener información. Para esto, escriba el nombre y de clic en el botón de *Submit*. Con esto usted podrá ver la información encontrada para la pélicula que ingresó.
## Diseño
Se tiene una clase HttpServer que escucha por el puerto 35000, este servidor procesa solicitudes GET, cuyos parámetros se utilizan para obtener el nombre de la pélicula de la solicitud. Con dicho nombre, imprime el resultado de la búsqueda de la información para esa película. Lo cual se logra por medio de otra clase llamada ApiMovie, en la que se establece una connexión con la URL de la API externa de OMDb que es la que brinda la información de las películas por medio de solicitudes GET, esta conexión se realiza por medio de la clase HttpConnection. Por su parte se maneja un caché, cuya estructura de datos es un HashMap del nombre de la película asociado a la información de la misma, con el fin de almacenar los datos de películas que ya hayan sido consultadas para evitar hacer consultas repetidas a la API externa. Como posibles extensiones se podría realizar un manejo de errores más completo y se podría modificar el diseño para que pueda trabajar con otras API, no necesariamente con la de OMDb, se podría añadir otro parámetro que indique la API a utilizar en el método de searchMovieInformation para que dependiendo de la API realice un comportamiento diferente.
## Construido con
Maven - Gestión de dependencias
## Versiones
Git - Control de versiones
## Autor
Jessica Daniela Muñoz Ossa
