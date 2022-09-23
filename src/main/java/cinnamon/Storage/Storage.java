package cinnamon.Storage;
import cinnamon.Exception.DukeException;
import cinnamon.Tasks.Deadline;
import cinnamon.Tasks.Event;
import cinnamon.Tasks.TaskList;
import cinnamon.Tasks.Todo;
import cinnamon.Tasks.Task;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores files and read files
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor for Storage
     *
     */
    public Storage(String fp) throws DukeException {
        this.filePath = fp;
        File file = new File(filePath);
        if (!file.exists()) {
            File newFile = new File("./Data");
            newFile.mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("failed to load file");
            }
        }

    }

    /**
     * Reads the file and store the tasks in file to the list
     *
     * @return ArrayList of type Task
     * @throws DukeException when there is no tasks created
     */
    public ArrayList<Task> loadTasks() throws DukeException, FileNotFoundException {
        ArrayList<Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(new File(this.filePath));
        while (sc.hasNext()) {
            String txt = sc.nextLine();

            if (txt.length() == 0) {
                throw new DukeException("You have not created nay tasks.");
            }
            String[] parts = txt.split("\\|");
            if (parts.length == 3) {
                Task todo = new Todo(parts[2]);
                checkMark(todo, Integer.parseInt(parts[1]));
                list.add(todo);
            } else if (txt.startsWith("D")) {
                Task dl = new Deadline(parts[2], parts[3], parts[4]);
                checkMark(dl, Integer.parseInt(parts[1]));
                list.add(dl);
            } else if (txt.startsWith("E")) {
                Task event = new Event(parts[2], parts[3], parts[4], parts[5]);
                checkMark(event, Integer.parseInt(parts[1]));
                list.add(event);
            }
        }
        return list;
    }


    /**
     * Checks if the task is done
     *
     * @param task the task to check if it is done
     * @param i if is 1 then mark task as done else mark as not done
     */
    public void checkMark(Task task, int i) {
        if (i == 0) {
            task.markAsNotDone();
        } else {
            task.markAsDone();
        }
    }

    /**
     * Writes current task to file
     *
     * @param tl list of tasks that is to be stored in file
     */
        public void writeTasks(TaskList tl) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (int i = 0; i < tl.size(); i++) {
                Task task = tl.listTasks().get(i);
                String str = task.fileFormat();
                fileWriter.write(str + "\n");
            }
           fileWriter.close();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Failed to save your file!.");
        }
    }
}