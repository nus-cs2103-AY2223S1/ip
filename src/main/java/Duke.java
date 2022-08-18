import java.util.Scanner;

public class Duke {
    private static boolean alive;

    private static String handle(String command, String params) {
        switch (command) {
            case "bye":
                alive = false;
                return "Goodbye!";
            default:
                return command;
        }
    }

    private static void printResponse(String response) {
        System.out.println("    __________________________________________________");
        System.out.println("    " + response);
        System.out.println("    __________________________________________________");
    }

    public static void main(String[] args) {
        printResponse("Hello! What can I do for you today?");
        Scanner sc = new Scanner(System.in);

        alive = true;
        while (alive) {
            String in, command, params;
            in = sc.nextLine();
            int split = in.indexOf(" ");
            if (split < 0) {
                command = in;
                params = "";
            } else {
                command = in.substring(0, split);
                params = in.substring(split + 1);
            }
            printResponse(handle(command, params));
        }
    }
}
