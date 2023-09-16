module sunat.gob.pe.lavanderia {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;
    requires java.sql;
    
    opens sunat.gob.pe.lavanderia;
    opens sunat.gob.pe.lavanderia.controller;
    exports sunat.gob.pe.lavanderia;
}
