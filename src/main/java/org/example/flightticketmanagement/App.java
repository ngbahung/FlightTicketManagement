package org.example.flightticketmanagement;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Models.Model;


public class App extends Application {

    @Override
    public void start(Stage stage) {

        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        Model.getInstance().getViewFactory().hienThiManHinhDangNhap();
    }
}
