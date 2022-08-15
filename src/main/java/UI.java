import java.util.Scanner;
/**
 * The user interface of Dwuke.
 */
public class UI {
    public static final int lineLength = 60;
    private Scanner sc;

    UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the input entered by the user.
     *
     * @return A String representing the user input.
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints the text for the user.
     *
     * @param text The text to print.
     */
    public void echo(String text) {
        String line = "-".repeat(lineLength);
        String indentedText = text.replaceAll("(?m)^", "\t");
        System.out.println(line + "\n" + indentedText + "\n" + line);
    }

    /**
     * Prints a welcome message for the user.
     */
    public void showWelcomeMessage() {
        String line = "-".repeat(lineLength);
        String welcomeText = "hewwo UwU! am dwuke\nwat can me do fow u?";
        String indentedText = welcomeText.replaceAll("(?m)^", "\t");
        System.out.println(line + "\n" + indentedText + "\n" + line);
    }

    /**
     * Prints a goodbye message for the user.
     */
    public void showGoodbyeMessage() {
        String line = "-".repeat(lineLength);
        String goodbyeText = "Bwye. am hope to UwU u soon";
        String indentedText = goodbyeText.replaceAll("(?m)^", "\t");
        System.out.println(line + "\n" + indentedText + "\n" + line);
    }

    /**
     * Prints the Duke logo.
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
