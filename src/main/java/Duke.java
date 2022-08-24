import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<>();
    static String divider = "____________________________________________________________";

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println(divider);
        System.out.println("K. Added your task:");
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
                if (index < 0 || index >= taskList.size()) {
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
                if (index < 0 || index >= taskList.size()) {
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
                String desc = text.substring(5);
                if (desc.trim().length() == 0) {
                    System.out.println(divider);
                    System.out.println("Empty task? Are you kidding me??");
                    System.out.println(divider);
                    continue;
                }
                addTask(new Todo(desc));
                continue;
            }
            if (text.startsWith("deadline ")) {
                String[] params = text.substring(9).split(" /by ");
                if (params.length < 2) {
                    failure();
                    continue;
                }
                if (params[0].trim().length() == 0) {
                    System.out.println(divider);
                    System.out.println("Empty task? Are you kidding me??");
                    System.out.println(divider);
                    continue;
                }

                LocalDateTime dt = Task.decodeDateTime(params[1]);
                addTask(new Deadline(params[0], dt));
                continue;
            }
            if (text.startsWith("event ")) {
                String[] params = text.substring(6).split(" /at ");
                if (params[0].trim().length() == 0) {
                    System.out.println(divider);
                    System.out.println("Empty task? Are you kidding me??");
                    System.out.println(divider);
                    continue;
                }
                if (params.length < 2) {
                    failure();
                    continue;
                }

                LocalDateTime dt = Task.decodeDateTime(params[1]);
                addTask(new Event(params[0], dt));
                continue;
            }
            if (text.startsWith("delete ")) {
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                if (index < 0 || index >= taskList.size()) {
                    System.out.println(divider);
                    System.out.println("Umm can you count?" + "\n" + divider);
                    continue;
                }
                Task toRemove = taskList.get(index);
                taskList.remove(index);
                System.out.println(divider);
                System.out.println("K. Removed your task:");
                System.out.println(toRemove);
                System.out.println("Now you have " + taskList.size() + " tasks.");
                System.out.println(divider);
                continue;
            }
            failure();
        }
    }
}
