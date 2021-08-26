/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaVendas.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    
    //Listas
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    
    //Atributos Base Dados
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
        
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
        
    }   
    
    public void carregarTableViewClientes(){
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteNif.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        listClientes = clienteDAO.listar();
        
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tableViewClientes.setItems(observableListClientes);
    }
    
    public void selecionarItemTableViewClientes(Cliente cliente){
        if(cliente != null){
            labelClienteCodigo.setText(String.valueOf(cliente.getCdCliente()));
            labelClienteNome.setText(cliente.getNome());
            labelClienteNif.setText(cliente.getCpf());
            labelClienteTelefone.setText(cliente.getTelefone());
        }else{
            labelClienteCodigo.setText("");
            labelClienteNome.setText("");
            labelClienteNif.setText("");
            labelClienteTelefone.setText("");
        }
       
    }
    
    @FXML
    public void handleButtonInserir() throws IOException{
        Cliente cliente = new Cliente();
        
        boolean buttonConfirmarClicked = showFXMLInscricaoClienteConfirmar(cliente);
        if(buttonConfirmarClicked){
            clienteDAO.inserir(cliente);
            carregarTableViewClientes();
        }      
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException{
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        
        if(cliente != null){
            boolean buttonConfirmarClicked = showFXMLInscricaoClienteConfirmar(cliente);
            if(buttonConfirmarClicked){
                clienteDAO.alterar(cliente);
                carregarTableViewClientes();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please, select a cliente from the table to alter");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonRemover() throws IOException{
        Cliente cliente = tableViewClientes.getSelectionModel().getSelectedItem();
        
        if(cliente != null){
                clienteDAO.remover(cliente);
                carregarTableViewClientes();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please, select a client from the table to remove");
            alert.show();
        }
    }

    public boolean showFXMLInscricaoClienteConfirmar(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLInscricaoClientesController.class.getResource("/sistemaVendas/view/FXMLInscricaoClientesDialog.fxml"));        
        AnchorPane page = (AnchorPane) loader.load();
        
        //Criar Stage Dialog
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Inscricao de Cliente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Set do Cliente no Controller
        FXMLInscricaoClientesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);
        
        //Mostrar Dialog até user fechar
        dialogStage.showAndWait();
               
        return controller.isBtnConfirmarClicked();
    }
    
}
