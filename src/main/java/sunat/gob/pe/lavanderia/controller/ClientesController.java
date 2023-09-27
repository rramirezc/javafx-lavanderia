/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sunat.gob.pe.lavanderia.controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sunat.gob.pe.lavanderia.model.dao.IClientesDao;
import sunat.gob.pe.lavanderia.model.dao.impl.ClientesDaoImpl;
import sunat.gob.pe.lavanderia.model.entities.Clientes;

/**
 * FXML Controller class
 *
 * @author ovelarde
 */
public class ClientesController implements Initializable {

    @FXML
    private TableView<Clientes> clientesTable;
    //@FXML
    //private TableColumn<Clientes, String> tipoDocumentoColumn;
    @FXML
    private TableColumn<Clientes, String> numDocColumn;
    @FXML
    private TableColumn<Clientes, String> nombreColumn;
    @FXML
    private TableColumn<Clientes, String> apellidoColumn;
    @FXML
    private TableColumn<Clientes, String> telefonoColumn;
    @FXML
    private TableColumn<Clientes, String> emailColumn;
    @FXML
    private TableColumn<Clientes, String> sexoColumn;
    @FXML
    private TableColumn<Clientes, String> direccionColumn;
    @FXML
    private TableColumn<Clientes, Date> fechaNacimientoColumn;
    //private TableColumn<Clientes, String> fechaNacimientoColumn;
    //@FXML
   // private TextField txtTipoDocumento;
    @FXML
    private TextField txtNombres;
    @FXML
    private DatePicker dateFechaNacimiento;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtSexo;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtNumeroDocumento;
    @FXML
    private Button btnGuardar;

    private ObservableList<Clientes> clientesData = FXCollections.observableArrayList();
    //Date date = Date.valueOf(LocalDate.now());
     Date date = new Date(0); // Today's date and current time
   // LocalDate localDate = dateFechaNacimiento.getValue();

    private Clientes clientesActual = new Clientes("", "", "", "",date, "", "", "", "");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        enlazarTabla();
                llenarDatosEnTabla();
        seleccionarElementosDeTabla();

    }
    public void getDate(ActionEvent event){
        LocalDate myDate = dateFechaNacimiento.getValue();
    // String myformattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        fechaNacimientoColumn.setText(myDate.toString());
    }

    private void seleccionarClientes(Clientes clientesNuevo) {

        if (clientesNuevo != null) {
            clientesActual.setTipo_documento(clientesNuevo.getTipo_documento());
            clientesActual.setNumero_documento(clientesNuevo.getNumero_documento());
            clientesActual.setNombres(clientesNuevo.getNombres());
            clientesActual.setApellidos(clientesNuevo.getApellidos());
            clientesActual.setEmail(clientesNuevo.getEmail());
            clientesActual.setSexo(clientesNuevo.getSexo());
            clientesActual.setDireccion(clientesNuevo.getDireccion());
            clientesActual.setTelefono(clientesNuevo.getTelefono());
            clientesActual.setFecha_nacimiento(clientesNuevo.getFecha_nacimiento());
            //clientesActual.setFecha_nacimiento(clientesNuevo.getFecha_nacimiento());
        } else {
            clientesActual = new Clientes("", "", "", "", date, "", "", "", "");
        }
    }

    private void enlazarTabla() {
        clientesTable.setItems(clientesData);
        //txtTipoDocumento.textProperty().bindBidirectional(clientesActual.getTipo_documento1());
        txtNombres.textProperty().bindBidirectional(clientesActual.getNombres1());
        txtApellidos.textProperty().bindBidirectional(clientesActual.getApellidos1());
        txtDireccion.textProperty().bindBidirectional(clientesActual.getDireccion1());
        txtEmail.textProperty().bindBidirectional(clientesActual.getEmail1());
        txtNumeroDocumento.textProperty().bindBidirectional(clientesActual.getNumero_documento1());
        txtSexo.textProperty().bindBidirectional(clientesActual.getSexo1());
        txtTelefono.textProperty().bindBidirectional(clientesActual.getTelefono1());
        //dateFechaNacimiento.valueProperty().bindBidirectional(clientesActual.getFecha_nacimiento1());
      //  dateFechaNacimiento.valueProperty().bindBidirectional(clientesActual.getFecha_nacimiento1());
        
        //inmueble.setFecharegistro2(((LocalDate)this.dpFecha.getValue()).toString());

       // tipoDocumentoColumn.setCellValueFactory(rowData -> rowData.getValue().getTipo_documento1());
        numDocColumn.setCellValueFactory(rowData -> rowData.getValue().getNumero_documento1());
        nombreColumn.setCellValueFactory(rowData -> rowData.getValue().getNombres1());
        apellidoColumn.setCellValueFactory(rowData -> rowData.getValue().getApellidos1());
        telefonoColumn.setCellValueFactory(rowData -> rowData.getValue().getTelefono1());
        emailColumn.setCellValueFactory(rowData -> rowData.getValue().getEmail1());
        sexoColumn.setCellValueFactory(rowData -> rowData.getValue().getSexo1());
        direccionColumn.setCellValueFactory(rowData -> rowData.getValue().getDireccion1());
        fechaNacimientoColumn.setCellValueFactory(rowData -> rowData.getValue().getFecha_nacimiento1());

    }

    private void llenarDatosEnTabla() {
        
        IClientesDao clientesDao = new ClientesDaoImpl();
        clientesData.addAll(clientesDao.listarClientes());
        
    }

    private void seleccionarElementosDeTabla() {
        clientesTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Clientes> ov, Clientes clientesAntiguo, Clientes clientesNuevo) -> {
            seleccionarClientes(clientesNuevo);
            StringBinding btnGuardarText = new StringBinding() {
                @Override
                protected String computeValue() {
                    if (clientesActual.getTipo_documento() == "") {
                        System.out.println("Entro a guardar");
                        return "Guardar";
                    }
                    System.out.println("Entro a actualizae");
                    return "Guardar";
                }
            };
            btnGuardar.textProperty().bind(btnGuardarText);
        });
    }

    public void guardarClientes(ActionEvent event) {

        if (clientesActual.getTipo_documento() == "") {
            if (clientesActual.getNombres().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese nombres", Alert.AlertType.WARNING);
                //limpiarTabla();
                return;
            }
            //if (clientesActual.getFecha_nacimiento().isEmpty()) {
              //  mostrarAlertas("Warning", "Ingrese fecha de nacimiento", Alert.AlertType.WARNING);
                //limpiarTabla();
                //return;
            //}
            if (clientesActual.getApellidos().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Apellidos", Alert.AlertType.WARNING);

                return;
            }
            if (clientesActual.getDireccion().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Dirección", Alert.AlertType.WARNING);
                return;
            }
            if (clientesActual.getEmail().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Email", Alert.AlertType.WARNING);
                return;
            }
            if (clientesActual.getNumero_documento().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Número de documento", Alert.AlertType.WARNING);
                return;
            }
            if (clientesActual.getTelefono().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Teléfono", Alert.AlertType.WARNING);
                return;
            }

            IClientesDao clientesDao = new ClientesDaoImpl();
            clientesDao.guardarClientes(clientesActual);
            mostrarAlertas("Informacion", "Se guardo exitosamente", Alert.AlertType.INFORMATION);
            limpiarTabla();
            llenarDatosEnTabla();
            
        }
    }

    private void mostrarAlertas(String header, String content, Alert.AlertType type) {
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }
    
    private void limpiarTabla() {
        clientesTable.getItems().clear();
    }
}
    

