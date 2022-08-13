import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private List<Task> tasks = new ArrayList<>();

    void greet() {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
    }

    void addTask(String task) {
        Task newTask = new Task(task);
        tasks.add(newTask);
        System.out.println("added: " + newTask.getDescription());
    }

    void signOff() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void list() {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i).toString());
        }
    }

    void mark(int index) {
        tasks.get(index-1).mark();
    }

    void unMark(int index) {
        tasks.get(index-1).unmark();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            Scanner sc2 = new Scanner(command);
            String firstWord = sc2.next();
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                duke.list();
            } else if (firstWord.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(sc2.next());
                duke.mark(index);
            } else if (firstWord.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(sc2.next());
                duke.unMark(index);
            } else {
                duke.addTask(command);
            }
        }
        duke.signOff();
    }
}
