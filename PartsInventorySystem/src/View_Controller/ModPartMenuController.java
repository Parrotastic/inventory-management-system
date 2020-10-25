/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
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
public class ModPartMenuController implements Initializable {

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
    
    Part part;
    
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
        Part part = null;

        int id = Integer.parseInt(partIDTxt.getText());
        String name = partNameTxt.getText();
        int stock = Integer.parseInt(partInvTxt.getText());
        double price = Double.parseDouble(partPriceTxt.getText());
        int max = Integer.parseInt(partMaxTxt.getText());
        int min = Integer.parseInt(partMinTxt.getText());
        String companyName = partSpecialTxtField.getText();

        if (addPartSelection.getSelectedToggle().equals(inhouseRdBtn)) {
            int machID = Integer.parseInt(companyName);
            part = new Inhouse(id, name, price, stock, min, max, machID);
        } else {

            part = new Outsourced(id, name, price, stock, min, max, companyName);

        }

        if (stock > max || stock < min) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory value out of bounds");
            alert.setContentText("The entered value is not within inventory range.");
            alert.showAndWait();
        }

        Inventory.allParts.remove(this.part);
        Inventory.getAllParts().add(part);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void setPart(Part part) {
        this.part = part;

        partIDTxt.setText(Integer.toString(part.getId()));
        partNameTxt.setText(part.getName());
        partInvTxt.setText(new Integer(part.getStock()).toString());
        partPriceTxt.setText(new Double(part.getPrice()).toString());
        partMaxTxt.setText(new Integer(part.getMax()).toString());
        partMinTxt.setText(new Integer(part.getMin()).toString());

        if (part instanceof Inhouse) {
            Inhouse ihPart = (Inhouse) part;
            partSpecialTxtField.setText(Integer.toString(ihPart.getMachineId()));
            partSpecialLbl.setText("Machine ID");
            inhouseRdBtn.selectedProperty().set(true);
        } else {
            Outsourced osPart = (Outsourced) part;
            partSpecialTxtField.setText(osPart.getCompanyName());
            partSpecialLbl.setText("Company Name");
            outsourcedRdBtn.selectedProperty().set(true);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }


}
