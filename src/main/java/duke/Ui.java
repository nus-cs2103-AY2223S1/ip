import java.util.Scanner;

public class Ui {

    Scanner sc = new Scanner(System.in);
    private final String BORDER = "    ____________________________________________________________";
    private final String INDENT = "     ";

    public void showLine() {
        System.out.println(BORDER);
    }

    public void showWelcome() {
        String content;
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        content = logo + "\n" + INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n";
        System.out.print(content);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading data.");
    }

    public void showError(String errMessage) {
        System.out.println(INDENT + errMessage);
    }

    public void showMessage(String message) {
        System.out.println(INDENT + message);
    }

}
