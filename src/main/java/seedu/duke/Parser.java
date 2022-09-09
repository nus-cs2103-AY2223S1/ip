package seedu.duke;

import java.io.IOException;

import java.util.ArrayList;


/**
 * Represents a Parser object that takes in a user command.
 */
public class Parser {


    static void validate(String type) throws DukeException {
        if (!type.equals("todo") && !type.equals("deadline") && !type.equals("event")) {
            throw new DukeException("");
        }
    }

    /**
     * Takes in a user input and informs Duke what to do.
     *
     * @param command  The main action of Duke.
     * @param input    The whole user input.
     * @param taskList tasks attribute of Duke.
     * @return String output command.
     */
    public static String parse(String command, String input, TaskList taskList, Duke duke) {
        String[] inputArr = input.split(" ");
        switch (command) {
        case "list":
            return taskList.printList();
        case "mark":
            int taskNum = Integer.parseInt(inputArr[1]);
            try {
                taskList.markTask(taskNum);
                Storage.rewriteTasks(taskList);
            } catch (IndexOutOfBoundsException | IOException e) {
                return "Task does not exist!";
            }
            String output = String.format("Nice! I've marked this task as done:\n%s",
                    taskList.taskList.get(taskNum - 1));
            return output;
        case "unmark":
            int taskNum2 = Integer.parseInt(inputArr[1]);
            try {
                taskList.unmarkTask(taskNum2);
                Storage.rewriteTasks(taskList);
            } catch (IndexOutOfBoundsException | IOException e) {
                return "Task does not exist!";
            }
            String output2 = String.format("OK, I've marked this task as not done yet:\n%s",
                    taskList.taskList.get(taskNum2 - 1));
            return output2;
        case "delete":
            try {
                int taskNum3 = Integer.parseInt(inputArr[1]);
                String deleteLine = taskList.deleteTask(taskNum3);
                Storage.rewriteTasks(taskList);
                return deleteLine;
            } catch (IOException e) {
                // just catching error
            }
            break;
        case "todo":
            try {
                String addLine = TaskList.addTask("todo", input);
                Storage.rewriteTasks(taskList);
                return addLine;
            } catch (IndexOutOfBoundsException | DukeException | IOException e) {
                System.out.println(e.getMessage());
                return String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
            }
        case "deadline":
            try {
                String addLine = TaskList.addTask("deadline", input);
                Storage.rewriteTasks(taskList);
                return addLine;
            } catch (IndexOutOfBoundsException | DukeException | IOException e) {
                System.out.println(e.getMessage());
                return String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
            }
        case "event":
            try {
                String addLine = TaskList.addTask("event", input);
                Storage.rewriteTasks(taskList);
                return addLine;
            } catch (IndexOutOfBoundsException | DukeException | IOException e) {
                System.out.println(e.getMessage());
                return String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
            }
        case "find":
            String keyword = inputArr[1];
            ArrayList<Task> tasks = taskList.find(keyword);
            return Ui.showMatchingTasks(tasks);
        case "bye":
            duke.closeWindow();
            return Ui.showGoodbyeMessage();
        default:
            try {
                validate(inputArr[0]);
            } catch (DukeException e) {
                return "Oh no!! I'm sorry, but I don't know what that means :(";
            }
        }
        return "Oh no!! I'm sorry, but I don't know what that means :(";
    }
}
