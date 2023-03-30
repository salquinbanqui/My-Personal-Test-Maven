#CREATE TABLE ""."USUARIOS" (
#	"ID"	INTEGER,
#	"USERNAME"	TEXT NOT NULL UNIQUE,
#	"EMAIL"	TEXT,
#	"PASSWORD"	TEXT,
#	"CARD"	TEXT,
#	PRIMARY KEY("ID" AUTOINCREMENT)
#);

/* DELETE 'gestionpelis' database*/
DROP SCHEMA IF EXISTS psc_gestionpelis;
/* DELETE USER 'root' AT LOCAL SERVER*/
DROP USER IF EXISTS 'root'@'localhost';

/* CREATE 'gestionpelis' DATABASE */
CREATE SCHEMA psc_gestionpelis;
/* CREATE THE USER 'root' AT LOCAL SERVER WITH PASSWORD 'admin' */
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'administrador';

GRANT ALL ON psc_gestionpelis.* TO 'root'@'localhost';
