/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaVendas.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemaVendas.model.dao.ClienteDAO;
import sistemaVendas.model.database.Database;
import sistemaVendas.model.database.DatabaseFactory;
import sistemaVendas.model.database.DatabaseSQLServer;
import sistemaVendas.model.domain.Cliente;

/**
 * FXML Controller class
 *
 * @author Rui
 */
public class FXMLInscricaoClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNif;
    @FXML
    private Label labelClienteCodigo;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteNif;
    @FXML
    private Label labelClienteTelefone;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    
    private final Database database = DatabaseFactory.getDatabase("SQLServer");
    private final Connection conn = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(conn);
        carregarTableViewClientes();
        
    }   
    
    public void carregarTableViewClientes(){
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteNif.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        listClientes = clienteDAO.listar();
        
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableViewClientes.setItems(observableListClientes);
    }
    
}
