import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    public void dukeRun() throws DukeException {
        String line = "_______________________________\n";
        System.out.println(line +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                line
        );
        String input = "";
        ArrayList<Task> arr = new ArrayList<Task>();
        //Task[] arr = new Task[100];
        int index = 0;
        while (!input.equals("bye")) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
            } else if (input.startsWith("mark")){
                if (input.length() > 5) {
                    int taskNum = Integer.parseInt(input.substring(5));
                    arr.get(taskNum - 1).makeDone();
                    System.out.println(line +
                            "Nice! I've marked this task as done: \n" +
                            arr.get(taskNum - 1).getStatusIcon() +
                            arr.get(taskNum - 1).description + "\n" + line);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to mark as completed!");
                }
            } else if (input.startsWith("unmark")){
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    arr.get(taskNum - 1).makeUndone();
                    System.out.println(line +
                            "OK, I've marked this task as not done yet: \n" +
                            arr.get(taskNum - 1).getStatusIcon() +
                            arr.get(taskNum - 1).description + "\n" + line);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to mark as incomplete!");
                }
            } else if (input.equals("list")) {
                String list = line + "\n" + "Here are the tasks in your list: \n";
                for (int i = 0; i < index; i++) {
                    list += i + 1;
                    list += ". ";
                    list += arr.get(i).toString();
                    list += "\n";
                }
                System.out.println(list + line);
            } else if (input.startsWith("todo")){
                if (input.length() > 5) {
                    Todo todo = new Todo(input);
                    arr.add(todo);
                    index++;
                    System.out.println(todo.addString(index));
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (input.startsWith("deadline")){
                if (input.length() > 9) {
                    String[] dead = input.split(" /by ");
                    Deadlines deadlines = new Deadlines(dead[0], dead[1]);
                    arr.add(deadlines);
                    index++;
                    System.out.println(deadlines.addString(index));
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (input.startsWith("event")){
                if (input.length() > 6) {
                    String[] time = input.split(" /at ");
                    Event event = new Event(time[0], time[1]);
                    arr.add(event);
                    index++;
                    System.out.println(event.addString(index));
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
            } else if (input.startsWith("delete")) {
                if (input.length() > 7) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    Task toDelete = arr.get(taskNum - 1);
                    arr.remove(taskNum - 1);
                    index --;
                    System.out.println(line + "Noted. I've removed this task: \n" +
                            toDelete.toString() + "\n" +
                            "Now you have " + index + " tasks in the list. \n" + line);
                } else {
                    throw new DukeException("☹ OOPS!!! Please include the index of the task you'd like to delete!");
                }
            }
            else {
//                Task task = new Task(input);
//                arr[index] = task;
//                index ++;
//                System.out.println(line + "added: " + task.description + "\n" + line);
                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
       Duke duke = new Duke();
       try {
           duke.dukeRun();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }
}
