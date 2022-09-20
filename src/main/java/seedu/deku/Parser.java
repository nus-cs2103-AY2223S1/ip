package seedu.deku;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Represents a Parser object that takes in a user command.
 */
public class Parser {


    static void validate(String type) throws DekuException {
        if (!type.equals("todo") && !type.equals("deadline") && !type.equals("event")) {
            throw new DekuException("");
        }
    }

    /**
     * Takes in a user input and informs Deku what to do.
     *
     * @param command  The main action of Deku.
     * @param input    The whole user input.
     * @param taskList tasks attribute of Deku.
     * @return String output command.
     */
    public static String parse(String command, String input, TaskList taskList, Deku deku) {
        assert input != "" : "input must not be empty!";
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
                    taskList.getTaskList().get(taskNum - 1));
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
                    taskList.getTaskList().get(taskNum2 - 1));
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
            } catch (IndexOutOfBoundsException | DekuException | IOException e) {
                System.out.println(e.getMessage());
                return String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
            }
        case "deadline":
            try {
                String addLine = TaskList.addTask("deadline", input);
                Storage.rewriteTasks(taskList);
                return addLine;
            } catch (IndexOutOfBoundsException | DekuException | IOException e) {
                System.out.println(e.getMessage());
                return String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
            }
        case "event":
            try {
                String addLine = TaskList.addTask("event", input);
                Storage.rewriteTasks(taskList);
                return addLine;
            } catch (IndexOutOfBoundsException | DekuException | IOException e) {
                System.out.println(e.getMessage());
                return String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
            }
        case "find":
            String keyword = inputArr[1];
            ArrayList<Task> tasks = taskList.find(keyword);
            return Ui.showMatchingTasks(tasks);
        case "bye":
            deku.closeWindow();
            return Ui.showGoodbyeMessage();
        case "reschedule":
            try {
                int taskToRescheduleNum = Integer.parseInt(inputArr[1]);
                ArrayList<String> rescheduleDateAndTime = new ArrayList<>();
                for (int i = 0; i < inputArr.length; i++) {
                    rescheduleDateAndTime.add(inputArr[i]);
                }
                rescheduleDateAndTime.remove(0);
                rescheduleDateAndTime.remove(0);
                String rescheduledTaskLine = taskList.rescheduleTask(taskToRescheduleNum, rescheduleDateAndTime);
                Storage.rewriteTasks(taskList);
                return rescheduledTaskLine;
            } catch (IOException e) {
                // just catching error
            }
        default:
            try {
                validate(inputArr[0]);
            } catch (DekuException e) {
                return "Oh no!! I'm sorry, but I don't know what that means :(";
            }
        }
        return "Oh no!! I'm sorry, but I don't know what that means :(";
    }
}
