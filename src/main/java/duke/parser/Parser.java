package duke.parser;

import java.io.IOException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDescriptionException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Parser {

    private static TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.taskList = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public String readInputString(String s) throws InvalidCommandException,
            InvalidDescriptionException, IOException {
        String[] splitCommand = s.split(" ", 2);
        String command = splitCommand[0];
        switch (command) {
        case "bye":
            storage.clearFile();
            for (int i = 0; i < taskList.getNumOfTasks(); i++) {
                char taskType = taskList.readTask(i).charAt(1);
                String status = taskList.readStatus(i);
                switch (taskType) {
                case 'T': {
                    String taskStatus = status.equals("X") ? "1" : "0";
                    String taskToAppend = "T | " + taskStatus + " | " + taskList.getDescription(i);
                    storage.appendToFile(taskToAppend);
                    break;
                }
                case 'D': {
                    String taskStatus = status.equals("X") ? "1" : "0";
                    String taskToAppend = "D | " + taskStatus + " | " + taskList.getDescription(i) + "| "
                            + taskList.getDate(i);
                    storage.appendToFile(taskToAppend);
                    break;
                }
                case 'E': {
                    String taskStatus = status.equals("X") ? "1" : "0";
                    String taskToAppend = "E | " + taskStatus + " | " + taskList.getDescription(i) + "| "
                            + taskList.getDate(i);
                    storage.appendToFile(taskToAppend);
                    break;
                }
                default:
                    break;
                }
            }
            return ui.getByeMessage();
        case "list": {
            int numOfTasks = taskList.getNumOfTasks();
            if (numOfTasks == 0) {
                return ui.getEmptyTaskMessage();
            } else {
                return ui.getList(taskList);
            }
        }
        case "mark": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                throw new InvalidCommandException("Task does not exist.");
            }
            taskList.setCompleted(index);
            return ui.getMarkedTaskMessage() + taskList.readTask(index);
        }
        case "unmark": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a number.");
            }
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index + 1 > taskList.getNumOfTasks()) {
                throw new InvalidCommandException("Task does not exist.");
            }
            taskList.setNotCompleted(index);
            return ui.getUnmarkedTaskMessage() + taskList.readTask(index);
        }
        case "delete": {
            int index = Integer.parseInt(splitCommand[1]) - 1;
            int numOfTasks = taskList.getNumOfTasks();
            int newNumOfTasks = numOfTasks - 1;
            if (index + 1 > numOfTasks) {
                throw new InvalidCommandException("Task does not exist.");
            }
            String result = ui.getDeleteMessage(taskList.readTask(newNumOfTasks), newNumOfTasks);
            taskList.deleteTask(index);
            return result;
        }
        case "todo": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description.");
            }
            taskList.addTodo(splitCommand[1]);
            int numOfTasks = taskList.getNumOfTasks();
            return ui.getAddTaskMessage(taskList.readTask(numOfTasks - 1), numOfTasks);
        }
        case "deadline": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description and deadline.");
            }
            taskList.addDeadline(splitCommand[1]);
            int numOfTasks = taskList.getNumOfTasks();
            return ui.getAddTaskMessage(taskList.readTask(numOfTasks - 1), numOfTasks);
        }
        case "event": {
            if (splitCommand.length == 1) {
                throw new InvalidDescriptionException("Please add a description and time/date.");
            }
            taskList.addEvent(splitCommand[1]);
            int numOfTasks = taskList.getNumOfTasks();
            return ui.getAddTaskMessage(taskList.readTask(numOfTasks - 1), numOfTasks);
        }
        default:
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
