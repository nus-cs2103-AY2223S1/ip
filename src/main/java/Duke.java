import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> todo = new ArrayList<>();

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
            String command = arr[0];
            String description;
            int startIndex;
            String deadline;
            Task task;
            switch (command) {
                case "Bye":
                    System.out.println("Bye. Hope to see you again soon");
                    break loop;
                case "list":
                    System.out.println(formatList(todo));
                    break;
                case "mark":
                    int indexToMark = Integer.valueOf(arr[1]) - 1;

                    toggleTaskStatus(indexToMark);
                    System.out.println("Nice! I'hv marked this task as done: \n" + todo.get(indexToMark).formatTask());
                    break;
                case "unmark":
                    int indexToUnmark = Integer.valueOf(arr[1]) - 1;
                    toggleTaskStatus(indexToUnmark);
                    System.out.println("Sadge u are not done :( \n" + todo.get(indexToUnmark).formatTask());

                    break;
                case "todo":
                    description = String.join(" ", Arrays.copyOfRange(arr, 1, arr.length - 1));
                    task = new Task(description);
                    todo.add(task);
                    System.out.println(String.format("Got it. I'hv added this task: \n   %s", task.formatTask()));
                    break;

                case "deadline":
                case "event":
                    startIndex = Arrays.asList(arr).indexOf(command.equals("deadline") ? "/by" : "/at");
                    description = String.join(" ", Arrays.copyOfRange(arr, 1, startIndex - 1));
                    deadline = String.join(" ", Arrays.copyOfRange(arr, startIndex + 1, arr.length));
                    task = command.equals("deadline") ? new Deadline(description, deadline) : new Event(description, deadline);
                    todo.add(task);
                    System.out.println(String.format("Got it. I'hv added this task: \n   %s", task.formatTask()));
                    break;

                default:
                    System.out.println(String.format("Now you have %d task in the list", todo.size()));
            }
        }



    }
}
