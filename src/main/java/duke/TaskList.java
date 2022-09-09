package duke;

import java.time.LocalDate;

import java.util.ArrayList;


public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public int getLength() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public String addToDo(String task) {
        ToDo newToDo = new ToDo(false, task, tasks.size() + 1);
        tasks.add(newToDo);

        return newToDo.printAdded();
    }

    public String addDeadline(String task, LocalDate d1, String newTime) {
        Deadline newDeadline = new Deadline(false, task, tasks.size() + 1, d1, newTime);
        tasks.add(newDeadline);

        return newDeadline.printAdded();
    }

    public String addEvent(String task, LocalDate d1, String newTime) {
        Event newEvent = new Event(false, task, tasks.size() + 1, d1, newTime);
        tasks.add(newEvent);

        return newEvent.printAdded();
    }

    public String deleteTask(int index) {
        try {
            Task t = tasks.get(index - 1);
            String deleted = t.printDeleted();
            tasks.remove(index - 1);
            return deleted + "\n  Now you have " + tasks.size()+ " left\n";
        } catch (StringIndexOutOfBoundsException e) {
            return ui.printInsufficientInfoException(Ui.Keywords.delete);
        } catch (IndexOutOfBoundsException e) {
            return ui.printIndexOutOfBoundsException(Ui.Keywords.delete);
        }
    }

    public String markDone(int index) {
        Task t = tasks.get(index - 1);

        return t.markDone();
    }

    public String markUndone(int index) {
        Task t = tasks.get(index - 1);

        return t.markUndone();
    }

    public String findMatchingTask(String keyword) {

        String matches = "";

        if (tasks.isEmpty()) {
            return ui.printNoMatchingTask();
        }

        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                String taskStr = task.printTask();
                matches += taskStr + "\n";
            }
        }

        return matches;
    }

    public String printList() {
        if (tasks.isEmpty()) {
            return ui.printTaskListEmpty();
        } else {

            String list = "";
            int freshIndex = 1;

            for (Task task : tasks) {
                task.setIndex(freshIndex);
                freshIndex++;
            }

            for (Task task : tasks) {
                String newTask = task.printTask();
                list += newTask + "\n";
            }

            return list;
        }
    }

}
