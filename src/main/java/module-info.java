module sunat.gob.pe.lavanderia {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;
    requires java.sql;
    requires commons.dbcp2;
    requires java.management;
    requires java.desktop;
    
    opens sunat.gob.pe.lavanderia;
    opens sunat.gob.pe.lavanderia.controller;
    exports sunat.gob.pe.lavanderia;
}
