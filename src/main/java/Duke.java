import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?" + "\n");
        Scanner sc = new Scanner(System.in);
        String[] input;
        ArrayList<Task> items = new ArrayList<>();
        boolean done = false;

        while(!done) {

            try {
                input = getInput(sc);
            }
            catch (DukeException ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            if (input[0].equals("list")) {
                // when user request list
                System.out.println("Here are the tasks in your list:");
                int count = 1;
                for (Task tsk : items) {
                    System.out.println(String.valueOf(count++)+ "." + tsk);
                }
            }
            else {
                switch(input[0]) {
                    case "mark":
                        // when user wants to mark as done
                        int num = Integer.valueOf(input[1]) - 1;
                        if (num >= items.size()) {
                            System.out.println("No such task");
                        }
                        else {
                            System.out.println(items.get(num).markAsDone());
                        }
                        break;

                    case "unmark":
                        // when user wants to mark as not done
                        int numb = Integer.valueOf(input[1]) - 1;
                        if (numb >= items.size()) {
                            System.out.println("No such task");
                        }
                        else {
                            System.out.println(items.get(numb).markAsNotDone());
                        }
                        break;

                    case "todo":
                        // when user wants to add todo task
                        Task t1 = new Todo(input[1]);
                        items.add(t1);
                        System.out.println("Got it. I've added this task:\n  " + t1 +"\n" + getCount(items.size()));
                        break;

                    case "deadline":
                        // when user wants to add deadline task
                        Task t2 = new Deadline(input[1], input[2]);
                        items.add(t2);
                        System.out.println("Got it. I've added this task:\n  " + t2 +"\n" + getCount(items.size()));
                        break;

                    case "event":
                        // when user wants to add event task
                        Task t3 = new Event(input[1], input[2]);
                        items.add(t3);
                        System.out.println("Got it. I've added this task:\n  " + t3 +"\n" + getCount(items.size()));
                        break;

                    case "delete":
                        // when user wants to delete task
                        try {
                            Task t4 = items.remove(Integer.valueOf(input[1]) - 1);
                            System.out.println("Noted. I've removed this task:\n  " + t4 + "\n" + getCount(items.size()));
                        }
                        catch (IndexOutOfBoundsException ex) {
                            System.out.println("Invalid index");
                        }
                        break;

                    case "bye":
                        done = true;
                        break;
                }
            }
            System.out.println("");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String getCount(int len) {
        return "Now you have " + String.valueOf(len) + " tasks in the list.";
    }

    public static String[] getInput(Scanner sc) throws DukeException {
        String input = sc.nextLine();
        String phrases[] = input.split(" /.. ", 2); // splits sentence and removes by/at
        String words[] = phrases[0].split(" ", 2);

        if (words[0].equals("todo")) {
            if (words.length != 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            else {
                return words;
            }
        }
        else if (words[0].equals("deadline") || words[0].equals("event")) {
            if (phrases.length != 2) {
                throw new DukeException("Please add in timing information.");
            }
            else {
                String output[] = new String[3];
                output[0] = words[0]; // type of task
                output[1] = words[1]; // task detail
                output[2] = phrases[1]; // task timing information
                return output;
            }
        }
        else if (words[0].equals("bye") || words[0].equals("list")) {
            if (words.length != 1) {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            else {
                return words;
            }
        }
        else if (words[0].equals("mark") || words[0].equals("unmark") || words[0].equals("delete")) {
            if (words.length != 2) {
                throw new DukeException("Please put in index");
            }
            else {
                return words;
            }
        }
        else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
