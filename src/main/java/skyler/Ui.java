package skyler;

/**
 * Represents an interface for user interactions
 */
public class Ui {

    public void greet() {
        System.out.println("Hello! I'm Skyler\nHow can I help you?\n");
    }

    public void bye() {
        System.out.println("Bye! See you again soon!");
    }

    public void showEmptyDescriptionError() {
        System.out.println("Oh no! Cannot insert task without description.");
    }

    public void showTaskNotRecognisedError() {
        System.out.println("Oops! I'm sorry, but I don't know what that means.");
    }
}
