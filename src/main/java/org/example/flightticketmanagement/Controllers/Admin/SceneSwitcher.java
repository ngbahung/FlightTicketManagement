package org.example.flightticketmanagement.Controllers.Admin;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    public static Stage stage = new Stage();
    static Scene scene;
    static Parent root;

    public void switchScene(Event event, String newFxml, Stage mainStageIfPopUpExist) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(newFxml + ".fxml"));
            scene = new Scene(loader.load());

            // Reset the Flight Search Page
            if(newFxml.equals("SearchFlightPage")){
                TraCuuBanVeController flightInformationController = loader.getController();
                TraCuuBanVeController.fillDataOfFlights();
            }
        } catch (IOException e) {
            System.out.printf("Unable to import %s.fxml in SceneSwitcher OR there is an error in %s.fxml or it's controller!", newFxml, newFxml);
            e.printStackTrace();
        }

        if (mainStageIfPopUpExist != null) {
            stage = mainStageIfPopUpExist;
            Stage popUpStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            popUpStage.close();
        } else
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public static void createPopUp(String newFxml) {
        try {
            root = new FXMLLoader(SceneSwitcher.class.getResource(newFxml)).load();
        } catch (IOException e) {
            System.out.printf("Unable to import %s.fxml in SceneSwitcher", newFxml);
            e.printStackTrace();
        }
        stage = new Stage();
        scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnShown(e -> stage.getScene().getRoot().requestFocus());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
