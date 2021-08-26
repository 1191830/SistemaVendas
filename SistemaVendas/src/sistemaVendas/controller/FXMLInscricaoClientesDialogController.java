/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaVendas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistemaVendas.model.domain.Cliente;

/**
 * FXML Controller class
 *
 * @author Rui
 */
public class FXMLInscricaoClientesDialogController implements Initializable {

    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteContribuinte;
    @FXML
    private Label labelClienteTelefone;
    @FXML
    private TextField txtClienteNome;
    @FXML
    private TextField txtClienteContribuinte;
    @FXML
    private TextField txtClienteTelefone;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Cliente cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the btnConfirmarClicked
     */
    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    /**
     * @param btnConfirmarClicked the btnConfirmarClicked to set
     */
    public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
        this.btnConfirmarClicked = btnConfirmarClicked;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        txtClienteNome.setText(cliente.getNome());
        txtClienteContribuinte.setText(cliente.getCpf());
        txtClienteTelefone.setText(cliente.getTelefone());
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {
            cliente.setNome(txtClienteNome.getText());
            cliente.setCpf(txtClienteContribuinte.getText());
            cliente.setTelefone(txtClienteTelefone.getText());

            btnConfirmarClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (txtClienteNome.getText() == null || txtClienteNome.getText().length() == 0) {
            errorMessage += "Insira Nome!\n";
        }
        if (txtClienteContribuinte.getText() == null || txtClienteContribuinte.getText().length() == 0) {
            errorMessage += "Insira Contribuinte!\n";
        }
        if (txtClienteTelefone.getText() == null || txtClienteTelefone.getText().length() == 0) {
            errorMessage += "Insira Telefone!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro na Inscrição");
            alert.setHeaderText("Campos inválidos, corrija por favor:");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

}
