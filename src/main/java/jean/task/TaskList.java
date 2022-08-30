package jean.task;

import java.util.ArrayList;

import jean.ui.Ui;

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
     * @return The string to be printed.
     */
    public String addTask(Task task, Ui ui) {
        this.taskList.add(task);
        String message = ("\tadded / ajouté:"
                          + "\n\t\t" + task.toString()
                          + "\n\tYou now have " + this.taskList.size() + " task(s)!"
                          + "\n\tVous avez " + this.taskList.size() + " tâche(s)!");
        ui.printMessage(message);
        return message;
    }


    /**
     * Removes a task from the task list.
     *
     * @param taskIndex The index of the task to be removed.
     * @param ui The ui object that prints feedback to the user interface.
     * @return The string to be printed.
     */
    public String deleteTask(int taskIndex, Ui ui) {
        Task curr = this.taskList.get(taskIndex);
        this.taskList.remove(taskIndex);
        String message = ("\tI have deleted the task:"
                          + "\n\tJe l'ai supprimé:"
                          + "\n\t\t" + curr.toString()
                          + "\n\tYou now have " + taskList.size() + " tasks remaining!"
                          + "\n\tIl vous reste maintenant " + taskList.size() + " tâches!");
        ui.printMessage(message);
        return message;
    }

    /**
     * Lists all the tasks in the task list.
     *
     * @param ui The ui object that prints feedback to the user interface.
     * @return The string to be printed.
     */
    public String listTask(Ui ui) {
        StringBuilder message = new StringBuilder();
        if (this.taskList.size() == 0) {
            String next = ("There are no tasks!"
                          + "\nIl n'y a pas de tâches!");
            ui.printMessage(next);
            message.append(next);
            return message.toString();
        }
        String next = ("Here are your tasks!"
                       + "\nVoici vos tâches!\n");
        ui.printMessage(next);
        message.append(next);
        for (int i = 0; i < this.taskList.size(); i++) {
            next = ("\t" + (i + 1) + ".\t " + this.taskList.get(i).toString());
            ui.printMessage(next);
            message.append(next);
            if (i != this.taskList.size() - 1) {
                message.append("\n");
            }
        }
        return message.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex The index of the task to be marked.
     * @param ui The ui object that prints feedback to the user interface.
     * @return The string to be printed.
     */
    public String markTask(int taskIndex, Ui ui) {
        Task curr = this.taskList.get(taskIndex);
        curr.setIsDone(true);
        String message = ("\tI have marked it as done:"
                          + "\n\tJe l'ai marqué comme fait:"
                          + "\n\t\t" + curr.toString());
        ui.printMessage(message);
        return message;
    }


    /**
     * Unmarks the status a task as not done.
     *
     * @param taskIndex The index of the task to be unmarked.
     * @param ui The ui object that prints feedback to the user interface.
     * @return The string to be printed.
     */
    public String unmarkTask(int taskIndex, Ui ui) {
        Task curr = this.taskList.get(taskIndex);
        curr.setIsDone(false);
        String message = ("\tI have marked it as undone:"
                          + "\n\tJe l'ai marqué comme défait:"
                          + "\n\t\t" + curr.toString());
        ui.printMessage(message);
        return message;
    }

    /**
     * Finds tasks with the corresponding keyword.
     *
     * @param keyword The keyword to match.
     * @param ui The ui object that prints feedback to the user interface.
     * @return The string to be printed.
     */
    public String findTask(String keyword, Ui ui) {
        ArrayList<Task> matches = new ArrayList<Task>();
        for (Task x : this.taskList) {
            if (x.toString().contains(keyword)) {
                matches.add(x);
            }
        }
        StringBuilder message = new StringBuilder();
        String toPrint;
        if (matches.isEmpty()) {
            toPrint = ("There are no matches!"
                       + "\nIl n'y a aucune correspondance !");
        } else {
            toPrint = ("There are " + matches.size() + " matches!"
                       + "\nIl y a " + matches.size() + " correspondances!");
        }
        ui.printMessage(toPrint);
        message.append(toPrint);

        if (matches.size() > 0) {
            message.append("\n");
        }

        for (int i = 0; i < matches.size(); i++) {
            toPrint = ("\t" + (i + 1) + ".\t " + matches.get(i).toString());
            ui.printMessage(toPrint);
            message.append(toPrint);
            if (i != matches.size() - 1) {
                message.append("\n");
            }
        }
        return message.toString();
    }
}
