# PSC Peliculas

PSC PELICULAS es una app en la que el cliente podrá comprar películas y así añadirlas a la biblioteca. El usuario podrá añadir amigos que también tengan su cuenta creada dentro de la aplicación. Los usuarios podrán añadir dinero a sus cuentas, de esta manera podrán comprar las películas. La parde administrador será la encargada de añadir o eliminar películas al sistema, así como gestionar los usuarios.


## Pre-requisitos

Para la ejecución de PSC Peliculas es necesario tener los siguientes programas correctamente instalados y configurados:

- [Java JDK 17] https://openjdk.org/projects/jdk/17/
- [Apache Maven (bin)] https://maven.apache.org/download.cgi
- [MySQL Workbench] https://dev.mysql.com/downloads/workbench/



## Instalación y ejecución

Para ejecutar el proyecto hay que ejecutar el cliente y el servidor con los siguientes comandos.

<h2> Para ejecutar el servidor los pasos son: </h2>
<h3> El primer comando  </h3>

    mvn clean
    
<h3> El segundo comando  </h3>

    mvn compile

<h3> El comando para realizar los test  </h3>

    mvn test
    
<h3> Despues hariamos los comando del datanucleus</h3>

    mvn datanucleus:enhance
    
<h3>  El siguiente comando del datanucleus: </h3>

    mvn datanucleus:schema-create
    

<h2> Para ejecutar el server: </h2>

    mvn exec:java -PServer
 
<h2> Para ejecutar el cliente: </h2>

    mvn exec:java -PClient 


## Autores

 * Jose Javier Garcia -- josejavier.garcia@opendeusto.es
 * Ignacio Echavarren -- ignacioechavarren@opendeusto.es
 * Yon Ander Escobal  -- yonander.escobal@opendeusto.es
 * Marcos Lopez       -- m.lopez.gutierrez@opendeusto.es
 * Samuel Martin      -- samuel.martin@opendeusto.es


