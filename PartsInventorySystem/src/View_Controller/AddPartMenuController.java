


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Parrotastic
 */
public class AddPartMenuController implements Initializable {

    Stage stage;
    Parent scene;

    private static final AtomicInteger count = new AtomicInteger(0);

    @FXML
    private RadioButton inhouseRdBtn;

    @FXML
    private RadioButton outsourcedRdBtn;

    private Boolean outSourced;

    @FXML
    private TextField partIDTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partInvTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private TextField partMinTxt;

    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    @FXML
    private ToggleGroup addPartSelection;
    @FXML
    private Label partSpecialLbl;
    @FXML
    private TextField partSpecialTxtField;

    static int idcount = 4;

    @FXML
    private void onActionInhouseRdBtn(ActionEvent event) {
        outSourced = false;
        partSpecialLbl.setText("Machine ID");
    }

    @FXML
    private void onActionOutsourcedRdBtn(ActionEvent event) {
        outSourced = true;
        partSpecialLbl.setText("Company");
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel?");
        alert.setContentText("Return to Main Menu?");
        alert.showAndWait();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML

    void onActionPartSave(ActionEvent event) throws IOException {
         
            
         
        int id = ++idcount;
        String name = partNameTxt.getText();
        int stock = Integer.parseInt(partInvTxt.getText());
        double price = Double.parseDouble(partPriceTxt.getText());
        int max = Integer.parseInt(partMaxTxt.getText());
        int min = Integer.parseInt(partMinTxt.getText());

        if (stock > max || stock < min) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory value out of bounds");
            alert.setContentText("The entered value is not within inventory range.");
            alert.showAndWait();}
        
        

            if (outSourced == true) {
                String companyName = partSpecialTxtField.getText();

                Outsourced outPart = new Outsourced(id, name, price, stock, min, max, companyName);

                outPart.setId(id);
                outPart.setName(name);
                outPart.setPrice(price);
                outPart.setStock(stock);
                outPart.setMin(min);
                outPart.setMax(max);
                outPart.setCompanyName(companyName);
                Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));

            } else {
                int machineId = Integer.parseInt(partSpecialTxtField.getText());

                Inhouse inPart = new Inhouse(id, name, price, stock, min, max, machineId);

                inPart.setId(id);
                inPart.setName(name);
                inPart.setPrice(price);
                inPart.setStock(stock);
                inPart.setMin(min);
                inPart.setMax(max);
                inPart.setMachineId(machineId);

                Inventory.addPart(new Inhouse(id, name, price, stock, min, max, machineId));

            }
        

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
       
    
    


/**
 * Initializes the controller class.
 */
@Override
        public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

}
