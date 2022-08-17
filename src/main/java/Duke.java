import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private static String[] list = new String[100];
    private static int listCounter = 0;

    private static boolean isDone = false;

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);

        while (!isDone) {
            String command = sc.nextLine();

            switch (command) {
                case "bye":
                    bye();
                    break;
                case "list":
                    displayList();
                    break;
                default:
                    printMessage("added: " + command);
                    addList(command);
            }
        }
    }

    private static void exitProgram() {
        isDone = true;
    }

    private static void displayLine() {
        System.out.println(LINE);
    }


    private static void printMessage(String message) {
        displayLine();
        System.out.println(message);
        displayLine();
    }

    private static void greet() {
        String greetingMessage = INDENTATION + "Hello! I'm Duke\n" +
                INDENTATION + "What can I do for you?\n";

        printMessage(greetingMessage);
    }

    private static void bye() {
        String byeMessage = INDENTATION + "Bye. Hope to see you again soon!\n";
        printMessage(byeMessage);
        exitProgram();
    }

    private static void addList(String input) {
        list[listCounter] = input;
        listCounter++;
    }

    private static void displayList() {
        displayLine();
        for (int i = 0; i < listCounter; i++) {
            int orderList = i + 1;
            String message = orderList + ". " + list[i];
            System.out.println(message);
        }
        displayLine();
    }
}
