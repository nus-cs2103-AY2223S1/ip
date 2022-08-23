package jean.task;

import jean.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Constructs the TaskList object with previous tasks from memory.
     *
     * @param taskList List of tasks obtained from memory.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks currently in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a tasks into the task list.
     *
     * @param task The task to be added.
     * @param ui The ui object that prints feedback to the user interface.
     */
    public void addTask(Task task, Ui ui) {
        this.taskList.add(task);
        ui.printMessage("\tadded / ajouté:"
                        + "\n\t\t" + task.toString());
        ui.printMessage("\tYou now have " + this.taskList.size() + " task(s)!"
                        + "\n\tVous avez " + this.taskList.size() + " tâche(s)!");
    }


    /**
     * Removes a task from the task list.
     *
     * @param taskIndex The index of the task to be removed.
     * @param ui The ui object that prints feedback to the user interface.
     */
    public void deleteTask(int taskIndex, Ui ui) {
        Task curr = this.taskList.get(taskIndex);
        this.taskList.remove(taskIndex);
        ui.printMessage("\tI have deleted the task:"
                        + "\n\tJe l'ai supprimé:"
                        + "\n\t" + curr.toString()
                        + "\n\tYou now have " + taskList.size() + " tasks remaining!"
                        + "\n\tIl vous reste maintenant " + taskList.size() + " tâches!");
    }

    /**
     * Lists all the tasks in the task list.
     *
     * @param ui The ui object that prints feedback to the user interface.
     */
    public void listTask(Ui ui) {
        int i = 0;
        for (Task x : this.taskList) {
            ui.printMessage("\t" + (++i) + ".\t " + x.toString());
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex The index of the task to be marked.
     * @param ui The ui object that prints feedback to the user interface.
     */
    public void markTask(int taskIndex, Ui ui) {
        Task curr = this.taskList.get(taskIndex);
        curr.setIsDone(true);
        ui.printMessage("\tI have marked it as done:"
                        + "\n\tJe l'ai marqué comme fait:"
                        + "\n\t" + curr.toString());
    }


    /**
     * Unmarks the status a task as not done.
     *
     * @param taskIndex The index of the task to be unmarked.
     * @param ui The ui object that prints feedback to the user interface.
     */
    public void unmarkTask(int taskIndex, Ui ui) {
        Task curr = this.taskList.get(taskIndex);
        curr.setIsDone(false);
        ui.printMessage("\tI have marked it as undone:"
                        + "\n\tJe l'ai marqué comme défait:"
                        + "\n\t" + curr.toString());
    }

    public void findTask(String keyword, Ui ui) {
        ArrayList<Task> matches = new ArrayList<Task>();
        for (Task x : this.taskList) {
            if (x.toString().contains(keyword)) {
                matches.add(x);
            }
        }
        if (matches.isEmpty()) {
            ui.printMessage("There are no matches!"
                            + "\nIl n'y a aucune correspondance !");
        } else {
            ui.printMessage("There are " + matches.size() + " matches!"
                            + "\nIl y a " + matches.size() + " correspondances!");
        }

        int i = 0;
        for (Task x : matches) {
            ui.printMessage("\t" + (++i) + ".\t " + x.toString());
        }
    }
}
