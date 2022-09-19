package jarvis;

import jarvis.task.*;
import jarvis.exception.JarvisException;

public class Parser {
    
    private static String introduction = "Hello. I am Jarvis \n"
            + "What can I do for you today?";
    private static String farewell = "Goodbye, have a good day.";
    private TaskList tasks;

    /**
     * Returns a new Parser object with the given list of tasks.
     * @param tasks Current list of tasks.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Reads and executes commands given by user.
     * @throws JarvisException if command entered is not recognised.
     */
    public String readCommand(String input) throws JarvisException {
            String output = "";

            //check if userinput is bye, break if true
            if (input.equals("bye")) {
                return farewell;
            }
            //if userinput equals list, return task list
            if (input.equals("list")) {
                output = output + ("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.getList().size(); i++) {
                    if (tasks.getList().get(i) == null) {
                        break;
                    }
                    output = output + (i + 1) + ". " + tasks.getList().get(i).toString() + "\n";
                }
                return output;
            }
            // if userInput equals find, find tasks which match given string
            if (input.length() > 4 && input.substring(0,4).equals("find")) {
                String keyword = input.substring((5));
                output = output + ("Here are the matching tasks in your list:\n");
                for (int i = 0; i < tasks.getList().size(); i++) {
                    Task currTask = tasks.getList().get(i);
                    if (currTask == null) {
                        return output;
                    }
                    if (currTask.toString().contains(keyword)) {
                        output = output + ((i + 1) + ". " + currTask) + "\n";
                    }
                }
                return output;
            }

            //if userinput equals mark, check which task and mark it
            if (input.length() > 4 && input.substring(0, 4).equals("mark")) {
                int toMark = Integer.parseInt(input.substring(5)) - 1;
                tasks.getList().get(toMark).mark();
                output = "Great. I have marked this task as done:\n " + tasks.getList().get(toMark).toString();
                return output;
            }
            //if userinput equals unmark, check which task and unmark
            if (input.length() > 6 && input.substring(0, 6).equals("unmark")) {
                int toMark = Integer.parseInt(input.substring(7)) - 1;
                tasks.getList().get(toMark).unmark();
                String markResponse = "Ok, I have marked this task as not done yet:\n ";
                output = markResponse + tasks.getList().get(toMark).toString();
                return output;
            }
            //if userinput equals delete, check which task and delete
            if (input.length() > 6 && input.substring(0, 6).equals("delete")) {
                int toDelete = Integer.parseInt(input.substring(7)) - 1;
                Task deleteTask = tasks.getList().get(toDelete);
                tasks.getList().remove(toDelete);
                String deleteResponse = "Noted. I have removed this task:\n ";
                output = deleteResponse + deleteTask.toString();
                return output;
            }

            //if userinput equals to do add new to do task to list
            if (input.length() > 3 && input.substring(0, 4).equals("todo")) {
                String description = input.substring(4);
                if (description.equals("")) {
                    throw new JarvisException("The description of a todo cannot be empty");
                }
                tasks.getList().add(new ToDo(description));
                output = ("Got it. I've added this task:\n " + tasks.getList().get(Task.count - 1)
                        + "\nNow you have " + (Task.count) + " tasks in the list.");
                return output;
            }

            //if userinput equals deadline add new deadline task to list
            if (input.length() > 8 && input.substring(0, 8).equals("deadline")) {
                int divisor = input.indexOf("/by");
                String description = input.substring(9, divisor - 1);
                String date = input.substring(divisor + 4);
                tasks.getList().add(new Deadline(description, date));
                output = ("Got it. I've added this task:\n " + tasks.getList().get(Task.count - 1)
                        + "\nNow you have " + (Task.count) + " tasks in the list.");
                return output;
            }
            //if userinput equals event add new event task to list
            if (input.length() > 7 && input.substring(0, 5).equals("event")) {
                int divisor = input.indexOf("/at");
                String description = input.substring(6, divisor - 1);
                String date = input.substring(divisor + 4);
                tasks.getList().add(new Event(description, date));
                output = ("Got it. I've added this task:\n " + tasks.getList().get(Task.count - 1)
                        + "\nNow you have " + (Task.count) + " tasks in the list.");
                return output;
            } else {
                return "I'm sorry, but I don't know what that means";
            }
    }

    public static String introduction() {
        return introduction;
    }
}

