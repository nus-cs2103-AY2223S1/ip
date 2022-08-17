package main.java;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Greetings Human! I am BetaGo, your personal robot assistant!\nHow may I assist you today?\n");
        Scanner sc = new Scanner(System.in);
        String str= sc.nextLine();
        String[] inputs = str.split(" ", 2);
        Task[] storage = new Task[100];
        int counter = 0;

        while (!str.equalsIgnoreCase("bye")) {
            if (str.equalsIgnoreCase("list")) {
                if (counter == 0) {
                    System.out.print("Currently, you have no tasks in your list!\n\n");
                } else {
                    System.out.print("Here are the tasks in your list:\n");
                    for (int i = 0; i < counter; i++) {
                        System.out.print(i+1);
                        System.out.println(". " + storage[i].toString());
                    }
                    System.out.print("\n");
                }
            } else if (inputs[0].equalsIgnoreCase("mark")) {
                if (inputs.length != 2) {
                    System.out.println("Please indicate which task number you would like to mark!\n");
                } else {
                    //Should add a try catch to catch exception where input is not an integer
                    int marker = Integer.valueOf(inputs[1]);
                    if (marker < 1 || marker > counter) {
                        System.out.println("Please indicate a valid task number!\n");
                    } else {
                        storage[marker - 1].markAsDone();
                    }
                }
            } else if (inputs[0].equalsIgnoreCase("unmark")) {
                if (inputs.length != 2) {
                    System.out.println("Please indicate which task number you would like to unmark!\n");
                } else {
                    int marker = Integer.valueOf(inputs[1]);
                    if (marker < 1 || marker > counter) {
                        System.out.println("Please indicate a valid task number!\n");
                    } else {
                        storage[marker - 1].markAsNotDone();
                    }
                }

            } else if (inputs[0].equalsIgnoreCase("todo")) {
                Todo temp = new Todo(inputs[1]);
                storage[counter] = temp;
                counter++;
                System.out.println("Got it. I've added this task:\n" + temp.toString());
                System.out.println("Now you have " + counter + " tasks in the list.\n");
            } else if (inputs[0].equalsIgnoreCase("deadline")) {
                String[] when = inputs[1].split(" /by ", 2);
                System.out.println(when[0]);
                Deadline temp = new Deadline(when[0], when[1]);
                storage[counter] = temp;
                counter++;
                System.out.println("Got it. I've added this task:\n" + temp.toString());
                System.out.println("Now you have " + counter + " tasks in the list.\n");
            } else if (inputs[0].equalsIgnoreCase("event")) {
                String[] where = inputs[1].split(" /at ", 2);
                Event temp = new Event(where[0], where[1]);
                storage[counter] = temp;
                counter++;
                System.out.println("Got it. I've added this task:\n" + temp.toString());
                System.out.println("Now you have " + counter + " tasks in the list.\n");
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
            str = sc.nextLine();
            inputs = str.split(" ", 2);
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
