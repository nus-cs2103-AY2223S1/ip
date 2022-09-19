import duke.ui.Gui;

import javafx.application.Application;

/**
 * Launches the interactive Duke GUI Application
 */
public class Launcher {

    /**
     * Launches the Duke application
     * @param args String[] arguments passed on to method
     */
    public static void main(String[] args){
        Application.launch(Gui.class, args);
    }
}
