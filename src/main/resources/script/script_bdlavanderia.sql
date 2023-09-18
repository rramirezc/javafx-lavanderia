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
password VARCHAR(30) NOT NULL,
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

INSERT INTO TIPO_DOCUMENTO(tipo_documento,descripcion_corta,descripcion_larga)VALUES
('01','LIBRETA ELECTORAL O DNI','L.E / DNI'),
('04','CARNET DE EXTRANJERIA','CARNET EXT.'),
('06','REG. UNICO DE CONTRIBUYENTES','RUC'),
('07','PASAPORTE','PASAPORTE'),
('11','PART. DE NACIMIENTO-IDENTIDAD','P. NAC.'),
('00','OTROS','OTROS');