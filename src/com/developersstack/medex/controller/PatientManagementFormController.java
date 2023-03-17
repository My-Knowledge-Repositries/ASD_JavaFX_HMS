package com.developersstack.medex.controller;

import com.developersstack.medex.db.Database;
import com.developersstack.medex.dto.PatientDto;
import com.developersstack.medex.view.tm.PatientTm;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PatientManagementFormController {
    public AnchorPane patientContext;
    public TableView<PatientTm> tblPatient;
    public TableColumn colNic;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colDob;
    public TableColumn colGender;
    public TableColumn colAddress;
    public TableColumn colAge;
    public TableColumn colEmail;
    public JFXTextField txtSearch;

    public void initialize() {
        loadAllData("");
    }

    private void loadAllData(String s) {
        s = s.toLowerCase();
        ObservableList<PatientTm> tmList = FXCollections.observableArrayList();
        for (PatientDto dto : Database.patientTable) {
            if (dto.getFirstName().contains(s) || dto.getLastName().contains(s) || dto.getEmail().contains(s)) {
                tmList.add(new PatientTm(
                        dto.getNic(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getDob(),
                        dto.getGender(),
                        dto.getAddress(),
                        10,
                        dto.getEmail()
                ));
            }
        }
        tblPatient.setItems(tmList);
    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }
}
