/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Parrotastic
 */
public class ProductAddMenuController implements Initializable{

    Stage stage;
    Parent scene;

    @FXML
    private TextField productIDTextField;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField productInventoryTextField;

    @FXML
    private TextField productPriceTextField;

    @FXML
    private TextField productMaxTextField;

    @FXML
    private TextField productMinTextField;

    @FXML
    private TextField productSearchTextField;

    @FXML
    private Button productSearchBtn;

    @FXML
    private Button productAddBtn;

    @FXML
    private Button productDeleteBtn;

    @FXML
    private Button productCancelBtn;

    @FXML
    private Button productSaveBtn;
    @FXML
    private TableView<Part> prodTableView1;
    @FXML
    private TableColumn<Product, Integer> prodIDCol;
    @FXML
    private TableColumn<Product, String> prodNameCol;
    @FXML
    private TableColumn<Product, Integer> prodInvCol;
    @FXML
    private TableColumn<Product, Double> prodPriceCol;
    @FXML
    private TableView<Part> prodTableView2;
    @FXML
    private TableColumn<Product, Integer> prodIDCol2;
    @FXML
    private TableColumn<Product, String> prodNameCol2;
    @FXML
    private TableColumn<Product, Integer> prodInvCol2;
    @FXML
    private TableColumn<Product, Double> prodPriceCol2;
    
    static int idcount = 4;
    
    Product product;

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel?");
        alert.setContentText("Return to Main Menu?");
        alert.showAndWait();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionProductAdd(ActionEvent event) throws IOException {
        Part selectedPart = prodTableView1.getSelectionModel().getSelectedItem();
        prodTableView2.getItems().add(selectedPart);

    }

    @FXML
    void onActionProductDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete?");
        alert.setContentText("Delete this item?");
        alert.showAndWait();
        Part selectedPart = prodTableView2.getSelectionModel().getSelectedItem();
        prodTableView2.getItems().remove(selectedPart);
        

    }

    @FXML
    void onActionProductSave(ActionEvent event) throws IOException {
        
        
        int id = ++idcount;
        String name = productNameTextField.getText();
        int stock = Integer.parseInt(productInventoryTextField.getText());
        double price = Double.parseDouble(productPriceTextField.getText());
        int max = Integer.parseInt(productMaxTextField.getText());
        int min = Integer.parseInt(productMinTextField.getText());
        ObservableList<Part> associatedPart = FXCollections.observableArrayList();
        associatedPart.addAll(prodTableView2.getItems());
        
         if (stock > max || stock < min) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory value out of bounds");
            alert.setContentText("The entered value is not within inventory range.");
            alert.showAndWait();}

        Inventory.addProduct(new Product(id, name, price, stock, min, max, associatedPart));
        
        

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionProductSearch(ActionEvent event) {
        String psearch = productSearchTextField.getText();
        Part part = null;
        try {
            int partsearch = Integer.parseInt(psearch);
            part = Inventory.partIdSearch(partsearch);
            prodTableView1.getSelectionModel().select(part);

        } catch (NumberFormatException e) {
            part = Inventory.partNameSearch(psearch);
            prodTableView1.getSelectionModel().select(part);

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        prodTableView1.setItems(Inventory.getAllParts());
        prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //prodTableView2.setItems(product.getAllAssociatedParts());
        prodIDCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
        
       
      
        
    }
    public void product1DataProvider() {
        prodTableView1.setItems(Inventory.getAllParts());
    }
    public void product2DataProvider() {
        prodTableView2.setItems(product.getAllAssociatedParts()); }

}

