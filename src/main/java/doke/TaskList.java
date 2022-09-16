package doke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.util.Pair;


/**
 * A class to represent a TaskList.
 *
 * @author Stevan Gerard Gunawan
 */
public class TaskList {

    public static final String SPECIALSTRING = " [|] ";
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructs a taskList object.
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
        assert strings[1].equals("1") : "If the doneness is not 0, it should be 1";
        task.markDone();
    }

    private void readStorage(Scanner scanner) {
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] temp = line.split(SPECIALSTRING);
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

    private Pair<Integer, String> taskSearcher(Ui ui, int num, int i, String str) {
        Task task = taskList.get(i);
        String desc = task.getDesc().toLowerCase();
        if (desc.indexOf(str.toLowerCase()) == -1) {
            return new Pair<>(0, "");
        } else if (num == 0) {
            ui.printOut("Here's what we found: ");
        }
        ui.printOut((i + 1) + "." + taskList.get(i).toString());
        return new Pair<>(1, "\n" + (i + 1) + "." + taskList.get(i).toString());
    }

    private Pair<Integer, String> taskSearchIterator(String str, String message, Ui ui) {
        int i = 0;
        int num = 0;
        int len = taskList.size();
        while (i < len) {
            Pair<Integer, String> temp = taskSearcher(ui, num, i, str);
            num += temp.getKey();
            message += temp.getValue();
            i++;
        }
        return new Pair<>(num, message + "\n");
    }

    /**
     * Searches and prints out the tasks which contains the string in its description.
     *
     * @param str
     * @param ui
     */
    public String searchString(String str, Ui ui) {
        int num = 0;
        String message = "_________________________ ";
        ui.printOut(message);
        Pair<Integer, String> temp = taskSearchIterator(str, message, ui);
        num = temp.getKey();
        if (num == 0) {
            ui.printOut("Sorry, we found nothing.");
            return "Sorry, we found nothing.";
        }
        ui.printOut("_________________________ ");
        message += temp.getValue() + "\n";
        return message;
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
        assert i >= 0 : "the index can't be smaller than 0";
        assert i <= taskList.size() : "we can't delete non-existent task";
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
     * Retrieves the current taskList.
     *
     * @return the ArrayList.
     */
    protected ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Returns the size of the ArrayList.
     *
     * @return the size of the ArrayList.
     */
    public int getSize() {
        return taskList.size();
    }

    @Override
    public String toString() {
        String message;
        if (this.taskList.isEmpty()) {
            message = "You have no task! \n";
            return message;
        }
        int len = this.taskList.size();
        int i = 0;
        message = "_________________________ \n";
        while (i < len) {
            message += (i + 1) + "." + this.taskList.get(i).toString() + "\n";
            i++;
        }
        message += "_________________________ \n";
        return message;
    }
}
