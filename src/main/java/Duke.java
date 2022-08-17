import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        View.printLogo();

        View.printWelcomeText();

        //Initialise the scanner used.
        Scanner sc = new Scanner(System.in);

        Enum currCommand = null;
        String description = null;

        while (true) {

            String message = sc.nextLine();
            currCommand = Controller.parseToEnum(message);
            description = Controller.parseToDescription(message);

            Controller.execute(currCommand, description);

        }
    }
}
