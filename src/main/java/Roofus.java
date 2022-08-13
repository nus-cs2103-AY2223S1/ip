import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Roofus {
    private List<Task> tasks = new ArrayList<>();
    static String lineSep = "****************************************";

    void greet() {
        System.out.println(lineSep);
        System.out.println("Hello I'm Roofus\n" + "What can I do for you?");
        System.out.println(lineSep);
    }

    void addTask(Task task) {
        tasks.add(task);
        String reply = String.format("%s\nGot it. I've added this task:\n%s\n" +
                "Now you have %d tasks in the list.\n%s", lineSep, task.toString(), tasks.size(),
                lineSep);
        System.out.println(reply);
    }

    void signOff() {
        System.out.println(String.format("%s\nBye. Hope to see you again soon!\n%s", lineSep, lineSep));
    }

    void list() {
        System.out.println(lineSep);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i).toString());
        }
        System.out.println(lineSep);
    }

    void mark(int index) {
        tasks.get(index-1).mark();
    }

    void unMark(int index) {
        tasks.get(index-1).unmark();
    }

    public static void main(String[] args) {
        Roofus roofus = new Roofus();
        roofus.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            Scanner sc2 = new Scanner(command);
            String firstWord = sc2.next();
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                roofus.list();
            } else if (firstWord.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(sc2.next());
                roofus.mark(index);
            } else if (firstWord.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(sc2.next());
                roofus.unMark(index);
            } else if (firstWord.equalsIgnoreCase("todo")) {
                roofus.addTask(new ToDo(sc2.nextLine()));
            } else if (firstWord.equalsIgnoreCase("deadline")) {
                String details = sc2.nextLine();
                String[] separate = details.split("/by", 2);
                roofus.addTask(new Deadline(separate[0], separate[1]));
            } else if (firstWord.equalsIgnoreCase("event")) {
                String details = sc2.nextLine();
                String[] separate = details.split("/at", 2);
                roofus.addTask(new Event(separate[0], separate[1]));
            } else {
                roofus.addTask(new Task(command));
            }
        }
        roofus.signOff();
    }
}
