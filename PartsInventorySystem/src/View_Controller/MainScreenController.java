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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Parrotastic
 */
public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;
    

    @FXML
    private TableView<Part> partTblView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> invLvlCol;

    @FXML
    private TableColumn<Part, Double> priceperUnitCol;

    @FXML
    private TableView<Product> productTblView;

    @FXML
    private TableColumn<Product, Integer> prodIdCol;

    @FXML
    private TableColumn<Product, String> prodNameCol;

    @FXML
    private TableColumn<Product, Integer> prodInvLvlCol;

    @FXML
    private TableColumn<Product, Double> prodpricePerUnitCol;

    @FXML
    private Button partSearchBtn;

    @FXML
    private Button productSearchBtn;

    @FXML
    private TextField partSearchbox;

    @FXML
    private TextField productSearchbox;

    @FXML
    private Button exitBtn;

    @FXML
    private Button addPartBtn;

    @FXML
    private Button modPartBtn;

    @FXML
    private Button delPartBtn;

    @FXML
    private Button addProductBtn;

    @FXML
    private Button delProductBtn;

    @FXML
    private Button modProductBtn;

    @FXML
    void onActionDelPart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete?");
        alert.setContentText("Delete this item?");
        alert.showAndWait();      
        Part selectedPart = partTblView.getSelectionModel().getSelectedItem();
        partTblView.getItems().remove(selectedPart);

    }

    @FXML
    void onActionDelProduct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete?");
        alert.setContentText("Delete this item?");
        alert.showAndWait();
        Product selectedProduct = productTblView.getSelectionModel().getSelectedItem();
        productTblView.getItems().remove(selectedProduct);

    }

    @FXML
    void onActionSearchPart(ActionEvent event) {
        String psearch = partSearchbox.getText();
        Part part = null;
        try {
            int partsearch = Integer.parseInt(psearch);
            part = Inventory.partIdSearch(partsearch);
            partTblView.getSelectionModel().select(part);

        } catch (NumberFormatException e) {

            part = Inventory.partNameSearch(psearch);
            partTblView.getSelectionModel().select(part);

        }
    }

    @FXML
    void onActionSearchProduct(ActionEvent event) {
        String psearch = productSearchbox.getText();
        Product product = null;
        try {
            int productsearch = Integer.parseInt(psearch);
            product = Inventory.productIdSearch(productsearch);
            productTblView.getSelectionModel().select(product);

        } catch (NumberFormatException e) {
            product = Inventory.productNameSearch(psearch);
            productTblView.getSelectionModel().select(product);

        }
    }

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddPartMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ProductAddMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModPart(ActionEvent event) throws IOException {
        Parent root;

        stage = (Stage) modPartBtn.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModPartMenu.fxml"));

        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene((scene));
        stage.show();
        ModPartMenuController controller = loader.getController();
        Part part = partTblView.getSelectionModel().getSelectedItem();
        controller.setPart(part);

    }
    

   
    
    @FXML
    void onActionModProduct(ActionEvent event) throws IOException {
        Parent root;
        
        stage = (Stage) modProductBtn.getScene().getWindow();
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductModMenu.fxml"));
        
        
        
      
        
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene((scene));
        stage.show();
        ProductModMenuController controller = loader.getController();
        Product product = productTblView.getSelectionModel().getSelectedItem();
        controller.setProduct(product);
        
    }

    public boolean partDelete(int id) {
        for (Part part : Inventory.getAllParts()) {

            if (part.getId() == id) {
                return Inventory.getAllParts().remove(part);
            }

        }
        return false;
    }

    public boolean productDelete(int id) {
        for (Product product : Inventory.getAllProducts()) {

            if (product.getId() == id) {
                return Inventory.getAllProducts().remove(product);
            }

        }
        return false;
    }

    @FXML
    void onActionExit(ActionEvent event) { 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit?");
        alert.setContentText("Exit program?");
        alert.showAndWait();

        System.exit(0);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partTblView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceperUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTblView.setItems(Inventory.getAllProducts());
        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodpricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsDataProvider();
        productDataProvider();
    }

    public void partsDataProvider() {

        partTblView.setItems(Inventory.getAllParts());

    }

    public void productDataProvider() {
        productTblView.setItems(Inventory.getAllProducts());
    }

}
