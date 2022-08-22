import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();
    private final static String WELCOME =
            "Hello! I'm Duke\n" +
                    "What can I do for you?\n";

    public static void main(String[] args) {
        System.out.println(WELCOME);
        CmdHandler cmdHandler = new CmdHandler();
        cmdHandler.handle();
    }
}
