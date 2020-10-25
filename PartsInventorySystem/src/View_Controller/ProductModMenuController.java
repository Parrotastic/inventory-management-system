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
public class ProductModMenuController implements Initializable {

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
    private TableColumn<Part, Integer> prodIDCol;
    @FXML
    private TableColumn<Part, String> prodNameCol;
    @FXML
    private TableColumn<Part, Integer> prodInvCol;
    @FXML
    private TableColumn<Part, Double> prodPriceCol;
    @FXML
    private TableView<Part> prodTableView2;
    @FXML
    private TableColumn<Part, Integer> prodIDCol2;
    @FXML
    private TableColumn<Part, String> prodNameCol2;
    @FXML
    private TableColumn<Part, Integer> prodInvCol2;
    @FXML
    private TableColumn<Part, Double> prodPriceCol2;

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
    void onActionProductAdd(ActionEvent event) {
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
        Product product = null;
        Part part = null;

        int id = Integer.parseInt(productIDTextField.getText());
        String name = productNameTextField.getText();
        int stock = Integer.parseInt(productInventoryTextField.getText());
        double price = Double.parseDouble(productPriceTextField.getText());
        int max = Integer.parseInt(productMaxTextField.getText());
        int min = Integer.parseInt(productMinTextField.getText());
        ObservableList<Part> associatedParts = FXCollections.observableArrayList();
        associatedParts.addAll(prodTableView2.getItems());
        
         if (stock > max || stock < min) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory value out of bounds");
            alert.setContentText("The entered value is not within inventory range.");
            alert.showAndWait();}

        product = new Product(id, name, price, stock, min, max, associatedParts);

        Inventory.getAllProducts().add(product);
        Inventory.allProducts.remove(this.product);
        product.getAllAssociatedParts().add(part);

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
            prodTableView2.getSelectionModel().select(part);

        } catch (NumberFormatException e) {
            part = Inventory.partNameSearch(psearch);
            prodTableView2.getSelectionModel().select(part);

        }

    }

    public void setProduct(Product product) {
        this.product = product;

        productIDTextField.setText(Integer.toString(product.getId()));
        productNameTextField.setText(product.getName());
        productInventoryTextField.setText(new Integer(product.getStock()).toString());
        productPriceTextField.setText(new Double(product.getPrice()).toString());
        productMaxTextField.setText(new Integer(product.getMax()).toString());
        productMinTextField.setText(new Integer(product.getMin()).toString());

        prodTableView2.setItems(product.getAllAssociatedParts());
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Shows all Parts from the Inventory.
        prodTableView1.setItems(Inventory.getAllParts());
        prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Shows the specific Parts that are affiliated with the selected Product.
        prodIDCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
      
    }

    
}
