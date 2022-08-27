package duke;

public class Ui {
    private final String FILE_NAME;

    private static final String WELCOME_MESSAGE = "\t-------------------------------\n"
            + "\tHello, I'm Duke!\n" + "\tWhat can I do for you?\n" + "\t-------------------------------";

    private static final String BYE_MESSAGE = "\t-------------------------------\n" +
            "\tBye! Hope to see you again\n" + "\t-------------------------------\n";

    public Ui(String fileName) {
        this.FILE_NAME = fileName;
    }

    public void printLineBreak() {
        String LINE_BREAK = "\t-------------------------------";
        System.out.println(LINE_BREAK);
    }
    public void printCreateNewFile() {
        System.out.println(String.format("\tCreated new file %s to store tasks!", FILE_NAME));
    }

    public void printLoadTasksFromFile() {
        System.out.println(String.format("\tLoaded tasks from %s", FILE_NAME));
    }

    public void printErrorCreatingFile() {
        System.out.println(String.format("\tError opening/creating %s!!!", FILE_NAME));
    }

    public void printErrorDatetimeFormat() {
        System.out.println("\tPlease ensure your datetime format is in YYYY-MM-DD HH:MM");
    }

    public void printErrorTaskNumber() {
        System.out.println("\tPlease make sure you enter a task number correctly!");
    }

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printByeMessage() {
        System.out.println(BYE_MESSAGE);
    }

    public void printCommandNotRecognized() {
        System.out.println("\tOops! I've no idea what you're talking about!");
    }

}
