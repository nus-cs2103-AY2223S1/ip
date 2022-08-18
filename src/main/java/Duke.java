import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String welcomeMsg = "Hello! I'm Duke\n     What can I do for you?";
        printEchosWithFormat(welcomeMsg);

        while(true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            }

            printEchosWithFormat(userInput);
        }

        String endingMsg = "Bye. Hope to see you again soon!";
        printEchosWithFormat(endingMsg);
    }

    private static void printEchosWithFormat(String msg) {
        String startHorizontalLines = "     ________________________________________\n";
        String endHorizontalLines = "\n     ________________________________________\n";
        System.out.println(startHorizontalLines + "     " + msg + endHorizontalLines);
    }
}
