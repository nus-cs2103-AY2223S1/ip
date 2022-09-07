package doke;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to represent a TaskList.
 *
 * @author Stevan Gerard Gunawan
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();
    public static final String specialString = " [|] ";

    private Task createTask(String[] strings) {
        Task addTask;
        if (strings[0].equals("T")) {
            addTask = new ToDo(strings[2]);
        } else if (strings[0].equals("D")) {
            addTask = new Deadline(strings[2], strings[3]);
        } else {
            addTask = new Event(strings[2], strings[3]);
        }
        return addTask;
    }

    private void setMarking(Task task, String[] strings) {
        if (Miscellaneous.equalsStringZero(strings[1])) {
            return;
        }
        task.markDone();
    }

    private void readStorage(Scanner scanner) {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] temp = line.split(specialString);
            Task task = createTask(temp);

            this.taskList.add(task);

            setMarking(task, temp);
        }
    }

    private void createNewDokeFile(Storage storage, Ui ui) {
        try {
            storage.DOKE_FILE.createNewFile();
            ui.printNewFileCreatedMessage();
        } catch (IOException a) {
            ui.printErrorMessage();
        }
    }

    /**
     * A public constructor for the TaskList class.
     * It will try to read and create and ArrayList of type task from storage,
     * which, if not possible would then create an empty ArrayList of type Task.
     *
     * @param ui
     * @param storage
     */
    public TaskList(Ui ui, Storage storage) {
        try {
            Scanner s = new Scanner(Storage.DOKE_FILE);
            readStorage(s);
        } catch (FileNotFoundException e) {
            createNewDokeFile(storage, ui);
        }
    }

    public void searchString(String str, Ui ui) {
        int len = taskList.size();
        int num = 0;
        String message = "_________________________ \n";
        int i = 0;
        message = "_________________________ \n";
        while (i < len) {
            Task task = taskList.get(i);
            String desc = task.getDesc().toLowerCase();
            String taskType = task.getType();
            if (desc.indexOf(str.toLowerCase()) != -1) {
                if (num == 0) {
                    message += "Here's what we found: \n";
                }
                message += (i + 1) + "." + taskList.get(i).toString() + "\n";
                num += 1;
            } else if ((taskType.equals("E") || taskType.equals("D"))
                    && task.getTime().format(
                            DateTimeFormatter.ofPattern("dd-MMM-yyyy")).indexOf(str) != -1) {
                if (num == 0) {
                    message += "Here's what we found: \n";
                }
                message += (i + 1) + "." + taskList.get(i).toString() + "\n";
                num += 1;
            }
            i++;
        }
        if (num == 0) {
            message += "Sorry, we found nothing. \n";
        }
        message += "_________________________ \n";
        ui.printOut(message);
    }

    /**
     * Sends order to ui to print out the whole content of the ArrayList.
     *
     * @param ui
     */
    public void listOut(Ui ui) {
        String message;
        if (this.taskList.isEmpty()) {
            message = "_________________________ \n" + "You have no task! \n"
                    + "________________________";
            ui.printOut(message);
            return;
        }
        int len = this.taskList.size();
        int i = 0;
        message = "_________________________ \n";
        while (i < len) {
            message += (i + 1) + "." + this.taskList.get(i).toString() + "\n";
            i++;
        }
        message += "_________________________ \n";
        ui.printOut(message);
    }

    /**
     * Deletes the ith task from the ArrayList.
     *
     * @param i the position + 1 of the task to delete
     */
    public void deleteTask(int i) {
        this.taskList.remove(i - 1);
    }

    /**
     * Adds a task to the ArrayList.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the Task at the position i-1 in the ArrayList.
     *
     * @param i the position + 1 of the task wanted.
     * @return the task desired.
     */
    public Task getTask(int i) {
        return taskList.get(i - 1);
    }

    /**
     * Returns a copy of the ArrayList.
     *
     * @return a copy of the ArrayList.
     */
    public ArrayList<Task> getList() {
        ArrayList<Task> temp = this.taskList;
        return temp;
    }

    /**
     * Returns the size of the ArrayList.
     *
     * @return the size of the ArrayList.
     */
    public int getSize() {
        return taskList.size();
    }

}
