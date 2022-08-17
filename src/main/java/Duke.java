import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.valueOf;

public class Duke {
    private static final String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String exit_message = "Bye. Hope to see you again soon!";
    private static final String line = "______________________________________________________________________________";
    private static final String list_message = "Here are the tasks in your list:";
    private static final String add_message = "Got it. I've added this task:";
    private static final String delete_message = "Noted. I've removed this task:";
    private static final ArrayList<Task> ls = new ArrayList<>(100);
    private static void addToList(Task task) {
        ls.add(task);
        System.out.println(add_message);
        System.out.println(task.toString());
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }
    private static void delFromList(int ind) {
        Task deleting_task = ls.get(ind);
        ls.remove(ind);
        System.out.println(delete_message);
        System.out.println(deleting_task.toString());
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
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
        String todo_command = "todo";
        String deadline_command = "deadline";
        String event_command = "event";
        String delete_command = "delete";
        boolean flag = true;
        while (flag) {
            String response = sc.nextLine();
            String [] split_slash = response.split("/");
            String [] cmd_descp = split_slash[0].split(" ");
            String task_description = String.join(" ", Arrays.copyOfRange(cmd_descp,1,cmd_descp.length));
            String command = cmd_descp[0];
            System.out.println(line);
            if (command.toLowerCase().equals(exit_command)) {
                flag = false;
                System.out.println(exit_message);
            } else if (command.toLowerCase().equals(list_command)) {
                display(ls);
            } else if (command.toLowerCase().equals(mark_command)) {
                String ind = cmd_descp[1];
                ls.get(valueOf(ind) - 1).doing();
            } else if (command.toLowerCase().equals(unmark_command)) {
                String ind = cmd_descp[1];
                ls.get(valueOf(ind) - 1).undo();
            } else if (command.toLowerCase().equals(delete_command)) {
                String ind = cmd_descp[1];
                delFromList(valueOf(ind)-1);
            } else if (command.toLowerCase().equals(todo_command)){
                try {
                    String test = cmd_descp[1];
                    addToList(new Todo(task_description));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The description of a todo cannot be empty");
                }
            } else if (command.toLowerCase().equals(deadline_command)){
                try {
                    String test = cmd_descp[1];
                    try {
                        String[] time_part = split_slash[1].split(" ");
                        String time = String.join(" ", Arrays.copyOfRange(time_part, 1, time_part.length));
                        addToList(new Deadline(task_description, time));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Deadline require a /by");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The description of a deadline cannot be empty");
                }
            } else if (command.toLowerCase().equals(event_command)){
                try {
                    String test = cmd_descp[1];
                    try {
                        String[] time_part = split_slash[1].split(" ");
                        String time = String.join(" ", Arrays.copyOfRange(time_part, 1, time_part.length));
                        addToList(new Event(task_description, time));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Event require a /at");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The description of a event cannot be empty");
                }
            } else {
                System.out.println("Invalid Command. Try again.");
            }
            System.out.println(line);
        }
    }
}
