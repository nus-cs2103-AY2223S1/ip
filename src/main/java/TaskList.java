package duke;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> list;
    private static int noOfTasks;
    private Ui ui;

    TaskList(List<Task> pastList) {
        this.list = pastList;
        this.noOfTasks = pastList.size();
        this.ui = new Ui();
    }

    List<Task> getList() {
        return this.list;
    }

    void add(String s) {
        String[] strarr = s.split(" ");
        String typeOfTask = strarr[0];
        Task newTask = new Task(s);
        try {
            if (typeOfTask.equals("todo")) {
                String[] descriptionList1 = this.processDescription(strarr);
                newTask = new ToDo(descriptionList1[0]);
            } else if (typeOfTask.equals("deadline")) {
                String[] descriptionList2 = this.processDescription(strarr);
                newTask = new Deadline(descriptionList2[0],descriptionList2[1]);
            } else if (typeOfTask.equals("event")) {
                String[] descriptionList3 = this.processDescription(strarr);
                newTask = new Event(descriptionList3[0],descriptionList3[1]);
            }
            list.add(newTask);
            this.noOfTasks++;
            String addedTask = newTask.toString();
            ui.add(addedTask, this.noOfTasks);
        } catch(TaskWithNoDescriptionException ex) {
            System.err.print(ex);
        }
    }

    void delete(int taskNo) {
        Task currentTask = list.get(taskNo - 1);
        list.remove(taskNo - 1);
        noOfTasks--;
        String deletedTask = currentTask.toString();
        ui.delete(deletedTask, this.noOfTasks);
    }

    void getPrintedList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i < list.size() + 1; i++) {
            String currentTask = list.get(i - 1).toString();
            System.out.println(i + ". " + currentTask);
        }
    }

    void mark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.markAsDone();
        String markedTask = taskToBeModify.toString();
        ui.mark(markedTask);
    }

    void unmark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.markAsUndone();
        String unmarkedTask = taskToBeModify.toString();
        ui.unmark(unmarkedTask);
    }

    String[] processDescription(String[] strarr) throws TaskWithNoDescriptionException {
        if(strarr.length > 1) {
            String description = strarr[1];
            String date = "";
            String[] strarr1 = new String[2];
            for (int i = 2; i < strarr.length; i++) {
                if (strarr[i].equals("/by") || strarr[i].equals("/at")) {
                    date = strarr[i + 1];
                    break;
                } else {
                    description = description + " " + strarr[i];
                }
            }
            strarr1[0] = description;
            strarr1[1] = date;
            return strarr1;
        } else {
            throw new TaskWithNoDescriptionException(":( OOPS!!! The description of a "
                    + strarr[0] + " cannot be empty.");
        }
    }

}
