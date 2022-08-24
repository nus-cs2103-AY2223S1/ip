package duke.component;

public class Ui {

    private static final String LINES = "_________________________\n";
    private static final String START = "Hey there! I'm Duke.\nWhat do you want to do today?";


    public void printMessage(String message) {
        System.out.print(LINES + message + "\n" + LINES);
    }

    public void welcome() {
        this.printMessage(START);
    }

}
