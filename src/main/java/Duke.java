import exceptions.DukeException;
import handlers.DukeCommand;
import handlers.DukeCommandMap;
import models.Storage;
import models.Task;
import models.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static TaskList taskList;
    public static final DukeCommandMap commandMap = new DukeCommandMap();

    public Duke() {
        Storage storage = new Storage();
        try {
            taskList = new TaskList(storage.loadTasksFromDisk());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }
    public static void printFormatedMessage(String content) {
        System.out.print(separator + content + separator);
    }

    public static void save() {}

    public static void chat (Scanner sc) {
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
        System.out.print("Goodbye!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Duke().chat(sc);
    }
}
