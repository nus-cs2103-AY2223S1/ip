package qoobee;

/**
 * Represents the ui the user interacts with.
 */
public class Ui {

    private boolean isOn;

    /**
     * Creates a ui which is active.
     */
    public Ui() {
        this.isOn = true;
    }

    /**
     * Returns the state of the ui.
     * @return The active state of the ui.
     */
    public boolean isOn() {
        return this.isOn;
    }

    /**
     * Ends the interaction with the user.
     */
    public void exit() {
        this.isOn = false;
    }

    /**
     * Prints a loading error if storage could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("OOPS! Something went wrong!! :(");
    }

    /**
     * Greets the user when the bot runs.
     */
    public void greet() {
        System.out.println("Hello I'm best bot Qoobee!\n" + "What can I do for you? ^.^");
    }


}
