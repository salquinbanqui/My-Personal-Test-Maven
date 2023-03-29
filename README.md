# PSC Peliculas

PSC peliculas es una app la cual el cliente podrá comprar peliculas para poder verlas. Además, tambien podra añadir amigos y ver sus perfiles.


## Pre-requisitos

Para la ejecución de PSC Peliculas es necesario tener los siguientes programas correctamente instalados y configurados:

- [Java JDK 17] https://openjdk.org/projects/jdk/17/
- [Apache Maven (bin)] https://maven.apache.org/download.cgi
- [MySQL Workbench] https://dev.mysql.com/downloads/workbench/



## Instalación y ejecución

Para ejecutar el proyecto hay que ejecutar el cliente y el servidor.

1-Para ejecutar el servidor los pasos son:
-"mvn clean"
-"mvn compile"

***
Si es la primera vez que se hace este proceso se haría:
-"mvn datanucleus:enhance"
-"mvn datanucleus:schema-create"
***

-"mvn exec:java-PServer"

2-Para ejecutar el cliente:
-"mvn exec:java-PClient"



## Autores

Jose Javier Garcia -- josejavier.garcia@opendeusto.es
Ignacio Echavarren -- ignacioechavarren@opendeusto.es
Yon Ander Escobal  -- yonander.escobal@opendeusto.es
Marcos Lopez       -- m.lopez.gutierrez@opendeusto.es



