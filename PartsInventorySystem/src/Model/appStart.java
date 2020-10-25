/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Parrotastic
 */
public class appStart extends Application{
    
     @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Inhouse inhouse1 = new Inhouse(1, "Wheel", 30.00, 5, 1, 80, 8432);
        Inhouse inhouse2 = new Inhouse(2, "Chain", 30.00, 5, 1, 80, 4832);
        Inhouse inhouse3 = new Inhouse(3, "Seat", 30.00, 5, 1, 80, 43243);
        Inhouse inhouse4 = new Inhouse(4, "Paint", 30.00, 5, 1, 80, 34243);
        
        Inventory.addPart(inhouse1);
        Inventory.addPart(inhouse2);
        Inventory.addPart(inhouse3);
        Inventory.addPart(inhouse4);
        
        ObservableList<Part> associatedParts = FXCollections.observableArrayList();
        //associatedParts.add(inhouse4);
        
        
        
        Product product1 = new Product(1, "Bike1", 50.00, 4, 1, 80, associatedParts);
        //associatedParts.add(inhouse3);
        Product product2 = new Product(2, "Bike2", 50.00, 4, 1, 80, associatedParts);
       
        Product product3 = new Product(3, "Bike3", 50.00, 4, 1, 80, associatedParts);
        
        Product product4 = new Product(4, "Bike4", 50.00, 4, 1, 80, associatedParts);
        
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);






        
        launch(args);
    }
    
}
