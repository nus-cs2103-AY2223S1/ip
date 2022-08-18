import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /**
     * This method checks if a string typed can be a number or not
     * @param number the string that is entered that could be a number
     * @return a boolean value indicating whether the string entered can be parsed as an integer
     */
    public static boolean isNumeric(String number) {
        if (number == null) {
            return false;
        }
        try {
            // try to parse the integer as a number
            int num = Integer.parseInt(number);
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

    /**
     * The main function
     * @param args arguments
     */
    public static void main(String[] args) {

        // Create the commands for greeting and departure
        Command greeting = new Command(CommandName.GREETINGS);
        Command departure = new Command(CommandName.DEPARTURE);

        // Create an Array to store the Tasks the user has entered of size 100
        ArrayList<Task> taskArrayList = new ArrayList<>();
        String userCommand = "";

        // Creates a scanner to accept input
        Scanner enterInput = new Scanner(System.in);


        // Greet user
        greeting.printCommand();

        userCommand = enterInput.nextLine();

        // When user does not type bye, ask for input and print out the input
        while (!userCommand.equals("bye")) {
            try {
                System.out.println("----------------------");
                // If user types list, output all the text being stored
                if (userCommand.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskArrayList.size(); i++) {
                        Task currentTask = taskArrayList.get(i);
                        System.out.println(String.valueOf(i + 1) + "." + currentTask.toString());
                    }
                } else {
                    // Get all the words the user has typed
                    String[] words = userCommand.split(" ");
                    if (words.length > 1 &&
                            (words[0].equals("mark") || words[0].equals("unmark")) && isNumeric(words[1])) {
                        // If user first word is marked or unmarked and second word can be a number, it is a mark or unmark
                        // Get the index of task to mark or unmark
                        // Get the task and mark it as done or not done depending on the user
                        int taskNumber = Integer.parseInt(words[1]);
                        // Check if the user enters a number out of range
                        if (taskNumber < 0 || taskNumber >= taskArrayList.size()) {
                            throw new DukeException("Number out of range!");
                        }
                        else {
                            // If number is valid, undergo the operation
                            Task currentTask = taskArrayList.get(taskNumber - 1);
                            if (words[0].equals("mark")) {
                                // Mark task
                                System.out.println("Nice! I've marked this task as done:");
                                currentTask.markAsDone();
                                System.out.println(currentTask.toString());
                            } else {
                                // Unmark task
                                System.out.println("0K, I've marked this task as not done yet:");
                                currentTask.markNotDone();
                                System.out.println(currentTask.toString());
                            }
                        }
                    } else if (words.length > 1 && words[0].equals("delete") && isNumeric(words[1])) {
                        // Get the taskNumber to delete
                        // Delete the task
                        // Check if the user enters a number out of range
                        int taskNumber = Integer.parseInt(words[1]);
                        if (taskNumber < 0 || taskNumber >= taskArrayList.size()) {
                            throw new DukeException("Number out of range!");
                        }
                        else {
                            Task currentTask = taskArrayList.get(taskNumber - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(currentTask.toString());
                            taskArrayList.remove(taskNumber - 1);
                            System.out.println("Now you have " + String.valueOf(taskArrayList.size()) +" tasks in the list.");
                        }
                    }
                    else {
                        // User is trying to add a new to-do / deadline / event
                        // Create a string to be outputted
                        String outputString = "";
                        if (words[0].equals("todo")) {
                            // If user is trying to add a to-do, save the description
                            // Have a default value in case the user did not add any description
                            String description = "";
                            if (words.length > 1) {
                                description = joinString(words, 1);
                                description = description.substring(0, description.length() - 1);
                            }
                            Todo newTodo = new Todo(description);
                            taskArrayList.add(newTodo);
                            outputString = newTodo.toString();
                            System.out.println("Got it. I 've added this task:");
                            System.out.println(outputString);
                            System.out.println("Now you have " + String.valueOf(taskArrayList.size()) + " tasks in the list.");
                        } else if (words[0].equals("deadline")) {
                            // If user is trying to add a deadline, save the description and the 'by' date
                            // Have a default value in case the user did not add any description
                            String remainingDescription = "";
                            String description = "";
                            String[] remainingWords;
                            String by = "";
                            if (words.length > 1) {
                                // Remaining description are the words after the task description
                                remainingDescription = joinString(words, 1);
                                remainingWords = remainingDescription.split(" /by ");
                                description = remainingWords[0];
                                by = remainingWords[1];
                                // Cut down a white spacing at the end
                                by = by.substring(0, by.length() - 1);
                            }
                            Deadline newDeadline = new Deadline(description, by);
                            taskArrayList.add(newDeadline);
                            outputString = newDeadline.toString();
                            System.out.println("Got it. I 've added this task:");
                            System.out.println(outputString);
                            System.out.println("Now you have " + String.valueOf(taskArrayList.size()) + " tasks in the list.");
                        } else if (words[0].equals("event")) {
                            // If user is trying to add an event, save the description and the 'at' date
                            // Have a default value in case the user did not add any description
                            String description = "";
                            String[] remainingWords;
                            String at = "";
                            String remainingDescription = "";
                            if (words.length > 1) {
                                // Remaining description are the words after the task description
                                remainingDescription = joinString(words, 1);
                                remainingWords = remainingDescription.split(" /at ");
                                description = remainingWords[0];
                                at = remainingWords[1];
                                // Cut down a white spacing at the end
                                at = at.substring(0, at.length() - 1);
                            }
                            Event newEvent = new Event(description, at);
                            taskArrayList.add(newEvent);
                            outputString = newEvent.toString();
                            System.out.println("Got it. I 've added this task:");
                            System.out.println(outputString);
                            System.out.println("Now you have " + String.valueOf(taskArrayList.size()) + " tasks in the list.");
                        } else {
                            throw new DukeException("I'm sorry, I don't know what that means!");
                        }
                    }

                }
                System.out.println("----------------------");
                // Catch exceptions thrown by the Dukebot
            } catch (DukeException dukeException) {
                System.out.println(dukeException.getMessage());
                System.out.println("----------------------");
            } finally {
                userCommand = enterInput.nextLine();
            }
        }

        // User operations done
        departure.printCommand();
    }


}
