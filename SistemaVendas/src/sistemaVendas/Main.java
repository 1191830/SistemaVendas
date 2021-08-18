/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaVendas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Rui
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("view/FXMLVBoxMain.fxml"));
            
            Scene scene = new Scene(root);
        
            stage.setScene(scene);
            stage.setTitle("Sistema Vendas");
            stage.setResizable(false);
            stage.show();
    }
        
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
