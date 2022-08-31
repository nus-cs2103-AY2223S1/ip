package duke;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The main class of the Duke application.
 *
 * @author Elbert Benedict
 */
public class Duke {
    private TaskList taskList;

    /**
     * Contructs a new Duke instance.
     */
    public Duke() {
        try {
            taskList = Storage.getSavedTasks();
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Returns response messsage for user input.
     *
     * @param input the user input.
     * @return response message.
     */
    public String getResponse(String input) {
        try {
            String[] parsed = Parser.parseUserInput(input);
            //command is first word of the input
            String command = parsed[0];
            int index;
            Task task;

            switch (command) {
            case "todo":
                String taskString = parsed[1];
                task = new ToDo(taskString);
                taskList.add(task);
                Storage.saveTasks(taskList);
                return Ui.getTaskAddedMessage(task, taskList.getSize());

            case "deadline":
                String deadlineTask = parsed[1];
                String deadlineDate = parsed[2];
                task = new Deadline(deadlineTask, deadlineDate);
                taskList.add(task);
                Storage.saveTasks(taskList);
                return Ui.getTaskAddedMessage(task, taskList.getSize());

            case "event":
                String eventString = parsed[1];
                String eventDate = parsed[2];
                task = new Event(eventString, eventDate);
                taskList.add(task);
                Storage.saveTasks(taskList);
                return Ui.getTaskAddedMessage(task, taskList.getSize());

            case "mark":
                index = Integer.parseInt(parsed[1]);
                return taskList.mark(index);

            case "unmark":
                index = Integer.parseInt(parsed[1]);
                return taskList.unmark(index);

            case "delete":
                index = Integer.parseInt(parsed[1]);
                return taskList.delete(index);

            case "find":
                String keyword = parsed[1];
                TaskList filtered = taskList.filter(keyword);
                return Ui.getFilteredTasksMessage(filtered);

            case "bye":
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                }, 1500);
                return Ui.getGoodbyeMessage();

            case "list":
                return Ui.getTaskListMessage(taskList);

            default:
                throw new DukeException("Command Not Found!");
            }
        } catch (DukeException e) {
            return Ui.getDukeErrorMessage(e);
        }
    }
}
