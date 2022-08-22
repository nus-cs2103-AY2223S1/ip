import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommand;

public class Ui {

    public void helloMessage() {
        System.out.println("Quack! I'm Duck\nWhat can I do for you?");
    }

    public void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public void byeMessage() {
        System.out.println("Quack! Hope to see you again soon!");
    }

    public void printError(DukeException e) {
        System.out.println(e);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
