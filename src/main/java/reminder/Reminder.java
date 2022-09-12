package reminder;

import task.Deadline;
import task.Task;
import task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Reminder {

    private File file = new File("data/reminder.txt");

    private TaskList taskList = new TaskList();

    public Reminder() {
    }

    public Reminder(TaskList taskLists) {
        for (Task t : taskLists.getTaskList()) {
            addIfReminder(t);
        }
    }


    //At the end to add the Reminder tasks

    public void addIfReminder(Task task) {
        if (task.getClass() == Deadline.class) {
            Deadline deadline = (Deadline) task;
            if (deadline.isDueSoon()) {
                this.taskList.add(task);
            }
        }
    }

    public void writeAllReminder() {
        ArrayList<Task> listOfActions = this.taskList.getTaskList();
        try {
            FileWriter writer = new FileWriter(this.file.getPath());
            for (Task t : listOfActions) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops");
        }
    }
}
