import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?" + "\n");
        Scanner sc = new Scanner(System.in);
        String[] input;
        int index = 0;
        Task[] items = new Task[100];
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
                for (int i = 0; i < items.length; i++) {
                    if ((items[i]) == null) {
                        break;
                    }
                    System.out.println(i+1 + ". " + items[i]);
                }
            }
            else {
                switch(input[0]) {
                    case "mark":
                        // when user wants to mark as done
                        int num = Integer.valueOf(input[1]) - 1;
                        if (items[num] == null) {
                            System.out.println("No such task");
                        }
                        else {
                            System.out.println(items[num].markAsDone());
                        }
                        break;

                    case "unmark":
                        // when user wants to mark as not done
                        int numb = Integer.valueOf(input[1]) - 1;
                        if (items[numb] == null) {
                            System.out.println("No such task");
                        }
                        else {
                            System.out.println(items[numb].markAsNotDone());
                        }
                        break;

                    case "todo":
                        // when user wants to add todo task
                        items[index] = new Todo(input[1]);
                        System.out.println("Got it. I've added this task:\n  " + items[index++] +"\n" + Task.getCount());
                        break;

                    case "deadline":
                        // when user wants to add deadline task
                        items[index] = new Deadline(input[1], input[2]);
                        System.out.println("Got it. I've added this task:\n  " + items[index++] +"\n" + Task.getCount());
                        break;

                    case "event":
                        // when user wants to add event task
                        items[index] = new Event(input[1], input[2]);
                        System.out.println("Got it. I've added this task:\n  " + items[index++] +"\n" + Task.getCount());
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
        else if (words[0].equals("mark") || words[0].equals("unmark")) {
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
