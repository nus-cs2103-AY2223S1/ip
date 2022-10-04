package org.Olivia;

import org.Olivia.Dispatchers.CliEventDispatcher;
import org.Olivia.IO.*;
import org.Olivia.calendar.Calendar;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the application entry point
 * In the GUI version, the JavaFX launcher will call the start function in this class to setup the app
 * In the CLI version (obsolete), call the main function in this class to start the application
 * @author ZHANG TONGJUN (albertZhangTJ)
 */

public class Olivia extends Application {

    private Gui UI;


    @Deprecated
    /**
     * This main method is the entry point for the CLI version of the software
     * As the GUI is already implemented, this is no longer needed
     * However, it is kept here in case someone want to try with the CLI version
     */
    public static void main(String[] args) {
        //each worker is attached to a calendar table to work on and an UiHandler to handle IO
        //this design would allow the program to handle multi-user with multi-input-source case (with multi-thread)
        //the UiHandler can be binded to a specific InputStream and a specific PrintStream (default to be stdin & stdout)
        CliEventDispatcher worker_1 = new CliEventDispatcher(new Calendar(), new UiHandler(), new FileHandler());
        worker_1.startWorking();
    }

    @Override
    public void start(Stage stage) {

        this.UI = new Gui(stage, GuiApplicationInitializer.setUpGuiEventDispatcher());
        this.UI.initialize();
    }




}
