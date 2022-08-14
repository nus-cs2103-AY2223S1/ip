import java.util.Scanner;
/**
 * User interface of Thomas
 */
public class UI {
    Scanner sc;
    private static int lineLength = 40;
    private static int indentLength = 2;

    UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the command entered by the user
     * @return A String representing the user command
     */
    public String getUserCommand() {
        return sc.nextLine();
    }

    /**
     * Echoes back the command for the user
     * @param command The user command
     */
    public void echo(String command) {
        int length = Math.max(command.length(), lineLength);
        String line = "-".repeat(length);
        String indent = " ".repeat(indentLength);
        System.out.println(line + "\n" + indent + command + "\n" + line);
    }

    /**
     * Prints a welcome message for the user
     */
    public void showWelcomeMessage() {
        String line = "-".repeat(lineLength);
        String indent = " ".repeat(indentLength);
        String welcome = "Choo choo! I'm Thomas\n" + indent + "What can I choo for you?";
        System.out.println(line + "\n" + indent + welcome + "\n" + line);
    }

    /**
     * Prints a goodbye message for the user
     */
    public void showGoodbyeMessage() {
        String line = "-".repeat(lineLength);
        String indent = " ".repeat(indentLength);
        String goodbye = "Bye now!";
        System.out.println(line + "\n" + indent + goodbye + "\n" + line);
    }

    /**
     * Prints the Duke logo
     */
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }
}
