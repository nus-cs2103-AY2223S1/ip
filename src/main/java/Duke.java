import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(null);
        int number = 0;
        String dummyString;
        Task dummyTask;
        int counter;
        int start;
        int end;
        String input = myObj.nextLine();  // Read user input


        while (!input.equals("bye")) {
            try {
                if (input.startsWith("mark")) {
                    dummyString = input.substring(5); //get number of task
                    counter = Integer.parseInt(dummyString);//convert to int
                    tasks.get(counter).mark();
                    System.out.println("Nice! I've marked this task as done:\n" +
                            "[" + tasks.get(counter).getStatusIcon() + "] " + tasks.get(counter).getDescription());

                } else if (input.startsWith("unmark")) {
                    dummyString = input.substring(7); //get number of task
                    counter = Integer.parseInt(dummyString);//convert to int
                    tasks.get(counter).unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" +
                            "[" + tasks.get(counter).getStatusIcon() + "] " + tasks.get(counter).getDescription());

                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= number; i++) { //iterate through all tasks
                        System.out.println(String.valueOf(i) + "." + tasks.get(i).toString());
                    }

                } else if (input.startsWith("delete")) {
                    if (input.equals("delete")) {
                        throw new DukeException("Please specify which item is to be deleted.");
                    }
                    dummyString = input.substring(7); //get item number to be deleted
                    counter = Integer.parseInt(dummyString);//convert to int
                    dummyTask = tasks.get(counter);
                    tasks.remove(counter);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + dummyTask.toString());
                    number -= 1;
                    System.out.println("Now you have " + String.valueOf(number) + " tasks in the list.");


                } else if (input.startsWith("todo")) {
                    if (input.equals("todo")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    start = 5;
                    number += 1;
                    tasks.add(number, new Todo(input.substring(start)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(number).toString());
                    System.out.println("Now you have " + String.valueOf(number) + " tasks in the list.");


                } else if (input.startsWith("deadline")) {
                    if (input.equals("deadline")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    counter = input.indexOf("/");
                    number += 1;
                    start = 9;
                    end = counter - 1;
                    tasks.add(number, new Deadline(input.substring(start, end), input.substring(counter + 4)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(number).toString());
                    System.out.println("Now you have " + String.valueOf(number) + " tasks in the list.");


                } else if (input.startsWith("event")) {
                    if (input.equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    counter = input.indexOf("/");
                    number += 1;
                    start = 6;
                    end = counter - 1;
                    tasks.add(number, new Event(input.substring(start, end), input.substring(counter + 4)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(number).toString());
                    System.out.println("Now you have " + String.valueOf(number) + " tasks in the list.");


                } else { //random input
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e){
                    System.out.println(e.toString().substring(15));
            }
            input = myObj.nextLine(); // Read next user input
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
