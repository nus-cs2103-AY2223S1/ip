package duke;

import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description task's description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * @return marked X if its done else dont mark
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Set isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Set isDone to false
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Use duke todo, duke.event, deadlines
     *
     * @param tasks the taskList where tasks are stored and loaded from
     * @param str description of the task
     */
    public static String makeTask(TaskList tasks, String str) throws DukeException, IOException {
        if(str.split(" ", 2)[0].equals("todo")) {

            if (str.equals("todo")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
                String result = str.substring(5);
                ToDo input = new ToDo(result);
                return Ui.addedTask(tasks, input);
        }
        else if(str.split(" ", 2)[0].equals("deadline")) {
            int index = str.indexOf("/");
            assert str.contains("/by"): "Please enter task in the format <desc> /by <date>";
            String taskNameWithType = str.substring(0, index - 1);
            String taskNameOnly = taskNameWithType.split(" ",2)[1];
            Deadline input = new Deadline(taskNameOnly);
            input.date = new FormatDate(str.substring(index + 4));
            return Ui.addedTask(tasks, input);
        }
        else if(str.split(" ", 2)[0].equals("event")) {
            int index = str.indexOf("/");
            assert str.contains("/at"): "Please enter task in the format <desc> /at <date>";
            String taskNameWithType = str.substring(0, index - 1);
            String taskNameOnly = taskNameWithType.split(" ",2)[1];
            Event input = new Event(taskNameOnly);
            input.day = new FormatDate(str.substring(index + 4));
            return Ui.addedTask(tasks, input);
        }
        throw new DukeException("Command not recognised.");
    }

    /**
     *
     * @param str the "find book" command to be passed in
     * @param tasks the taskList which we are searching from
     */
    public static String find(String str, TaskList tasks) {
        TaskList result = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(str.substring(5))) {
                result.add(task);
            }
        }
        return Ui.finderPrinter(result);
    }

    public static String reschedule(String str, Task task, TaskList taskList) throws DukeException, IOException {
        int slashIndex = str.indexOf("/");
        String newDate = str.substring(slashIndex + 1);
        FormatDate date = new FormatDate(newDate);
        if (task instanceof ToDo) {
            throw new DukeException("You cannot reschedule a todo :-(");
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            deadline.setDate(date);
        } else {
            Event event = (Event) task;
            event.setDay(date);
        }
        Storage.writeToFile(taskList);
        String result = "I've rescheduled: ";
        result += task.toString();
        return result;
    }
    public static String snoozeTask(String str, TaskList taskList) throws DukeException, IOException {
        String result = str.split(" ")[1];
        int taskIndex = Integer.parseInt(result.split("/")[0]);
        assert taskIndex > 0 && taskIndex <= taskList.size(): "Task index should be from 1 to length of list";
        if (1 <= taskIndex && taskIndex <= taskList.size()) {
            Task currTask = taskList.get(taskIndex - 1);
            return reschedule(str, currTask, taskList);
        } else {
            throw new DukeException("Invalid task number!");
        }
    }
}
