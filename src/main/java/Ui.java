import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = "Hello there! I'm Duke!\n"
            + "How can I help you?";

    private final Scanner in;
    private final PrintStream out;

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readLine() {
        return in.nextLine();
    }

    public void showMessages(String... messages) {
        for (String message : messages) {
            this.out.println(message);
        }
    }

    public void showFormattedMessage(String message, Object... args) {
        this.out.printf(message, args);
    }

    public void showStartupMessage() {
        this.showMessages(LOGO, WELCOME_MESSAGE);
    }

    public void showOrderedList(Iterable<?> list) {
        int i = 1;
        for (Object obj : list) {
            this.showFormattedMessage("%d. %s%n", i, obj);
            i++;
        }
    }
}
