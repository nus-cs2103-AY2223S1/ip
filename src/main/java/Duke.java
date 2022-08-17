import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<Task> list;
    private void line() {
        System.out.println("________________________________________");
    }
    private void greet() {
        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
    }

    private void echo(String message) {
        line();
        System.out.println(message);
        line();
    }

    private void store(String input) {
        Task t = new Task(input);
        list.add(t);
        line();
        System.out.println("added: " + t);
        line();
    }

    private void enumerateArrayList(){
        line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            System.out.println(i + 1 + "." + t);
        }
        line();
    }

    private void markDone(int index) {
        Task t = this.list.get(index);
        t.markDone();
        line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        line();
    }

    private void markUndone(int index) {
        Task t = this.list.get(index);
        t.markUndone();
        line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        line();
    }

    private int getArraySize() {
        return this.list.size();
    }

    private void handleTodo(String input) {
        Todo todo = new Todo(input);
        addTask(todo);
        line();
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + getArraySize() + " tasks in the list.");
        line();
    }

    private void handleDeadline(String input) {
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateBy = secondModifiedInput[1];
        Deadline deadline = new Deadline(description, dateBy);
        addTask(deadline);
        line();
        System.out.println("Got it, I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + getArraySize() + " tasks in the list.");
        line();
    }

    private void handleEvent(String input) {
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateAt = secondModifiedInput[1];
        Event event = new Event(description, dateAt);
        addTask(event);
        line();
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + getArraySize() + " tasks in the list.");
        line();
    }

    private void addTask(Task t) {
        this.list.add(t);
    }

    private void exit() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        boolean isDone = false;
        duke.list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        duke.greet();
        Scanner scanner = new Scanner(System.in); // creating scanner for user input
        while (!isDone) {
            String input = scanner.nextLine();
            String[] strArray = input.split(" ", 2);
            String first = strArray[0];
            String second = "";
            if (strArray.length == 2) {
                second = strArray[1];
            }
            switch (first) {
                case ("bye"): {
                    duke.exit();
                    isDone = true;
                    break;
                }
                case("list"): {
                    duke.enumerateArrayList();
                    break;
                }
                case("mark"): {
                    duke.markDone(Integer.parseInt(second) - 1);
                    break;
                }
                case("unmark"): {
                    duke.markUndone(Integer.parseInt(second) - 1);
                    break;
                }
                case("todo"): {
                    duke.handleTodo(second);
                    break;
                }
                case("deadline"): {
                    duke.handleDeadline(second);
                    break;
                }
                case("event"): {
                    duke.handleEvent(second);
                    break;
                }
                default: {
                }
            }
        }
    }
}
