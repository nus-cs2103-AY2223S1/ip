import java.util.Scanner;

public class Duke {

    private static final TaskList tasks = new TaskList();

    private static final Scanner scanner = new Scanner(System.in);

    private static boolean running = true;

    public static void inputHandling(String input) {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                running = false;
                break;
            case "list":
                System.out.println(tasks);
                break;
            case "mark":
                System.out.println(tasks.markdone(Integer.parseInt(splitInput[1])));
                break;
            case "unmark":
                System.out.println(tasks.unmarkdone(Integer.parseInt(splitInput[1])));
                break;
            case "todo":
                System.out.println(tasks.add(new ToDo(splitInput[1])));
                break;
            case "deadline":
                System.out.println(tasks.add(new Deadlines(splitInput[1].split("/by "))));
                break;
            case "event":
                System.out.println(tasks.add(new Events(splitInput[1].split("/at "))));
                break;
        }
    }

    public static void main(String[] args) {
        String logo = " ____                 \n"
                + "|  _ \\ _____ _   ____ \n"
                + "| | | |  _  | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|__/ \\___|\n";
        System.out.println("Hello, I'm\n" + logo + "\nWhat can I do for you?\n");

        while (running) {
            inputHandling(scanner.nextLine());
        }
    }
}