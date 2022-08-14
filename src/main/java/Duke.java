import java.util.Scanner;

public class Duke {
    private static String receiveCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }

    private static void bye() {
        System.out.println("\tGoodbye! See you soon!\n" +
                           "\tAu revoir! À tout à l'heure!");
    }

    private static void echo(String input) {
        System.out.println("\t" + input);
    }

    public static void main(String[] args) {
        System.out.println("Hello! I' Jean\n" +
                           "How can I help you?\n" +
                           "Bonjour! Je m'appelle Jean\n" +
                           "Vous désirez?\n");

        while(true) {
            String input = receiveCommand();
            if (input.equals("bye")) {
                bye();
                break;
            }

            echo(input);
        }




    }
}
