package pixel.util;

import pixel.Pixel;
import pixel.task.Task;

import java.io.IOException;
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
    public void parse (String userInput) {

        try {
            if (userInput.strip().equals("bye")) {
                System.out.println(UserInterface.GOODBYE_MESSAGE);
                System.exit(0);

            } else if (userInput.strip().startsWith("todo ")) {
                taskList.handleNewTask(userInput, "T");
                System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().startsWith("deadline ")) {
                taskList.handleNewTask(userInput, "D");
                System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().startsWith("event ")) {
                taskList.handleNewTask(userInput, "E");
                System.out.println(UserInterface.AFTER_VALID_INPUT);

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

                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(Storage.INPUT_TASKS.get(indexToChange - 1));
                System.out.println(UserInterface.AFTER_VALID_INPUT);

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

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(Storage.INPUT_TASKS.get(indexToChange - 1));
                System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().equals("list")) {
                // System.out.println(inputMemory.length);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Pixel.count; i++) {
                    Task currentTask = Storage.INPUT_TASKS.get(i);
                    System.out.println((i + 1) + ". " + currentTask);
                }
                System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().startsWith("delete ")) {
                Storage.deleteEntry(userInput, filePath);
                System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else if (userInput.strip().startsWith("find ")) {
                ArrayList<Task> results = Storage.findEntry(userInput);
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < results.size(); i++) {
                    Task currentTask = results.get(i);
                    System.out.println((i + 1) + ". " + currentTask);
                }
                System.out.println(UserInterface.AFTER_VALID_INPUT);

            } else {
                throw new IncorrectFormatException("Input should be a task or command!"); // programme breaks
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("caught Index Out of Bounds Exception");
            System.out.println(UserInterface.AFTER_INVALID_INPUT);
            System.out.println(UserInterface.PROMPT_MESSAGE);

        } catch (StackOverflowError e) {
            System.out.println(e);
            System.out.println("caught Stack Overflow Error");
            System.out.println(UserInterface.AFTER_INVALID_INPUT);
            System.out.println(UserInterface.PROMPT_MESSAGE);

        } catch (NullPointerException e) {
            System.out.println(e);
            System.out.println("caught Null pointer exception");
            System.out.println(UserInterface.AFTER_INVALID_INPUT);
            System.out.println(UserInterface.PROMPT_MESSAGE);

        } catch (IncorrectFormatException e) {
            System.out.println(e);
            System.out.println("Incorrect format exception!");
            System.out.println(UserInterface.AFTER_INVALID_INPUT);
            System.out.println(UserInterface.PROMPT_MESSAGE);

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Caught IO exception! Please ensure that the file address provided is valid");
            System.out.println(UserInterface.PROMPT_MESSAGE);

//        } finally {
//            // clean up
//            // System.out.println("cleaning up. Process resumes. Please enter your new input");
//            run();
        }
    }

}
