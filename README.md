# PSC Peliculas

PSC PELICULAS es una app en la que el cliente podrá comprar películas y así añadirlas a la biblioteca. El usuario podrá añadir amigos que también tengan su cuenta creada dentro de la aplicación. Los usuarios podrán añadir dinero a sus cuentas, de esta manera podrán comprar las películas. La parde administrador será la encargada de añadir o eliminar películas al sistema, así como gestionar los usuarios.


## Pre-requisitos

Para la ejecución de PSC Peliculas es necesario tener los siguientes programas correctamente instalados y configurados:

- [Java JDK 17] https://openjdk.org/projects/jdk/17/
- [Apache Maven (bin)] https://maven.apache.org/download.cgi
- [MySQL Workbench] https://dev.mysql.com/downloads/workbench/



## Instalación y ejecución

Para ejecutar el proyecto hay que ejecutar el cliente y el servidor.

1-Para ejecutar el servidor los pasos son:
 * "mvn clean"
 * "mvn compile"

***
Si es la primera vez que se hace este proceso se haría:
 * "mvn datanucleus:enhance"
 * "mvn datanucleus:schema-create"
***

 * "mvn exec:java-PServer"

2-Para ejecutar el cliente:
* "mvn exec:java-PClient"



## Autores

 * Jose Javier Garcia -- josejavier.garcia@opendeusto.es
 * Ignacio Echavarren -- ignacioechavarren@opendeusto.es
 * Yon Ander Escobal  -- yonander.escobal@opendeusto.es
 * Marcos Lopez       -- m.lopez.gutierrez@opendeusto.es
 * Samuel Martin      -- samuel.martin@opendeusto.es



