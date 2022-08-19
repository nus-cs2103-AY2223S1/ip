import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<>();
    static String divider = "____________________________________________________________";

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println(divider);
        System.out.println("K. added your task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks.");
        System.out.println(divider);
    }

    public static void failure() {
        System.out.println(divider);
        System.out.println("What's that??");
        System.out.println(divider);
    }
    public static void main(String[] args) {

        System.out.println("  _  __                    \n" +
                " | |/ /__ _ _ __ ___ _ __  \n" +
                " | ' // _` | '__/ _ \\ '_ \\ \n" +
                " | . \\ (_| | | |  __/ | | |\n" +
                " |_|\\_\\__,_|_|  \\___|_| |_|\n");
        System.out.println("Hello I'm Karen. What do you want??\n" + divider);
        Scanner input = new Scanner(System.in);
        while (true) {
            String text = input.nextLine();
            if (text.equals("bye")) {
                System.out.println(divider);
                System.out.println("Hmm kay...\n" + divider);
                break;
            }
            if (text.equals("list")) {
                System.out.println(divider);
                for (int i = 0; i < taskList.size(); i++) {
                    Task taskI = taskList.get(i);
                    System.out.println(i+1 + ". " + taskI);
                }
                System.out.println(divider);
                continue;
            }
            if (text.startsWith("mark ")) {
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                if (index >= taskList.size()) {
                    System.out.println(divider);
                    System.out.println("Umm can you count?" + "\n" + divider);
                    continue;
                }
                Task t = taskList.get(index);
                t.mark();
                System.out.println(divider);
                System.out.println("Oh you did a task. Congratulations.\n" + t + "\n" + divider);
                continue;
            }
            if (text.startsWith("unmark ")) {
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                if (index >= taskList.size()) {
                    System.out.println(divider);
                    System.out.println("Uh can you count?" + "\n" + divider);
                    continue;
                }
                Task t = taskList.get(index);
                t.unmark();
                System.out.println(divider);
                System.out.println("Hmm make up your mind maybe??.\n" + t + "\n" + divider);
                continue;
            }
            if (text.startsWith("todo ")) {
                addTask(new Todo(text.substring(5)));
                continue;
            }
            if (text.startsWith("deadline ")) {
                String[] params = text.substring(9).split(" /by ");
                if (params.length < 2) {
                    failure();
                    continue;
                }
                addTask(new Deadline(params[0], params[1]));
                continue;
            }
            if (text.startsWith("event ")) {
                String[] params = text.substring(6).split(" /at ");
                if (params.length < 2) {
                    failure();
                    continue;
                }
                addTask(new Event(params[0], params[1]));
                continue;
            }
            failure();
        }
    }
}
