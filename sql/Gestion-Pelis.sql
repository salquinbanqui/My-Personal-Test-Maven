#CREATE TABLE ""."USUARIOS" (
#	"ID"	INTEGER,
#	"USERNAME"	TEXT NOT NULL UNIQUE,
#	"EMAIL"	TEXT,
#	"PASSWORD"	TEXT,
#	"CARD"	TEXT,
#	PRIMARY KEY("ID" AUTOINCREMENT)
#);

/* DELETE 'GestionPelis' database*/
DROP SCHEMA IF EXISTS GestionPelis;
/* DELETE USER 'admin' AT LOCAL SERVER*/
DROP USER IF EXISTS 'admin'@'localhost';

/* CREATE 'GestionPelis' DATABASE */
CREATE SCHEMA GestionPelis;
/* CREATE THE USER 'admin' AT LOCAL SERVER WITH PASSWORD 'administrador' */
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'administrador';

GRANT ALL ON GestionPelis.* TO 'admin'@'localhost';
