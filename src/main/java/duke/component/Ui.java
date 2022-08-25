package duke.component;

public class Ui {

    public static final String MSG_LINES = "_________________________\n";
    public static final String MSG_START = "Hey there! I'm Duke.\nWhat do you want to do today?";


    public void printMessage(String message) {
        System.out.print(MSG_LINES + message + "\n" + MSG_LINES);
    }

    public void welcome() {
        this.printMessage(MSG_START);
    }

}
