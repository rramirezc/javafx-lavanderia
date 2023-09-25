module sunat.gob.pe.lavanderia {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;
    requires java.sql;
    requires commons.dbcp2;
    requires java.management;
    requires java.desktop;
    requires AnimateFX;
    requires java.mail;
    
    opens sunat.gob.pe.lavanderia;
    opens sunat.gob.pe.lavanderia.controller;
    opens sunat.gob.pe.lavanderia.controller.service;
    exports sunat.gob.pe.lavanderia;
}
