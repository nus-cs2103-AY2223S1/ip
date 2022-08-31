package duke;

import duke.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskArr;

    public TaskList() {
        this.taskArr = new ArrayList<>();
    }
    public TaskList(ArrayList<String> taskArray) throws DukeException {
        this.taskArr = new ArrayList<>();
        if (taskArray == null) {
            throw new DukeException("Sorry, I encountered an error while trying to load your Task List :(");
        }
        for (int i = 0; i < taskArray.size(); i++) {
            String[] splitTask = taskArray.get(i).split(",", 2);
            addTask(splitTask[0], splitTask[1]);
        }
    }

    public void addTask(String keyword, String input) throws DukeException {
        if (keyword.equals("todo")) {
            Task todoTask = new Todo(input);
            taskArr.add(todoTask);
        } else {
            int slashChar = input.indexOf("/");
            String taskDesc = input.substring(0, slashChar);
            String deadlineInput = input.substring(slashChar + 1);
            if (taskDesc.isBlank() || deadlineInput.isBlank()) {
                throw new DukeException("oops, the description of your task seems to be incomplete!");
            } else {
                try {
                    String taskDate = input.substring(slashChar + 4);
                    LocalDate deadline = LocalDate.parse(taskDate);
                    if (keyword.equals("deadline")) {
                        taskArr.add(new Deadline(taskDesc,
                                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                    } else if (keyword.equals("event")) {
                        taskArr.add(new Event(taskDesc, deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
                    }
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteTask(String input, Ui ui) {
        Integer taskNo = Integer.valueOf(input) - 1;
        ui.showDeletingTask(this.taskArr.get(taskNo).toString());
        this.taskArr.remove(getTask(taskNo));
    }

    public void markTask(String keyword, String input, Ui ui) {
        Integer taskNo = Integer.valueOf(input) - 1;
        switch (keyword) {
            case "mark":
                this.taskArr.get(taskNo).markAsDone();
                ui.showMarkedTask(this.taskArr.get(taskNo).toString());
                break;
            case "unmark":
                this.taskArr.get(taskNo).markAsUndone();
                ui.showUnmarkedTask(this.taskArr.get(taskNo).toString());
        }
    }

    public Task getRecentTask() {
        return this.taskArr.get(taskArr.size() - 1);
    }

    public int getNoOfTasks() {
        return this.taskArr.size();
    }

    public Task getTask(int taskNo) {
        return this.taskArr.get(taskNo);
    }
}
