import java.util.Scanner;

public class Duke {

    /**
     * This method checks if a string typed can be a number or not
     * @param number
     * @return a boolean value indicating whether the string entered can be parsed as an integer
     */
    public static boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(number);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

    /**
     * This method joins the individual string elements in an array from a start position
     * @param stringArray the array of strings
     * @param start the starting position
     * @return the string representing the elements joined from the starting position to the end
     */
    public static String joinString(String[] stringArray, int start) {
        StringBuilder outputString = new StringBuilder();
        for (int i = start; i < stringArray.length; i++) {
            String toAdd = stringArray[i] + " ";
            outputString.append(toAdd);
        }

        return outputString.toString();

    }

    public static void main(String[] args) {

        // Create an Array to store the Tasks the user has entered of size 100
        Task[] taskArray = new Task[100];
        // Create a count to track the number of items the user has stored
        int count = 0;

        String userCommand = "";

        // Creates a scanner to accept input
        Scanner enterInput = new Scanner(System.in);


        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        userCommand = enterInput.nextLine();

        // When user does not type bye, ask for input and print out the input
        while (!userCommand.equals("bye")) {
            try {
                System.out.println("----------------------");
                // If user types list, output all the text being stored
                if (userCommand.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        Task currentTask = taskArray[i];
                        System.out.println(String.valueOf(i + 1) + "." + currentTask.toString());
                    }
                } else {
                    String[] words = userCommand.split(" ");
                    if (words.length > 1 &&
                            (words[0].equals("mark") || words[0].equals("unmark")) && isNumeric(words[1])) {
                        // If user first word is marked or unmarked and second word can be a number, it is a mark or unmark
                        // Get the index of task to mark or unmark
                        // Get the task and mark it as done or not done depending on the user
                        int taskNumber = Integer.parseInt(words[1]);
                        Task currentTask = taskArray[taskNumber - 1];
                        if (words[0].equals("mark")) {
                            System.out.println("Nice! I've marked this task as done:");
                            currentTask.markAsDone();
                            System.out.println(currentTask.toString());
                        } else {
                            System.out.println("0K, I've marked this task as not done yet:");
                            currentTask.markNotDone();
                            System.out.println(currentTask.toString());
                        }
                    } else {
                        // User is trying to add a new to-do / deadline / event
                        // Create a string to be outputted
                        String outputString = "";
                        if (words[0].equals("todo")) {
                            // If user is trying to add a to-do, save the description
                            String description = "";
                            if (words.length > 1) {
                                description = joinString(words, 1);
                                description = description.substring(0, description.length() - 1);
                            }
                            Todo newTodo = new Todo(description);
                            taskArray[count] = newTodo;
                            outputString = newTodo.toString();
                            count += 1;
                            System.out.println("Got it. I 've added this task:");
                            System.out.println(outputString);
                            System.out.println("Now you have " + String.valueOf(count) + " tasks in the list.");
                        } else if (words[0].equals("deadline")) {
                            String description = "";
                            String[] remainingWords;
                            String by = "";
                            // If user is trying to add a deadline, save the description and the 'by' date
                            String remainingDescription = "";
                            if (words.length > 1) {
                                remainingDescription = joinString(words, 1);
                                remainingWords = remainingDescription.split(" /by ");
                                description = remainingWords[0];
                                by = remainingWords[1];
                                // Cut down a white spacing at the end
                                by = by.substring(0, by.length() - 1);
                            }
                            Deadline newDeadline = new Deadline(description, by);
                            taskArray[count] = newDeadline;
                            outputString = newDeadline.toString();
                            count += 1;
                            System.out.println("Got it. I 've added this task:");
                            System.out.println(outputString);
                            System.out.println("Now you have " + String.valueOf(count) + " tasks in the list.");
                        } else if (words[0].equals("event")) {
                            // If user is trying to add an event, save the description and the 'at' date
                            String description = "";
                            String[] remainingWords;
                            String at = "";
                            String remainingDescription = "";
                            if (words.length > 1) {
                                remainingDescription = joinString(words, 1);
                                remainingWords = remainingDescription.split(" /by ");
                                description = remainingWords[0];
                                at = remainingWords[1];
                                // Cut down a white spacing at the end
                                at = at.substring(0, at.length() - 1);
                            }
                            Event newEvent = new Event(description, at);
                            taskArray[count] = newEvent;
                            outputString = newEvent.toString();
                            count += 1;
                            System.out.println("Got it. I 've added this task:");
                            System.out.println(outputString);
                            System.out.println("Now you have " + String.valueOf(count) + " tasks in the list.");
                        } else {
                            throw new DukeException("I'm sorry, I don't know what that means!");
                        }
                    }

                }
                System.out.println("----------------------");
                userCommand = enterInput.nextLine();
                // Catch exceptions thrown by the Dukebot
            } catch (DukeException dukeException) {
                System.out.println(dukeException.getMessage());
                System.out.println("----------------------");
                userCommand = enterInput.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon ^^!");
    }


}
