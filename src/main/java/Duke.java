import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    private static ArrayList<Task> data = new ArrayList<Task>();

    private static void addItem(String item) {
        System.out.println("added: " + item);
        data.add(new Task(item));
    }

    private static void addTodo(String item) {
        data.add(new Todo(item));
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                data.get(data.size() - 1), data.size());
    }
    private static void addDeadLine(String item, String by) {
        data.add(new Deadline(item, by));
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                data.get(data.size() - 1), data.size());
    }

    private static void addEvent(String item, String at) {
        data.add(new Event(item, at));
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                data.get(data.size() - 1), data.size());
    }

    private static void listItems() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < data.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + data.get(i));
        }
    }

    private static void markItem(int index) {
        data.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + data.get(index - 1));
    }

    private static void unMarkItem(int index) {
        data.get(index - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + data.get(index - 1));

    }

    private static <T> int findElem(T[] arr, T elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String[] inputs = command.split(" ");
            if (inputs.length == 1) {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    listItems();
                }
            } else if (inputs.length == 2){
                if (inputs[0].equals("mark")) {
                    int index = Integer.parseInt(inputs[1]);
                    markItem(index);
                } else if (inputs[0].equals("unmark")) {
                    int index = Integer.parseInt(inputs[1]);
                    unMarkItem(index);
                }
            } else {
                if (inputs[0].equals("todo")) {
                    String task = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
                    addTodo(task);
                } else if (inputs[0].equals("deadline")) {
                    int limit = findElem(inputs, "/by");
                    String task = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
                    String by = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
                    addDeadLine(task, by);
                } else if (inputs[0].equals("event")) {
                    int limit = findElem(inputs, "/at");
                    String task = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
                    String at = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
                    addEvent(task, at);
                }
            }
        }
    }
}
