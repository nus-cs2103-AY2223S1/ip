package duke;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the list of task managed by the ChatBot.
 */
public class TaskList {

    private List<Task> list;
    private static int noOfTasks;
    private Ui ui;
    private static HashMap<String, List<Task>> taskByKeyword;

    /**
     * Create a tasklist.
     * @param pastList
     */
    TaskList(List<Task> pastList) {
        this.list = pastList;
        this.noOfTasks = pastList.size();
        this.ui = new Ui();
        this.taskByKeyword = new HashMap<String, List<Task>>();
        for (int i = 0; i < pastList.size(); i++) {
            Task pastTask = pastList.get(i);
            String stringDescription = pastTask.getTaskDescription();
            updateKeyword(stringDescription, pastTask);
        }
    }

    List<Task> getList() {
        return this.list;
    }

    /**
     * Add new task from the users into the existing taskList.
     * Keep track of the number of tasks in the list.
     * @param s Task given by the users
     */
    void add(String s) {
        String[] strarr = s.split(" ");
        String typeOfTask = strarr[0];
        Task newTask = new Task(s);
        try {
            if (typeOfTask.equals("todo")) {
                String[] descriptionList1 = this.processDescription(strarr);
                newTask = new ToDo(descriptionList1[0]);
                updateKeyword(descriptionList1[0], newTask);
            } else if (typeOfTask.equals("deadline")) {
                String[] descriptionList2 = this.processDescription(strarr);
                newTask = new Deadline(descriptionList2[0],descriptionList2[1]);
                updateKeyword(descriptionList2[0], newTask);
            } else if (typeOfTask.equals("event")) {
                String[] descriptionList3 = this.processDescription(strarr);
                newTask = new Event(descriptionList3[0],descriptionList3[1]);
                updateKeyword(descriptionList3[0], newTask);
            }
            list.add(newTask);
            this.noOfTasks++;
            String addedTask = newTask.toString();
            ui.add(addedTask, this.noOfTasks);
        } catch (TaskWithNoDescriptionException ex) {
            System.err.print(ex);
        }
    }

    void updateKeyword(String s, Task newTask) {
        String[] strarr = s.split(" ");
        for (int i = 0; i < strarr.length; i++) {
            if (taskByKeyword.containsKey(strarr[i])) {
                taskByKeyword.get(strarr[i]).add(newTask);
            } else {
                List<Task> listForHashMap = new ArrayList<Task>();
                listForHashMap.add(newTask);
                taskByKeyword.put(strarr[i], listForHashMap);
            }
        }
    }

    /**
     * Delete the task with this taskNo from the list.
     * @param taskNo TaskNo given by the users.
     */
    void delete(int taskNo) {
        Task currentTask = list.get(taskNo - 1);
        String currentTaskDescription = currentTask.getTaskDescription();
        removeTaskFromKeyword(currentTaskDescription, currentTask);
        list.remove(taskNo - 1);
        noOfTasks--;
        String deletedTask = currentTask.toString();
        ui.delete(deletedTask, this.noOfTasks);
    }


    void removeTaskFromKeyword(String s, Task deletedTask) {
        String[] strarr = s.split(" ");
        for (int i = 0; i < strarr.length; i++) {
            if (taskByKeyword.get(strarr[i]).size() == 1) {
                taskByKeyword.remove(strarr[i]);
            } else {
                taskByKeyword.get(strarr[i]).remove(deletedTask);
            }
        }
    }

    void findTaskWithThisKeyword(String s) {
        System.out.println("Here are the matching tasks in your list:");
        List<Task> tasks = taskByKeyword.get(s);
        for (int i = 1; i < tasks.size() + 1; i++) {
            String matchingTask = tasks.get(i - 1).toString();
            System.out.println(i + ". " + matchingTask);
        }
    }

    /**
     * Print out the existing list of tasks for the users.
     */
    void getPrintedList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < list.size() + 1; i++) {
            String currentTask = list.get(i - 1).toString();
            System.out.println(i + ". " + currentTask);
        }
    }

    /**
     * Mark the task with this taskNo as done.
     * @param taskNo TaskNo given by the users.
     */
    void mark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.markAsDone();
        String markedTask = taskToBeModify.toString();
        ui.mark(markedTask);
    }

    /**
     * Unmark the task with this taskNo.
     * @param taskNo TaskNo given by the users.
     */
    void unmark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.markAsUndone();
        String unmarkedTask = taskToBeModify.toString();
        ui.unmark(unmarkedTask);
    }

    private String[] processDescription(String[] strarr) throws TaskWithNoDescriptionException {
        if (strarr.length > 1) {
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
