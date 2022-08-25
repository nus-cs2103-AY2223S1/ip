import exceptions.DukeException;
import handlers.DukeCommand;
import handlers.DukeCommandMap;
import models.Storage;
import models.TaskList;
import java.util.Scanner;

public class Duke {
    private static final String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String separator = "––––––––––––––––––––––––––––––––––––––––\n";
    public static TaskList taskList;
    public static final DukeCommandMap commandMap = new DukeCommandMap();

    public Duke() {
        try {
            taskList = Storage.loadTasksFromDisk();
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }
    public static void printFormatedMessage(String content) {
        System.out.print(separator + content + separator);
    }

    public void chat (Scanner sc) {
        System.out.println("Hello from\n" + logo);
        System.out.print("Tell me what you need\n");

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
        try {
            Storage.saveTaskToDisk(taskList);
        } catch (DukeException e) {
            printFormatedMessage("OOPS! " + e.errorMessage);
        }
        System.out.print("Goodbye!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Duke().chat(sc);
    }
}
