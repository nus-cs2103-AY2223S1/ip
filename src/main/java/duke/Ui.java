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

    public String printLineBreak() {
        String LINE_BREAK = "\t-------------------------------";
        return LINE_BREAK;
    }

    public String printCreateNewFile() {
        return String.format("\tCreated new file %s to store tasks!", FILE_NAME);
    }

    public String printLoadTasksFromFile() {
        return String.format("\tLoaded tasks from %s", FILE_NAME);
    }

    public String printErrorCreatingFile() {
        return String.format("\tError opening/creating %s!!!", FILE_NAME);
    }

    public String printErrorDatetimeFormat() {
        return "\tPlease ensure your datetime format is in YYYY-MM-DD HH:MM";
    }

    public String printErrorTaskNumber() {
        return "\tPlease make sure you enter a task number correctly!";
    }

    public String printWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public String printByeMessage() {
        return BYE_MESSAGE;
    }

    public String printCommandNotRecognized() {
        return "\tOops! I've no idea what you're talking about!";
    }

}
