import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> data = new ArrayList<Task>();

    private static void addItem(String item) {
        System.out.println("added: " + item);
        data.add(new Task(item));
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
                } else {
                    addItem(command);
                }
            } else if (inputs.length == 2){
                if (inputs[0].equals("mark")) {
                    int index = Integer.parseInt(inputs[1]);
                    markItem(index);
                } else if (inputs[0].equals("unmark")) {
                    int index = Integer.parseInt(inputs[1]);
                    unMarkItem(index);
                } else {
                    addItem(command);
                }
            } else {
                addItem(command);
            }
        }
    }
}
