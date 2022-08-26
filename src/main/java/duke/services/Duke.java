package duke.services;

/**
 * Personal Assistant that helps you keep track of your tasks
 */
public class Duke {

    public static void run() {
        UI.introduceSelf();
        Parser.handleUserInputs();
        UI.sayGoodbye();
    }

    public static void main(String[] args) {
        run();
    }
}