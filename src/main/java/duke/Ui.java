package duke;

public class Ui {
    public void printMessage(String message) {
        String line = "    ____________________________________________________________";
        String wrappedMessage = line + "\n     " + message + "\n" + line;
        System.out.println(wrappedMessage);
    }

    public void printGreetings() {
        printMessage("Hello! I'm Duke.\n     What can I do for you?");
    }

    public void printGoodbye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        printMessage("There is a loading error.");
    }
}
