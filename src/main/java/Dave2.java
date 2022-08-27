import Commands.*;
import DataStruct.Pair;
import DaveExceptions.DaveException;
import Parser.Parser;
import Storage.SaveHandler;
import DataStruct.TaskList;

import java.util.Scanner;

public class Dave2 {

    private static TaskList tasks = new TaskList();

    private static final SaveHandler saveState = new SaveHandler();

    private static final Scanner scanner = new Scanner(System.in);

    private static boolean running = true;

    private static final String line = "____________________________________________________________\n";

    private static void print(Object method) {
        StringBuilder printable = new StringBuilder(line);
        printable.append(method);
        printable.append("\n");
        printable.append(line);
        System.out.println(printable);
    }

    public static void commandLogic(String command, String args) {
        try {
            switch (command) {
                case "bye":
                    print("Bye. Hope to see you again soon!");
                    running = false;
                    break;
                case "list":
                    print(tasks);
                    break;
                case "mark":
                    print(new MarkDoneCommand(Dave2.tasks, args).execute());
                    break;
                case "unmark":
                    print(new UnmarkDoneCommand(Dave2.tasks, args).execute());
                    break;
                case "todo":
                    print(new AddTodoCommand(Dave2.tasks, args).execute());
                    break;
                case "deadline":
                    print(new AddDeadlineCommand(Dave2.tasks, args).execute());
                    break;
                case "event":
                    print(new AddEventCommand(Dave2.tasks, args).execute());
                    break;
                case "remove":
                    print(new RemoveTaskCommand(Dave2.tasks, args).execute());
                    break;
                case "find":
                    print(new FindTasksCommand(Dave2.tasks, args).execute());
                default:
                    throw new DaveException("(｡╯︵╰｡) OOPS!!! I'm sowwy, but I don't know what that means ｡･ﾟﾟ*(>д<)*ﾟﾟ･｡");
            }
        } catch (DaveException de) {
            print(de);
        }
    }

    public static void main(String[] args) {

        try {
            saveState.init();
            tasks = saveState.load();

            String logo = " ____                     _____\n"
                    + "|  _ \\ _____ _   _ __    /___  \\\n"
                    + "| | | |  _  | |/ / _ \\      /  /\n"
                    + "| |_| | |_| |   <  __/     /  /_\n"
                    + "|____/ \\__,_|__/ \\___|    /_____|\n";
            System.out.println(line + "Hello, I'm\n" + logo + "\nHow can I help ùwú?\n" + line);

            while (running) {
                Pair<String, String> inputData = Parser.parseInput(scanner.nextLine());
                commandLogic(inputData.getHead(), inputData.getTail());
            }

            saveState.save(tasks);

        } catch (DaveException e) {
            print(e);
        }
    }
}