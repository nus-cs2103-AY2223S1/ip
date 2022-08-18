import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        Task[] tasks = new Task[101];
        int number = 1;
        String dummyString;
        int counter;
        int start;
        int end;
        String input = myObj.nextLine();  // Read user input


        while (!input.equals("bye")) {

            if (input.startsWith("mark")) {
                dummyString = input.substring(5); //get number of task
                counter = Integer.parseInt(dummyString);//convert to int
                tasks[counter].mark();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "[" + tasks[counter].getStatusIcon() + "] " + tasks[counter].getDescription());

            } else if (input.startsWith("unmark")) {
                dummyString = input.substring(7); //get number of task
                counter = Integer.parseInt(dummyString);//convert to int
                tasks[counter].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "[" + tasks[counter].getStatusIcon() + "] " + tasks[counter].getDescription());

            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < number; i++) { //iterate through all tasks
                    System.out.println(String.valueOf(i) + "." + tasks[i].toString());
                }
            } else if (input.startsWith("todo")) {
                start = 5;
                tasks[number] = new Todo(input.substring(start));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[number].toString());
                System.out.println("Now you have " + String.valueOf(number) + " tasks in the list.");
                number += 1;

            } else if (input.startsWith("deadline")) {
                counter = input.indexOf("/");
                start = 9;
                end = counter - 1;
                tasks[number] = new Deadline(input.substring(start, end), input.substring(counter + 4));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[number].toString());
                System.out.println("Now you have " + String.valueOf(number) + " tasks in the list.");
                number += 1;

            } else if (input.startsWith("event")) {
                counter = input.indexOf("/");
                start = 6;
                end = counter - 1;
                tasks[number] = new Event(input.substring(start, end), input.substring(counter + 4));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[number].toString());
                System.out.println("Now you have " + String.valueOf(number) + " tasks in the list.");
                number += 1;

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
