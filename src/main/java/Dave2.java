import java.util.Locale;
import java.util.Scanner;

public class Dave2 {

    private static final TaskList tasks = new TaskList();

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

    public static void inputHandling(String input) {
        String[] splitInput = input.trim().split(" ", 2);
        String command = splitInput[0].toLowerCase();
        String args;
        try {
            args = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            args = "";
        }
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
                default:
                    throw new DaveException("(｡╯︵╰｡) OOPS!!! I'm sowwy, but I don't know what that means ｡･ﾟﾟ*(>д<)*ﾟﾟ･｡");
            }
        } catch (DaveException de) {
            print(de);
        }
    }

    public static void main(String[] args) {
        String logo = " ____                     _____\n"
                + "|  _ \\ _____ _   _ __    /___  \\\n"
                + "| | | |  _  | |/ / _ \\      /  /\n"
                + "| |_| | |_| |   <  __/     /  /_\n"
                + "|____/ \\__,_|__/ \\___|    /_____|\n";
        System.out.println(line + "Hello, I'm\n" + logo + "\nHow can I help ùwú?\n" + line);

        while (running) {
            inputHandling(scanner.nextLine());
        }
    }
}