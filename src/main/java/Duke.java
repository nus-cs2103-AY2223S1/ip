import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String separator = "––––––––––––––––––––––––––––––––––––––––\n";
    public static List<Task> taskList = new ArrayList<>();
    public static final DukeCommandMap commandMap = new DukeCommandMap();


    public static void printFormatedMessage(String content) {
        System.out.print(separator + content + separator);
    }

    public static void chat (Scanner sc) {
        String userInput = sc.nextLine();

        while (!(userInput.equals("Bye") || userInput.equals("bye"))) {
            String[] input = userInput.split("\\s+", 2);
            String keyword = input[0].toLowerCase();
            String content = input.length < 2 ? "" : input[1];

            try {
                DukeCommand command = commandMap.getCommand(keyword);
                String result = command.run(taskList, content);
                printFormatedMessage(result);
            } catch (DukeException e) {
                printFormatedMessage("OOPS! " + e.errorMessage);
            }
            userInput = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        System.out.print("Tell me what you need\n");
        Scanner sc = new Scanner(System.in);
        chat(sc);
        System.out.print("Goodbye!");
    }
}
