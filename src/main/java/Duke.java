import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.valueOf;

public class Duke {
    private static final String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String exit_message = "Bye. Hope to see you again soon!";
    private static final String line = "______________________________________________________________________________";
    private static final String list_message = "Here are the tasks in your list:";
    private static final ArrayList<Task> ls = new ArrayList<>(100);
    private static void addToList(Task task) {
        ls.add(task);
        System.out.println("added: " + task.description());
    }
    private static void display(ArrayList<Task> ls) {
        System.out.println(list_message);
        for (int i = 1; i <= ls.size(); i++) {
            System.out.println(i + ". " + ls.get(i - 1).toString());
        }
    }
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println(greetings);
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        String exit_command = "bye";
        String list_command = "list";
        String mark_command = "mark";
        String unmark_command = "unmark";
        boolean flag = true;
        while (flag) {
            String response = sc.nextLine();
            System.out.println(line);
            if (response.toLowerCase().equals(exit_command)) {
                flag = false;
                System.out.println(exit_message);
            } else if (response.toLowerCase().equals(list_command)) {
                display(ls);
            } else if (response.toLowerCase().equals(mark_command)) {
                display(ls);
                System.out.println("Enter the task number you want to mark done: ");
                String ind = sc.nextLine();
                ls.get(valueOf(ind) - 1).doing();
            } else if (response.toLowerCase().equals(unmark_command)) {
                display(ls);
                System.out.println("Enter the task number you want to mark as not done yet: ");
                String ind = sc.nextLine();
                ls.get(valueOf(ind) - 1).undo();
            } else {
                addToList(new Task(response));
            }
            System.out.println(line);
        }
    }
}
