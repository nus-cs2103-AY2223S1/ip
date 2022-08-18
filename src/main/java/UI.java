import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class UI {

    private Scanner sc;
    private List<Task> taskList;

    private static String initGreeting = "Hello! I'm Duke ┌|*ﾟｏﾟ|┘\nWhat can I do for you?";

    public UI() {
        this.sc = new Scanner(System.in);
        this.taskList = new ArrayList<Task>();
    }

    public String greet() {
        return initGreeting;
    }

    public String list() {
        if (this.taskList.isEmpty()) {
            return "No tasks as of now :)";
        } else {
            String temp = "";
            for (int i = 0; i < taskList.size(); i++) {
                int curr = i + 1;
                temp = temp + curr + ": " + taskList.get(i).toString() + "\n";
            }
            return "Here are the tasks in your list:\n"
                   + temp;
        }
    }

    public String blah() {
        return "blah";
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public String addTask(String str) {
        if (str.startsWith("todo")) {
            ToDo todo = new ToDo(str.substring(5));
            taskList.add(todo);
            return "Got it. I've added this task:\n" + todo.toString() + "\n"
                    + String.format("There are %d task(s) in the list", taskList.size());
        } else if (str.startsWith("event")) {
            String[] arrOfStrings = str.split("/at", 2);
            Event event = new Event(arrOfStrings[0], arrOfStrings[1]);
            taskList.add(event);
            return "Got it. I've added this task:\n" + event.toString() + "\n"
                    + String.format("There are %d task(s) in the list", taskList.size());
        } else {
            String[] arrOfStrings = str.split("/by", 2);
            Deadline deadline = new Deadline(arrOfStrings[0], arrOfStrings[1]);
            taskList.add(deadline);
            return "Got it. I've added this task:\n" + deadline.toString() + "\n"
                    + String.format("There are %d task(s) in the list", taskList.size());
        }
    }

    public String markTask(int id) {
        Task curr = taskList.get(id - 1);
        curr.mark();
        return "Nice! I've marked this task as done: \n"
                + curr.toString();
    }

    public String unmarkTask(int id) {
        Task curr = taskList.get(id - 1);
        curr.unmark();
        return "OK, I've marked this task as not done yet: \n"
                + curr.toString();
    }

    public String editTask(String str) {
        if (str.startsWith("mark")) {
            int taskId = Integer.parseInt(str.substring(5));
            return markTask(taskId);
        } else if (str.startsWith("unmark")) {
            int taskId = Integer.parseInt(str.substring(7));
            return unmarkTask(taskId);
        }
        return "";
    }

    public String getInput() {
        return sc.nextLine();
    }
}
