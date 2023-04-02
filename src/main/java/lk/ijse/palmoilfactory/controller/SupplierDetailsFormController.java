package lk.ijse.palmoilfactory.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.palmoilfactory.dto.Supplier;
import lk.ijse.palmoilfactory.model.SupplierModel;
import lk.ijse.palmoilfactory.util.CrudUtil;

import java.sql.SQLException;

public class SupplierDetailsFormController {

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierAddress;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtSupplierContact;

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        if(txtSupplierId.getText().isEmpty() || txtSupplierName.getText().isEmpty() || txtSupplierAddress.getText().isEmpty() || txtSupplierContact.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please Input Data to Add Supplier").show();
        }else {
            String supId = txtSupplierId.getText();
            String supName = txtSupplierName.getText();
            String supAddress = txtSupplierAddress.getText();
            String supContact = txtSupplierContact.getText();

            //Supplier supplier = new Supplier(supId, supName, supAddress, supContact);
            boolean isAdded;

            try {
                isAdded = SupplierModel.addSupplier(supId, supName, supAddress, supContact);
                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added").show();
                    txtSupplierName.clear();
                    txtSupplierId.clear();
                    txtSupplierAddress.clear();
                    txtSupplierContact.clear();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Supplier Not Added Please Try Again").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "OOPSSS!! something happened!!!").show();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "OOPSSS!! something happened!!!").show();
            }
        }
    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        if(txtSupplierId.getText().isEmpty() || txtSupplierName.getText().isEmpty() || txtSupplierAddress.getText().isEmpty() || txtSupplierContact.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please Input Supplier ID and Search Supplier is exist").show();
        }else {
            String supId = txtSupplierId.getText();
            try {

                boolean isDeleted = SupplierModel.deleteSupplier(supId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.WARNING, "Supplier Deleted Successfully").show();
                    txtSupplierName.clear();
                    txtSupplierId.clear();
                    txtSupplierAddress.clear();
                    txtSupplierContact.clear();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Delete Fail").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.WARNING, "OOPSSS!! something happened!!!").show();

            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.WARNING, "OOPSSS!! something happened!!!").show();
            }
        }
    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {
        if(txtSupplierId.getText().isEmpty() && txtSupplierName.getText().isEmpty() && txtSupplierAddress.getText().isEmpty() && txtSupplierContact.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please Input Supplier ID to Search Supplier ").show();
        }else {
            String id = txtSupplierId.getText();
            try {
                Supplier supplier = SupplierModel.searchSupplier(id);
                // if (SupplierModel.searchSupplier(id) != null) {
                if (SupplierModel.searchSupplier(id)  != null) {
                   /* txtSupplierName.setText(supplier.getSupName());
                    txtSupplierAddress.setText(supplier.getSupAddress());
                    txtSupplierContact.setText(supplier.getSupContact());*/
                    txtSupplierName.setText(supplier.getSupName());
                    txtSupplierAddress.setText(supplier.getSupAddress());
                    txtSupplierContact.setText(supplier.getSupContact());
                } else {
                    new Alert(Alert.AlertType.WARNING, "Supplier Not Found Please Try Again").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.WARNING, "OOPSSS!! something happened!!!").show();

            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.WARNING, "OOPSSS!! something happened!!!").show();
            }
        }
    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {
        if(txtSupplierId.getText().isEmpty() || txtSupplierName.getText().isEmpty() || txtSupplierAddress.getText().isEmpty() || txtSupplierContact.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please Input Supplier ID and Search Supplier is exist").show();
        }else {
            String supId = txtSupplierId.getText();
            String supName = txtSupplierName.getText();
            String supAddress = txtSupplierAddress.getText();
            String supContact = txtSupplierContact.getText();

            Supplier supplier = new Supplier(supId, supName, supAddress, supContact);
            boolean isUpdated;

            try {
                isUpdated = SupplierModel.updateSupplier(supplier);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated").show();
                    txtSupplierName.clear();
                    txtSupplierId.clear();
                    txtSupplierAddress.clear();
                    txtSupplierContact.clear();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Supplier Not Updated Please Try Again").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "OOPSSS!! something happened!!!").show();
            } catch (ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "OOPSSS!! something happened!!!").show();
            }
        }
    }
}