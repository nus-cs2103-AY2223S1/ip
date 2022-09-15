package reminder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import task.Deadline;
import task.Task;
import task.TaskList;


/**
 * Reminder class that stores all reminders.
 */
public class Reminder {

    private final File file = new File("data/reminder.txt");

    private TaskList taskList = new TaskList();

    /**
     * Constructor to create Reminder object.
     */
    public Reminder() {
    }


    //At the end to add the Reminder tasks

    /**
     * Adds a Task to the reminder TaskList.
     * @param task The reminder task to add to the reminder TaskList.
     */
    public void addIfReminder(Task task) {
        if (task.getClass() == Deadline.class) {
            Deadline deadline = (Deadline) task;
            if (deadline.isDueSoon()) {
                this.taskList.add(task);
            }
        }
    }


    /**
     * Writes all the reminder task to reminder.txt file.
     */
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
