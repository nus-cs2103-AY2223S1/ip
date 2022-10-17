package hazell.ui.gui;

import hazell.Hazell;
import hazell.ui.UiInterface;



/**
 * A class that abstracts the interactions with user via the GUI.
 */
public class Gui implements UiInterface {

    private MainWindow window;

    private String userInput = null;
    private Hazell hazell;

    public Gui(MainWindow window) {
        this.window = window;
    }

    @Override
    public void attachBotInstance(Hazell hazell) {
        this.hazell = hazell;
    }

    @Override
    public boolean hasNextUserInput() {
        System.out.println("Userinput is ");
        System.out.println(userInput);
        return userInput != null;
    }

    /**
     * Sets the internal buffer to a new user input.
     *
     * @param input User input to store
     */
    public void setUserInput(String input) {
        System.out.println("Setting userinput");
        userInput = input;
    }

    @Override
    public String getNextUserInput() {
        String output = userInput;
        userInput = null;
        return output;
    }

    @Override
    public void displayUserInput(String input) {
        window.displayUserInput(input);
    }

    @Override
    public void displayBotResponse(String response) {
        window.displayBotResponse(response);
    }


    @Override
    public void start() {

    }

    public void step() {

    }

    @Override
    public void run() {

    }
}
