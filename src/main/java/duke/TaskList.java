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

    public int length() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addToDo(String task) {
        ToDo newToDo = new ToDo(false, task, tasks.size() + 1);
        tasks.add(newToDo);

        newToDo.printAdded();
    }

    public void addDeadline(String task, LocalDate d1, String newTime) {
        Deadline newDeadline = new Deadline(false, task, tasks.size() + 1, d1, newTime);
        tasks.add(newDeadline);

        newDeadline.printAdded();
    }

    public void addEvent(String task, LocalDate d1, String newTime) {
        Event newEvent = new Event(false, task, tasks.size() + 1, d1, newTime);
        tasks.add(newEvent);

        newEvent.printAdded();
    }

    public void deleteTask(int index) {
        try {
            Task t = tasks.get(index);
            t.printDeleted();
            tasks.remove(index - 1);
            System.out.println("  Now you have " + (tasks.size() - 1)+ " left\n" + ui.straightLine + "\n");
        } catch (StringIndexOutOfBoundsException e) {
            ui.printInsufficientInfoException(Ui.Keywords.delete);
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundsException(Ui.Keywords.delete);
        }
    }

    public void markDone(int index) {
        Task t = tasks.get(index - 1);

        t.markDone();
    }

    public void markUndone(int index) {
        Task t = tasks.get(index - 1);

        t.markUndone();
    }

    public void printList() {
        if (tasks.isEmpty()) {
            ui.printTaskListEmpty();
        } else {

            System.out.println(ui.straightLine);

            int freshIndex = 1;

            for (Task task : tasks) {
                task.setIndex(freshIndex);
                freshIndex++;
            }

            for (Task task : tasks) {
                task.printTask();
            }

            System.out.println(ui.straightLine + "\n");
        }
    }

}
