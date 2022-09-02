package pixel.util;

import pixel.Pixel;
import pixel.task.Task;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Deals with making sense of the user command
 */
public class Parser { // inner class

    private final TaskList taskList;
    private final String filePath;

    public Parser(String filePath) {
        this.filePath = filePath;
        this.taskList = new TaskList(filePath);
    }

    // Method is made public to facilitate testing, should be private
    public String parse (String userInput) {

        try {
            if (userInput.strip().startsWith("bye")) {
                // System.out.println(UserInterface.GOODBYE_MESSAGE);
                return UserInterface.GOODBYE_MESSAGE;
                // System.exit(0);

            } else if (userInput.strip().startsWith("todo ")) {
                return taskList.handleNewTask(userInput, "T");
                // System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().startsWith("deadline ")) {
                return taskList.handleNewTask(userInput, "D");
                // System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().startsWith("event ")) {
                return taskList.handleNewTask(userInput, "E");
                // System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().startsWith("mark ")) {
                // truncate the front part
                String temp = userInput.substring(5);
                // System.out.println(temp);
                int indexToChange = Character.getNumericValue(temp.charAt(0));
                // System.out.println(indexToChange);
                if ((indexToChange > 0) && (indexToChange < 100)) {
                    Storage.INPUT_TASKS.get(indexToChange - 1).markAsDone();
                }

                Storage.resetFile(this.filePath);
                for (Task task : Storage.INPUT_TASKS) {
                    Storage.appendToFile(task, this.filePath);
                }

                return (" Nice! I've marked this task as done: \n"
                    + Storage.INPUT_TASKS.get(indexToChange - 1) + "\n"
                    + UserInterface.AFTER_VALID_INPUT);
//                System.out.println(Storage.INPUT_TASKS.get(indexToChange - 1));
//                System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().startsWith("unmark ")) {
                // truncate the front part
                String temp = userInput.substring(7);
                // System.out.println(temp);
                int indexToChange = Character.getNumericValue(temp.charAt(0));
                // System.out.println(indexToChange);
                if ((indexToChange > 0) && (indexToChange < 100)) {
                    Storage.INPUT_TASKS.get(indexToChange - 1).markAsNotDone();
                }

                Storage.resetFile(this.filePath);
                for (Task task : Storage.INPUT_TASKS) {
                    Storage.appendToFile(task, this.filePath);
                }

               return ("OK, I've marked this task as not done yet: \n"
                   + Storage.INPUT_TASKS.get(indexToChange - 1) + "\n"
                   + UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().equals("list")) {
                // System.out.println(inputMemory.length);
                String output = "Here are the tasks in your list: \n";

                for (int i = 0; i < Pixel.count; i++) {
                    Task currentTask = Storage.INPUT_TASKS.get(i);
                    output += ((i + 1) + ". " + currentTask + "\n");
                }
                return output + UserInterface.AFTER_VALID_INPUT;

            } else if (userInput.strip().startsWith("delete ")) {
                String output = Storage.deleteEntry(userInput, filePath);
                return output + "\n" + UserInterface.AFTER_VALID_INPUT;

            } else if (userInput.strip().startsWith("find ")) {
                ArrayList<Task> results = Storage.findEntry(userInput);
                String output = "Here are the matching tasks in your list: \n";
                for (int i = 0; i < results.size(); i++) {
                    Task currentTask = results.get(i);
                    output += ((i + 1) + ". " + currentTask + "\n");
                }
                return output + UserInterface.AFTER_VALID_INPUT;

            } else {
                throw new IncorrectFormatException("Input should be a task or command!"); // programme breaks
            }

        } catch (IndexOutOfBoundsException e) {
            // System.out.println(e);
            return ("caught Index Out of Bounds Exception \n"
            + UserInterface.AFTER_INVALID_INPUT + "\n"
            + UserInterface.PROMPT_MESSAGE);

        } catch (StackOverflowError e) {
            // System.out.println(e);
            return ("caught Stack Overflow Error \n"
            + UserInterface.AFTER_INVALID_INPUT + "\n"
            + UserInterface.PROMPT_MESSAGE);

        } catch (NullPointerException e) {
            // System.out.println(e);
            return ("caught Null pointer exception \n"
            + UserInterface.AFTER_INVALID_INPUT + "\n"
            + UserInterface.PROMPT_MESSAGE);

        } catch (IncorrectFormatException e) {
            // System.out.println(e);
            return ("Incorrect format exception! \n"
            + UserInterface.AFTER_INVALID_INPUT + "\n"
            + UserInterface.PROMPT_MESSAGE);

        } catch (IOException e) {
            // System.out.println(e);
            return ("Caught IO exception! Please ensure that the file address provided is valid \n"
            + UserInterface.PROMPT_MESSAGE);

//        } finally {
//            // clean up
//            // System.out.println("cleaning up. Process resumes. Please enter your new input");
//            run();
        }
    }

}
