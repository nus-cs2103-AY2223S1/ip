package duke;

public class Ui {

    public void printOut(String str) {
        String line = "____________________________________________________________\n";
        System.out.println(line + str + "\n" + line);
    }

    public void showWelcome() {
        printOut("Hello! I'm Duke.\n" +
                "What can I do for you?");
    }

    public void showGoodbye() {
        printOut("See you later. Bye!");
    }

    public void showInvalidTaskIndexError() {
        printOut("This task number is invalid!");
    }
}
