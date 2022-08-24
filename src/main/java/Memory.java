import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class encapsulates the idea of a memory/taskLists of the chatbot.
 */
public class Memory {
    //this is where the tasks are stored
    private ArrayList<Task> taskLists;
    //this is the physical file saving the items
    private static final File storageFile = new File("C:\\Users\\xudao\\OneDrive" +
            "\\Documents\\NUS FILES\\year 2\\sem 1\\cs2103t\\git\\ip\\storage.txt");

    private BufferedReader reader;
    private BufferedWriter writer;

    /**
     * Constructor for the memory
     */
    public Memory() {
        this.taskLists = new ArrayList<Task>();
        readData();
    }

    /**
     * Returns the task with the given index.
     * @param index index of the task you want to retrieve
     * @return the stored task
     */
    public Task getTask(int index) { return taskLists.get(index);
    }

    /**
     * Returns the number of tasks stored
     * @return number of tasks stored
     */
    public int getNumOfTask() {
        return taskLists.size();
    }

    private boolean checkValidIndex(int index) {
        return index > -1 && index < taskLists.size();
    }

    //reads data in text file and saves it in taskLists
    private void readData() {
        try {
            this.reader = new BufferedReader(new FileReader(storageFile));
            String currentLine;
            String description;
            boolean status;
            String time;
            while((currentLine = reader.readLine()) != null) {
                String type = currentLine.substring(0, 1);
                status = currentLine.substring(4, 5).equals("T");
                switch(type) {
                    case "T":
                        taskLists.add(new ToDo(currentLine.substring(8), status));
                        break;
                    case "D":
                        int divider = currentLine.substring(8).indexOf("|") + 8;
                        taskLists.add(new Deadline(currentLine.substring(8, divider - 1),
                                status, currentLine.substring(divider + 2)));
                        break;
                    case "E":
                        int divider_2 = currentLine.substring(8).indexOf("|") + 8;
                        taskLists.add(new Event(currentLine.substring(8, divider_2 - 1),
                                status, currentLine.substring(divider_2 + 2)));
                        break;
                }
            }
            reader.close();
        }
        catch(IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }

    //copies data stored in taskLists into external file
    private void transferData() {
        try {
            for (int i = 0; i < taskLists.size(); i++) {
                this.writer = new BufferedWriter(new FileWriter(storageFile));
                writer.write(taskLists.get(i).getDescription());
            }
            writer.close();
        }
        catch(IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }

    /**
     * Saves the given task.
     * @param task given task
     */
    public void saveTask(Task task) {
        taskLists.add(task);
        try {
            this.writer = new BufferedWriter(new FileWriter(storageFile, true));
            writer.write(task.getDescription());
            writer.close();
        }
        catch(IOException e) {
            System.out.print("Invalid Path for storage file");
        }
    }

    /**
     * Marks the task as completed.
     * @param index given index of the task
     * @throws DukeException
     */
    public void markTask(int index) throws DukeException {
        if (checkValidIndex(index)) {
            Task current = taskLists.get(index);
            if (current.getStatus()) {
                throw new DukeException("☹ OOPS!!! The task you want to mark is already marked.");
            }
            current.markasDone();
            transferData();
            String content = "Nice! I've marked this task as done:\n" + current.toString();
            System.out.println(Duke.wrapper(content));
        } else {
            throw new DukeException("☹ OOPS!!! The task you want to mark is not here.");
        }
    }

    /**
     * Marks the task as incomplete.
     * @param index given index of the task
     * @throws DukeException
     */
    public void unMarkTask(int index) throws DukeException {
        if (index > -1 && index < taskLists.size()) {
            Task current = taskLists.get(index);
            if (!current.getStatus()) {
                throw new DukeException("☹ OOPS!!! The task you want to mark is already marked.");
            }
            current.markasNotDone();
            transferData();
            String content = "OK, I've marked this task as not done yet:\n" + current.toString();
            System.out.println(Duke.wrapper(content));
        } else {
            throw new DukeException("☹ OOPS!!! The task you want to mark is not here.");
        }
    }

    /**
     * Deletes the tasks with the given index
     * @param index given index
     * @throws DukeException
     */
    public void deleteTask(int index) throws DukeException {
        if (checkValidIndex(index)) {
            Task deletedTask = taskLists.get(index);
            taskLists.remove(index);
            transferData();
            String content = "Noted. I've removed this task:\n" + deletedTask.toString()
                    + numOfTaskToString();
            System.out.println(Duke.wrapper(content));
        } else {
            throw new DukeException("☹ OOPS!!! The The task you want to delete is not here.");
        }

    }

    /**
     * Returns the string representation of the stored tasks.
     * @return a table listing all the stored tasks
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < taskLists.size(); i ++) {
            Task current = taskLists.get(i);
            output = output + String.valueOf(i + 1) + "." + current.toString();
            if (i != taskLists.size() - 1) {
                output = output + "\n";
            }
        }
        return output;
    }

    /**
     * Returns a string detailing how many tasks are stored.
     * @return a string
     */
    public String numOfTaskToString() {
        return "\n" + "Now you have " + String.valueOf(taskLists.size()) + " tasks in the list.";
    }
}
