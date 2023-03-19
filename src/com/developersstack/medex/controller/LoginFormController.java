package com.developersstack.medex.controller;

import com.developersstack.medex.db.Database;
import com.developersstack.medex.dto.User;
import com.developersstack.medex.enums.AccountType;
import com.developersstack.medex.util.Cookie;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXRadioButton rBtnDoctor;
    public AnchorPane loginContext;

    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        AccountType accountType = rBtnDoctor.isSelected() ? AccountType.DOCTOR : AccountType.PATIENT;
        // if (rBtnDoctor.isSelected()) accountType = AccountType.DOCTOR;

        for (User dto : Database.userTable) {
            if (dto.getEmail().toLowerCase().equals(email)) {
                if (dto.getPassword().equals(password)) {
                    if (dto.getAccountType().equals(accountType)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Success!").show();

                        Cookie.selectedUser = dto;
                        setUi("DoctorDashboardForm");
//                        Stage stage = (Stage) loginContext.getScene().getWindow();
//                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DoctorDashboardForm.fxml"))));
                        return;
                    } else {
                        /*new Alert(Alert.AlertType.WARNING, "We can't find your" + accountType + "account").show();*/
                        new Alert(Alert.AlertType.WARNING, String.format("We can't find your %s account", accountType.name())).show();
                        return;
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Your password is incorrect").show();
                    return;
                }
            }
        }
        new Alert(Alert.AlertType.WARNING, String.format("we can't find an email (%s)", email)).show();
    }

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
//        Stage stage = (Stage) loginContext.getScene().getWindow();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SignUpForm.fxml"))));
        setUi("SignUpForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage =(Stage) loginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.
                load(getClass().getResource("../view/"+location+".fxml"))));
    }
}
