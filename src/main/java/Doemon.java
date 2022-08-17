import java.util.ArrayList;
import java.util.Scanner;

public class Doemon {
    /**
     * Picture of Doemon made using text
     */
    private static String logo = "                       _______________\n" +
            "                      /  --. --.      \\ \n" +
            "                     /  | '| ' |   \\   \\ \n" +
            "                    / /  `-O--'     \\   \\ \n" +
            "                   |.  --  |  --     |   |\n" +
            "                   |  --   |  --     |   |\n" +
            "                    \\  (___|_______) /  /\n" +
            "                     \\              /  /\n" +
            "                       |== (t) ===|____";

    /**
     * Introduction string that is printed when Doemon is started.
     */
    private static String introStr = "Hello I'm\n" + logo + "\t\t\tDoemon!";

    /**
     * The string that is printed when Doemon is exited.
     */
    private static String exitStr = "I'm going to sleep now...See you again soon!";

    /**
     * A fixed-sized string array that stores user input.
     */
    private ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Introduction
        System.out.println(output(introStr));

        new Doemon().start();
    }

    /**
     * Starts the Doemon chatbot.
     */
    private void start() {
        // Handling inputs
        Scanner sc = new Scanner(System.in);

        while (true) {
            String inputStr = sc.nextLine();

            // Exit the chatbot
            if (inputStr.equals("bye")) {
                System.out.println(output(exitStr));
                break;
            }

            // List tasks
            if (inputStr.equals("list")) {
                int listNum = 1;
                String listStr = "Here is what's on my bread:\n\t";
                for (Task task : tasks) {
                    if (task == null) break;
                    listStr += listNum++ + "." + task.toString() + "\n\t";
                }
                listStr = listNum == 1 ? "You have no tasks!" : listStr.trim();
                System.out.println(output(listStr));
                continue;
            }

            String[] inputArr = inputStr.split(" ");

            // Check for mark/unmark/delete
            try {
                if (hasMarkedOrDeleted(inputArr)) continue;
            } catch (InvalidTaskNumberException itne) {
                System.out.println(output(itne.toString()));
            }

            // Add task
            try {
                addTask(inputStr, inputArr);
            } catch (EmptyTaskException ete) {
                System.out.println(output(ete.toString()));
            } catch (InvalidTaskException ite) {
                System.out.println(output(ite.toString()));
            } catch (MissingArgumentException mae) {
                System.out.println(output(mae.toString()));
            }
        }
    }

    /**
     * Attempts to mark/unmark/delete a task depending on the input. Returns a boolean indicating if the command was
     * to mark/unmark/delete.
     * @param inputArr the user-inputted string split into String array using a space delimiter
     * @return a boolean indicating if the command was a mark/unmark/delete command
     * @throws InvalidTaskNumberException if the task number indicated does not exist
     */
    private boolean hasMarkedOrDeleted(String[] inputArr) throws InvalidTaskNumberException{
        if (inputArr.length == 2
                && (inputArr[0].equals("mark") || inputArr[0].equals("unmark") || inputArr[0].equals("delete"))
                && isInteger(inputArr[1])) {
            int index = Integer.parseInt(inputArr[1]) - 1;
            if (index >= 0 && index < this.tasks.size()) {
                if (inputArr[0].equals("mark")) {
                    this.tasks.get(index).mark();
                    System.out.println(
                            output(String.format("Yay! This task is now marked as done:\n\t  %s",
                                    this.tasks.get(index).toString())));
                } else if (inputArr[0].equals("unmark")){
                    this.tasks.get(index).unmark();
                    System.out.println(
                            output(String.format("I guess you weren't done with that one:\n\t  %s",
                                    this.tasks.get(index).toString())));
                } else {
                    Task removed = this.tasks.remove(index);
                    System.out.println(
                            output(String.format("I used a knife to slice off this task from my bread:\n\t  %s" +
                                            "\n\tThere are %d items left on my bread.",
                                    removed.toString(),
                                    this.tasks.size())));
                }
                return true;
            } else {
                throw new InvalidTaskNumberException(inputArr[0]);
            }
        }
        return false;
    }

    /**
     * Adds a task to the list of tasks
     * @param inputStr the user-inputted string
     * @param inputArr the user-inputted string split into String array using a space delimiter
     * @throws EmptyTaskException if task description is empty
     * @throws InvalidTaskException if the add task command given is invalid
     * @throws MissingArgumentException if there is a missing argument from a deadline or event
     */
    private void addTask(String inputStr, String[] inputArr) throws EmptyTaskException, InvalidTaskException,
            MissingArgumentException {
        // Add item to list of tasks
        if (inputArr[0].equals("todo")) {
            String detail = inputStr.substring(4).trim();
            if (detail.trim().equals("")) throw new EmptyTaskException("todo");
            this.tasks.add(new Todo(detail));
        } else if (inputArr[0].equals("deadline")) {
            String[] details = inputStr.substring(8).trim().split(" /by ");
            if (details[0].trim().equals("")) throw new EmptyTaskException("deadline");
            if (details.length == 1) throw new MissingArgumentException("deadline", "/by");
            this.tasks.add(new Deadline(details[0], details[1]));
        } else if (inputArr[0].equals("event")) {
            String[] details = inputStr.substring(5).trim().split(" /at ");
            if (details[0].trim().equals("")) throw new EmptyTaskException("event");
            if (details.length == 1) throw new MissingArgumentException("event", "/at");
            this.tasks.add(new Event(details[0], details[1]));
        } else {
            throw new InvalidTaskException();
        }
        System.out.println(output("Alright! I have recorded this task on my bread:\n\t  "
                + this.tasks.get(this.tasks.size() - 1).toString()
                + "\n\tYou now have " + this.tasks.size() + " task(s) recorded on my bread."));
    }

    /**
     * Used to check if the input string after mark/unmark is an integer.
     * @param text the string input to be checked
     * @return a boolean indicating if the text can be parsed into an integer
     */
    private static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns a formatted string to display the given text.
     * @param text the text to be formatted
     * @return the formatted string
     */
    private static String output(String text) {
        String line = "____________________________________________________________";
        String outputStr = "\t" + line + "\n" +
                "\t" + text + "\n" +
                "\t" + line;
        return outputStr;
    }
}
