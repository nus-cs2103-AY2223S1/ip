import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> todo = new ArrayList<>();
    private static String[] commandList = new String[]{"list", "Bye", "todo", "mark", "unmark", "event", "deadline", "delete"};
    enum Commands {
        list,
        Bye,
        todo,
        mark,
        unmark,
        event,
        deadline,
        delete
    }
    private static String formatList(ArrayList<Task> lst) {

        String result = "";
        int length = lst.size();
        for (int i = 0; i < length; i++) {
            Task curr = lst.get(i);
            result +=String.format("%d. %s \n",i + 1, curr.formatTask());
        }
        return result;
    }
    private static void toggleTaskStatus(int index) {
       Task task =  todo.get(index);
       task.toggleStatus();
    }

    public static String validateDescription(String description) throws descriptionException {
        if (description != "todo") {
            return description;
        }
        throw new descriptionException();
    }

    public static Boolean validateCommand(String command) throws NoSuchCommandException {
        for (String x : commandList) {
            if (x.equals(command)) {
                return true;
            }
        }
        throw new NoSuchCommandException();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner((System.in));
        System.out.println("Hello! I'm Duke \n What can i do for you? \n");



        loop: while (true) {
            String input = scanner.nextLine();
            String arr[] = input.split(" ");
            String commandString = arr[0];
            try {
                validateCommand(commandString);
                Commands command = Commands.valueOf(commandString);
                String description ;
                int startIndex;
                String deadline;
                Task task;

                switch (command) {
                    case Bye:
                        System.out.println("Bye. Hope to see you again soon");
                        break loop;
                    case list:
                        System.out.println(formatList(todo));
                        break;
                    case mark:
                        int indexToMark = Integer.valueOf(arr[1]) - 1;

                        toggleTaskStatus(indexToMark);
                        System.out.println("Nice! I'hv marked this task as done: \n" + todo.get(indexToMark).formatTask());
                        break;
                    case unmark:
                        int indexToUnmark = Integer.valueOf(arr[1]) - 1;
                        toggleTaskStatus(indexToUnmark);
                        System.out.println("Sadge u are not done :( \n" + todo.get(indexToUnmark).formatTask());

                        break;
                    case todo:
                        try {
                            description = validateDescription(String.join(" ", Arrays.copyOfRange(arr, 1, arr.length)));
                            task = new Task(description);
                            todo.add(task);
                            System.out.println(String.format("Got it. I'hv added this task: \n   %s", task.formatTask()));
                            System.out.println(String.format("Now you have %d task in the list\n", todo.size()));
                        } catch (descriptionException err) {
                            System.out.println(err.toString());
                        }
                        break;
                    case deadline:
                    case event:
                        startIndex = Arrays.asList(arr).indexOf(command.equals("deadline") ? "/by" : "/at");
                        try {
                            description = validateDescription(String.join(" ", Arrays.copyOfRange(arr, 1, startIndex)));
                            deadline = String.join(" ", Arrays.copyOfRange(arr, startIndex + 1, arr.length));
                            task = command.equals("deadline") ? new Deadline(description, deadline) : new Event(description, deadline);
                            todo.add(task);
                            System.out.println(String.format("Got it. I'hv added this task: \n   %s", task.formatTask()));
                            System.out.println(String.format("Now you have %d task in the list\n", todo.size()));
                        } catch (descriptionException err) {
                            System.out.println(err.toString());
                        }
                        break;
                    case delete:
                        int index = Integer.valueOf(arr[1]) - 1;
                        task = todo.get(index);
                        todo.remove(index);
                        System.out.println(String.format("Noted. I've removed this task: \n   %s", task.formatTask()));
                        System.out.println(String.format("Now you have %d task in the list\n", todo.size()));

                    default:

                }
            } catch (NoSuchCommandException err) {
                System.out.println(err);
            }

        }



    }
}
