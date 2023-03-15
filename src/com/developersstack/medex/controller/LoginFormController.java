package com.developersstack.medex.controller;

import com.developersstack.medex.db.Database;
import com.developersstack.medex.dto.UserDto;
import com.developersstack.medex.enums.AccountType;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class LoginFormController {
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXRadioButton rBtnDoctor;

    public void signInOnAction(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        AccountType accountType = rBtnDoctor.isSelected() ? AccountType.DOCTOR : AccountType.PATIENT;
        // if (rBtnDoctor.isSelected()) accountType = AccountType.DOCTOR;

        for (UserDto dto : Database.userTable) {
            if (dto.getEmail().toLowerCase().equals(email)) {
                if (dto.getPassword().equals(password)) {
                    if (dto.getAccountType().equals(accountType)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Success!").show();
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

    public void createAnAccountOnAction(ActionEvent actionEvent) {

    }
}
