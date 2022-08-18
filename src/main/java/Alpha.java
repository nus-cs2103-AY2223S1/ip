import java.util.Scanner;
public class Alpha {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {
        welcomeMessage();
        enterMessage();
    }

    private static void welcomeMessage() {
        System.out.println("\n-----------------\n" + ANSI_BLUE + "Hello, I'm ALPHA!" + ANSI_RESET + "\n-----------------");
    }

    private static void enterMessage() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        switch (input) {
            case "bye": {
                System.out.println(ANSI_YELLOW + ">> " + "Bye, See you soon!" + ANSI_RESET);
                System.exit(0);
                break;
            }
            default: {
                System.out.println(ANSI_YELLOW + ">> " + input + ANSI_RESET);
                enterMessage();
            }
        }
    }
}
