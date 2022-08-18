import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        Task[] tasks = new Task[101];
        int number = 1;
        String dummyString;
        int counter;
        String input = myObj.nextLine();  // Read user input
        while (!input.equals("bye")) {
            if ((input.length() == 6) && input.substring(0, 4).equals("mark")) {
                dummyString = input.substring(5); //get number of task
                counter = Integer.parseInt(dummyString);//convert to int
                tasks[counter].mark();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "[" + tasks[counter].getStatusIcon() + "] " + tasks[counter].getDescription());
            } else if (input.length() == 8 && input.substring(0, 6).equals("unmark")) {
                dummyString = input.substring(7); //get number of task
                counter = Integer.parseInt(dummyString);//convert to int
                tasks[counter].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "[" + tasks[counter].getStatusIcon() + "] " + tasks[counter].getDescription());
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < number; i++) { //iterate through all tasks
                    System.out.println(String.valueOf(i) + ".[" + tasks[i].getStatusIcon() + "] " +
                            tasks[i].getDescription());
                }
            } else { //adding a task

                tasks[number] = new Task(input);
                number += 1;
                System.out.println("added: " + input);  // Output user input
            }
            input = myObj.nextLine(); // Read next user input
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
