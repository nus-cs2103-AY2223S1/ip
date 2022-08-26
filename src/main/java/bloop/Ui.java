package bloop;

public class Ui {
    private static final String HI_MESSAGE = "Hey! I'm bloop.Bloop\n" + "\tWhat can I do for you?";

    private static final String BYE_MESSAGE = "Goodbye! Hope to see you soon :)";

    private static final String SEPARATOR = "\t-------------------------------------------------------";

    public void print(String message) {
        System.out.println(SEPARATOR);
        System.out.println("\t" + message);
        System.out.println(SEPARATOR);
    }

    public String getSeparator() {
        return SEPARATOR;
    }

    public void startMessage() {
        print(HI_MESSAGE);
    }

    public void endMessage() {
        print(BYE_MESSAGE);
    }

}
