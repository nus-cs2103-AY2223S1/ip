package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import exception.DukeException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

public class AddCommand extends Command {
    protected String commandLine;

    public AddCommand(String str) {
        this.commandLine = str;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (commandLine.startsWith("todo")) {
            try {
                if (commandLine.length() <= 4) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    String actTask = commandLine.substring(5);
                    Task currTask = new Todo(actTask);
                    taskList.add(currTask);
                    storage.updateData(taskList);
                    System.out.println("Got it. I've added this task:\n" +
                            " " + currTask + "\n" +
                            "Now you have " + taskList.size() + " tasks in the list.\n");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        } else if (commandLine.startsWith("deadline")) {
            try {
                if (commandLine.length() <= 8) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else {
                    if (!commandLine.contains("/")) {
                        System.out.println("There is no date! >:(\n");
                    } else {
                        int slashIndex = commandLine.indexOf("/");
                        String actTask = commandLine.substring(9, slashIndex - 1);
                        String taskDate = commandLine.substring(slashIndex + 4);
                        Task currTask = new Deadline(actTask, taskDate);
                        taskList.add(currTask);
                        storage.updateData(taskList);
                        System.out.println("Got it. I've added this task:\n" +
                                " " + currTask + "\n" +
                                "Now you have " + taskList.size() + " tasks in the list.\n");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        } else if (commandLine.startsWith("event")) {
            try {
                if (commandLine.length() <= 5) {
                    throw new DukeException("The description of a event cannot be empty.");
                } else {
                    if (!commandLine.contains("/")) {
                        throw new DukeException("There is no date! >:(");
                    } else {
                        int slashIndex = commandLine.indexOf("/");
                        String actTask = commandLine.substring(6, slashIndex - 1);
                        String taskDate = commandLine.substring(slashIndex + 4);
                        Task currTask = new Event(actTask, taskDate);
                        taskList.add(currTask);
                        storage.updateData(taskList);
                        System.out.println("Got it. I've added this task:\n" +
                                " " + currTask.toString() + "\n" +
                                "Now you have " + taskList.size() + " tasks in the list.\n");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}
