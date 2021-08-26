/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaVendas.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Rui
 */
public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem menuItemInscricaoClientes;
    @FXML
    private MenuItem menuItemProcessosVendas;
    @FXML
    private MenuItem menuItemGraficosVendasMes;
    @FXML
    private MenuItem menuItemRelatoriosStock;
    @FXML
    private AnchorPane anchorPaneBody;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleMenuItemInscricaoClientes(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/sistemaVendas/view/FXMLInscricaoClientes.fxml"));
        anchorPaneBody.getChildren().setAll(pane);
    }

    @FXML
    private void menuItemProcessosVendas(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/sistemaVendas/view/FXMLVendas.fxml"));
        anchorPaneBody.getChildren().setAll(pane);
    }
    
}
