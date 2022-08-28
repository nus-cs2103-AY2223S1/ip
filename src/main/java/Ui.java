import java.util.Scanner;

/**
 * Handles UI of Duke.
 */
public class Ui {
    public static String LOGO = " ____        _        \n"
                             + "|  _ \\ _   _| | _____ \n"
                             + "| | | | | | | |/ / _ \\\n"
                             + "| |_| | |_| |   <  __/\n"
                             + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;
    private boolean verbose;

    public Ui() {
        sc = new Scanner(System.in);
        verbose = true;
    }

    public String getNextLine() {
        return sc.nextLine();
    }

    public void printLogo() {
        if (verbose) {
            System.out.println("Hello from\n" + LOGO);
        }
    }

    public void printMessage(String m) {
        String message = "    ____________________________________________________________\n    "
                + m
                + "\n    ____________________________________________________________\n";
        if (verbose) {
            System.out.println(message);
        }
    }

    public void setVerbose(boolean v) {
        verbose = v;
    }
}
