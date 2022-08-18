import java.util.Scanner;

public class Alpha {
    private static void welcomeMessage() {
        System.out.println("\n-----------------\n" + "Hello, I'm ALPHA!" + "\n-----------------");
    }
    private static void enterMessage() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        switch (input) {
            case "bye": {
                System.out.println(">> " + "Bye, See you soon!");
                System.exit(0);
                break;
            }
            default: {
                System.out.println(">> " + input);
                enterMessage();
            }
        }
    }
    public static void main(String[] args) {
        welcomeMessage();
        enterMessage();
    }
}
