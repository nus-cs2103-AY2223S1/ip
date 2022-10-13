package org.Olivia;

import org.Olivia.IO.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the application entry point
 * In the GUI version, the JavaFX launcher will call the start function in this class to setup the app
 * In the CLI version (obsolete), call the main function in this class to start the application
 *
 * @author ZHANG TONGJUN (albertZhangTJ)
 */

public class Olivia extends Application {

    private Gui UI;

    @Override
    public void start(Stage stage) {

        this.UI = new Gui(stage, GuiApplicationInitializer.setUpGuiEventDispatcher());
        this.UI.initialize();
    }


}
