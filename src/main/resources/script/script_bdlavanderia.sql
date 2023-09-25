CREATE DATABASE bdlavanderia;

USE bdlavanderia;

CREATE TABLE TIPO_DOCUMENTO(
tipo_documento CHAR(2) NOT NULL,
descripcion_corta VARCHAR(30) NOT NULL,
descripcion_larga VARCHAR(100) NOT NULL,
CONSTRAINT tipo_documento_pk PRIMARY KEY (tipo_documento)
);

CREATE TABLE USUARIO (
id_usuario BIGINT auto_increment NOT NULL,
nombres VARCHAR(100) NOT NULL,
apellidos VARCHAR(100) NOT NULL,
usuario VARCHAR(30) NOT NULL,
password BLOB NOT NULL,
telefono VARCHAR(20) NULL,
email VARCHAR(100) NULL,
CONSTRAINT usuario_pk PRIMARY KEY (id_usuario)
);


CREATE TABLE CLIENTE (
tipo_documento CHAR(2) NOT NULL,
numero_documento VARCHAR(15) NOT NULL,
nombres VARCHAR(100) NOT NULL,
apellidos VARCHAR(100) NOT NULL,
fecha_nacimiento DATE,
sexo CHAR(1) NOT NULL,
telefono VARCHAR(20) NULL,
email VARCHAR(100) NULL,
direccion VARCHAR(100) NULL,
CONSTRAINT cliente_pk PRIMARY KEY (tipo_documento,numero_documento),
CONSTRAINT tipo_documento_fk FOREIGN KEY (tipo_documento) REFERENCES TIPO_DOCUMENTO(tipo_documento)
);

CREATE TABLE TIPO_PRENDA(
id_tipo_prenda BIGINT auto_increment NOT NULL,
descripcion VARCHAR(100) NOT NULL,
precio DECIMAL(5,2) NOT NULL,
CONSTRAINT tipo_prenda_pk PRIMARY KEY (id_tipo_prenda)
);

CREATE TABLE SOLICITUD(
id_solicitud BIGINT auto_increment NOT NULL,
tipo_documento CHAR(2) NOT NULL,
numero_documento VARCHAR(15) NOT NULL,
id_usuario BIGINT NOT NULL,
id_tipo_prenda BIGINT NOT NULL,
cantidad_prendas INT,
peso DECIMAL(5,2) NOT NULL,
precio_total DECIMAL(6,2) NOT NULL,
fecha_solicitud DATETIME NOT NULL,
fecha_entrega DATETIME NULL,
CONSTRAINT solicitud_pk PRIMARY KEY (id_solicitud),
CONSTRAINT cliente_fk FOREIGN KEY (tipo_documento,numero_documento) REFERENCES CLIENTE(tipo_documento,numero_documento),
CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
CONSTRAINT tipo_prenda_fk FOREIGN KEY (id_tipo_prenda) REFERENCES TIPO_PRENDA(id_tipo_prenda)
);

INSERT INTO TIPO_DOCUMENTO(tipo_documento,descripcion_larga,descripcion_corta)VALUES
('01','LIBRETA ELECTORAL O DNI','L.E / DNI'),
('04','CARNET DE EXTRANJERIA','CARNET EXT.'),
('06','REG. UNICO DE CONTRIBUYENTES','RUC'),
('07','PASAPORTE','PASAPORTE'),
('11','PART. DE NACIMIENTO-IDENTIDAD','P. NAC.'),
('00','OTROS','OTROS');

INSERT INTO USUARIO(id_usuario,nombres,apellidos,usuario,password,telefono,email)
VALUES
(0,'Juan Alberto','Perez Gonzales','jperez',aes_encrypt('Clave@125' ,'keyLavadanderia'),'925999999','jperez@lavanderia.com.pe');

INSERT INTO USUARIO(id_usuario,nombres,apellidos,usuario,password,telefono,email)
VALUES
(0,'Cristhian','Estrada Mori','cestrada',aes_encrypt('123' ,'keyLavadanderia'),'925999999','cestrada@lavanderia.com.pe');


INSERT INTO TIPO_PRENDA (id_tipo_prenda,descripcion,precio) VALUES (0,"Algodón",100.00);
INSERT INTO TIPO_PRENDA (id_tipo_prenda,descripcion,precio) VALUES (0,"Lino",200.00);
INSERT INTO TIPO_PRENDA (id_tipo_prenda,descripcion,precio) VALUES (0,"Lana",300.00);
INSERT INTO TIPO_PRENDA (id_tipo_prenda,descripcion,precio) VALUES (0,"Seda",400.00);
INSERT INTO TIPO_PRENDA (id_tipo_prenda,descripcion,precio) VALUES (0,"Fibras orgánicas",500.00);
INSERT INTO TIPO_PRENDA (id_tipo_prenda,descripcion,precio) VALUES (0,"Poliéster y nylon",600.00);
INSERT INTO TIPO_PRENDA (id_tipo_prenda,descripcion,precio) VALUES (0,"Látex o spándex",700.00);
INSERT INTO TIPO_PRENDA (id_tipo_prenda,descripcion,precio) VALUES (0,"Acetato, rayón y viscosa",800.00);
INSERT INTO TIPO_PRENDA (id_tipo_prenda,descripcion,precio) VALUES (0,"Acrílico",900.00);

insert into CLIENTE (tipo_documento,numero_documento,nombres,apellidos,fecha_nacimiento,sexo,telefono,email,direccion)
values ('01',44508872,'Cristhian','Estrada',STR_TO_DATE('1987-07-17', '%Y-%m-%d'),'M',123456789,'cestradam@gmail.com','Direccion 1');
insert into CLIENTE (tipo_documento,numero_documento,nombres,apellidos,fecha_nacimiento,sexo,telefono,email,direccion)
values ('07',12345678,'Fulano','Mengano',STR_TO_DATE('1987-07-17', '%Y-%m-%d'),'M',123456789,'cestradam@gmail.com','Direccion 1');
insert into CLIENTE (tipo_documento,numero_documento,nombres,apellidos,fecha_nacimiento,sexo,telefono,email,direccion)
values ('04',77777777,'Bob','Esponja',STR_TO_DATE('1987-07-17', '%Y-%m-%d'),'M',123456789,'cestradam@gmail.com','Direccion 1');



insert into SOLICITUD (id_solicitud,tipo_documento,numero_documento,id_usuario,id_tipo_prenda,cantidad_prendas,peso,precio_total,fecha_solicitud,fecha_entrega)
Values (0,'01',44508872,2,3,4,100,1200,STR_TO_DATE('2023-08-23', '%Y-%m-%d'),STR_TO_DATE('2023-10-23', '%Y-%m-%d'));

insert into SOLICITUD (id_solicitud,tipo_documento,numero_documento,id_usuario,id_tipo_prenda,cantidad_prendas,peso,precio_total,fecha_solicitud,fecha_entrega)
Values (0,'07',12345678,2,3,6,200,4000,STR_TO_DATE('2023-08-23', '%Y-%m-%d'),STR_TO_DATE('2023-10-23', '%Y-%m-%d'));

insert into SOLICITUD (id_solicitud,tipo_documento,numero_documento,id_usuario,id_tipo_prenda,cantidad_prendas,peso,precio_total,fecha_solicitud,fecha_entrega)
Values (0,'04',77777777,2,3,11,400,1200,STR_TO_DATE('2023-08-23', '%Y-%m-%d'),STR_TO_DATE('2023-10-23', '%Y-%m-%d'));
