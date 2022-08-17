import java.util.Scanner;

public class KKBot {
    public static void main(String[] args) {
        //Welcome message and header
        String divider = "____________________________________________________________\n";
        String logo = " __   __   __   __  _____\n"
                    + "|  | /  / |  | /  /|  __  \\\n"
                    + "|  |/  /  |  |/  / | |__|  |\n"
                    + "|     /   |     /  |      /\n"
                    + "|     \\   |     \\  |  __  \\\n"
                    + "|  |\\  \\  |  |\\  \\ | |__|  |\n"
                    + "|__| \\__\\ |__| \\__\\|______/\n";
        String welcomeMessage = "Hello! I'm KKBot! \n"
                + "What can I do for you?\n";
        System.out.println(divider + logo + welcomeMessage + divider);

        //Add scanner in for user input (create scanner object)
        Scanner userText = new Scanner(System.in);
        //scanner function:
        //if input is bye, send out closing message
        //else just echo the input
        while (true) {
            String input = userText.nextLine();
            System.out.println(divider);
            if (input.equals("bye") || input.equals("Bye") || input.equals("BYE")) {
                System.out.println("KKBot signing off. Goodbye!\n" + divider);
                break;
            }
            System.out.println(input + "\n" + divider);
        }
    }
}
