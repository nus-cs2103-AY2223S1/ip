package jean.task;

import jean.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task task, Ui ui) {
        this.taskList.add(task);
        ui.printMessage("\tadded / ajouté:"
                + "\n\t\t" + task.toString());
        ui.printMessage("\tYou now have " + this.taskList.size() + " task(s)!"
                + "\n\tVous avez " + this.taskList.size() + " tâche(s)!");
    }

    public void deleteTask(int taskIndex, Ui ui) {
        Task curr = this.taskList.get(taskIndex);
        this.taskList.remove(taskIndex);
        ui.printMessage("\tI have deleted the task:"
                + "\n\tJe l'ai supprimé:"
                + "\n\t" + curr.toString()
                + "\n\tYou now have " + taskList.size() + " tasks remaining!"
                + "\n\tIl vous reste maintenant " + taskList.size() + " tâches!");
    }

    public void listTask(Ui ui) {
        int i = 0;
        for (Task x : this.taskList) {
            ui.printMessage("\t" + (++i) + ".\t " + x.toString());
        }
    }

    public void markTask(int taskIndex, Ui ui) {
        Task curr = this.taskList.get(taskIndex);
        curr.setIsDone(true);
        ui.printMessage("\tI have marked it as done:"
                + "\n\tJe l'ai marqué comme fait:"
                + "\n\t" + curr.toString());
    }

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
