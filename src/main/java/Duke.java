import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static boolean alive;
    private static List<String> tasks;

    private static String handle(String command, String params) {
        switch (command) {
            case "list":
                String out = "";
                for (int i = 0; i < tasks.size(); ++i) {
                    if (i != 0) out += "\n";
                    out += (i + 1) + ". " + tasks.get(i);
                }
                return out;
            case "bye":
                alive = false;
                return "Goodbye!";
            default:
                tasks.add(command + " " + params);
                return "added: " + command + " " + params;
        }
    }

    private static void printResponse(String response) {
        System.out.println("    __________________________________________________");
        System.out.println("    " + response.replace("\n", "\n    "));
        System.out.println("    __________________________________________________");
    }

    public static void main(String[] args) {

        tasks = new ArrayList<>();

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
