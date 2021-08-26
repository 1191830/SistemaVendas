/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaVendas.controller;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import sistemaVendas.model.dao.ItemDeVendaDAO;
import sistemaVendas.model.dao.ProdutoDAO;
import sistemaVendas.model.dao.VendaDAO;
import sistemaVendas.model.database.Database;
import sistemaVendas.model.database.DatabaseFactory;
import sistemaVendas.model.domain.Venda;

/**
 * FXML Controller class
 *
 * @author Rui
 */
public class FXMLVendasController implements Initializable {

    @FXML
    private TableView<Venda> tableViewVendas;
    @FXML
    private TableColumn<Venda, Integer> tableColumnCodigo;
    @FXML
    private TableColumn<Venda, LocalDate> tableColumnData;
    @FXML
    private TableColumn<Venda, Venda> tableColumnCliente;
    @FXML
    private Label labelCodigo;
    @FXML
    private Label labelData;
    @FXML
    private Label labelValor;
    @FXML
    private Label labelPago;
    @FXML
    private Label labelCliente;
    @FXML
    private Button Inserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    
    //Listas
    private List<Venda> listVendas;
    private ObservableList<Venda> observableListVendas;
    
    //Atributos BaseDados
    private final Database db = DatabaseFactory.getDatabase("SQLServer");
    private final Connection conn = db.conectar();
    private final VendaDAO vendaDAO = new VendaDAO();
    private final ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vendaDAO.setConnection(conn);
        carregarTableViewVendas();
        
        //listen acionado quando user clica numa venda
        tableViewVendas.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewVendas(newValue));
    }
    
    public void carregarTableViewVendas(){
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("cdVenda"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        
        listVendas = vendaDAO.listar();
        observableListVendas = FXCollections.observableArrayList(listVendas);
        tableViewVendas.setItems(observableListVendas);
        
    }

    public void selecionarItemTableViewVendas(Venda venda) {
        if(venda != null){
            labelCodigo.setText(String.valueOf(venda.getCdVenda()));
            labelData.setText(String.valueOf(venda.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            labelValor.setText(String.format("%.2f", venda.getValor()));
            labelPago.setText(String.valueOf(venda.getPago()));
            labelCliente.setText(venda.getCliente().toString());
    }else{
            labelCodigo.setText("");
            labelData.setText("");
            labelValor.setText("");
            labelPago.setText("");
            labelCliente.setText("");
        }
        
    }
    
}
