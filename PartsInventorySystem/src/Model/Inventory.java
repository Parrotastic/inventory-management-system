/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Parrotastic
 */
public class Inventory {

    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);

    }

    public static ObservableList<Part> getAllParts() {
        return allParts;

    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;

    }

    private static ObservableList<Part> getFilteredParts() {
        return filteredParts;

    }

    private static ObservableList<Product> getFilteredProducts() {
        return filteredProducts;

    }

    public static Part partIdSearch(int id) {

        for (Part part : getAllParts()) {
            if (part.getId() == id) {
                return part;
            }
        }

        return null;

    }

    public static Part partNameSearch(String name) {

        for (Part part : getAllParts()) {
            if (part.getName().equals(name)) {
                return part;
            }
        }

        return null;

    }

    public static Product productIdSearch(int id) {

        for (Product product : getAllProducts()) {
            if (product.getId() == id) {
                return product;
            }

        }

        return null;

    }

    public static Product productNameSearch(String name) {

        for (Product product : getAllProducts()) {
            if (product.getName().equals(name)) {
                return product;
            }
        }

        return null;

    }

    public void partUpdate(int id, Part part) {

        int index = -1;

        for (Part selectedPart : Inventory.getAllParts()) {
            index++;
            if (part.getId() == id) {
                Inventory.getAllParts().set(index, part);
                return;
            }
        }
        return;
    }

    public void productUpdate(int id, Product product) {

        int index = -1;

        for (Product selectedProduct : Inventory.getAllProducts()) {
            index++;
            if (product.getId() == id) {
                Inventory.getAllProducts().set(index, product);
                return;
            }
        }
        return;
    }

}
