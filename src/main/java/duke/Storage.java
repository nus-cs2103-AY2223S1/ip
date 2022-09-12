package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads tasks from file or saves tasks into file.
 *
 * @author Lai Han Wen
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath Name of file where tasks are saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the specified file into an arraylist. Throws
     * DukeException if file does not exist.
     *
     * @return Arraylist of tasks loaded from file.
     * @throws DukeException if file does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>(); // To be returned

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String line = sc.nextLine();
                char typeOfTask = line.charAt(1);
                char statusIcon = line.charAt(4);
                boolean isDone = (statusIcon == 'X');

                switch (typeOfTask) {
                case 'T':
                    tasks.add(this.getTodo(line, isDone));
                    break;
                case 'D':
                    tasks.add(this.getDeadline(line, isDone));
                    break;
                case 'E':
                    tasks.add(this.getEvent(line, isDone));
                    break;
                default:
                    assert false; // Execution should never reach this point!
                }
            }

        } catch (FileNotFoundException e) {
            throw new DukeException();
        }

        return tasks;
    }

    private Task getTodo(String line, boolean isDone) {
        Task t = new Todo(this.getTodoDescription(line));
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    private Task getDeadline(String line, boolean isDone) {
        Task t = new Deadline(this.getDescription(line), this.getTime(line));
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    private Task getEvent(String line, boolean isDone) {
        Task t = new Event(this.getDescription(line), this.getTime(line));
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    private String getTodoDescription(String line) {
        int desStartIndex = 7;
        return line.substring(desStartIndex);
    }

    private String getDescription(String line) {
        int desStartIndex = 7;
        int desEndIndex = line.indexOf('(') - 1;
        return line.substring(desStartIndex, desEndIndex);
    }

    private String getTime(String line) {
        int timeStartIndex = line.indexOf('(') + 5;
        int timeEndIndex = line.indexOf(')');
        return line.substring(timeStartIndex, timeEndIndex);
    }

    /**
     * Saves the given list of tasks in the specified file.
     *
     * @param list TaskList to be saved in the file.
     */
    public void save(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.flush();
            ArrayList<Task> currList = list.getList();

            for (Task t : currList) {
                boolean isDeadline = (t.toString().charAt(1) == 'D');

                // Date of deadline saved must be in format yyyy-mm-dd; e.g. 2020-10-10
                if (isDeadline) {
                    Deadline d = (Deadline) t;
                    fw.write(d.toStringOri() + "\n");
                    continue;
                }

                fw.write(t + "\n");
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates folder if folder does not exist, and does nothing if
     * folder already exists.
     */
    public void createFolder() {
        String dir = this.filePath;
        String tempDir;

        while (dir.contains("/")) {
            tempDir = dir.substring(0, filePath.indexOf('/')); // Get first directory
            Path folder = Paths.get(tempDir);
            dir = dir.substring(dir.indexOf('/') + 1); // Get next directory

            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                System.err.println("Failed to create folder!" + e.getMessage());
            }

        }
    }

    /**
     * Creates file in folder if file does not exist, and
     * does nothing if file already exists.
     */
    public void createFile() {
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Failed to create file!" + e.getMessage());
        }
    }

    /**
     * Returns true if the given task exists in the current list of tasks,
     * false otherwise. Todo is duplicate if description is identical,
     * deadline and event is duplicate if both description and time is
     * identical.
     *
     * @param typeOfTask The type of task; "T" for todo, "D" for deadline, "E"
     *                   for event.
     * @param arr String array from parseCommand method.
     * @return True if task already exists, false otherwise.
     */
    public boolean isDuplicateTask(String typeOfTask, String[] arr) {
        File file = new File(this.filePath);
        assert file.exists();
        boolean isDuplicate = false;

        try {
            Scanner sc = new Scanner(file);

            loop:
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String typeOfTaskInList = String.valueOf(line.charAt(1));
                boolean isSameTaskType = typeOfTaskInList.equals(typeOfTask);

                if (!isSameTaskType) {
                    continue;
                }

                switch (typeOfTask) {
                case "T":
                    if (this.isDuplicateTodo(arr, line)) {
                        isDuplicate = true;
                        break loop;
                    }
                    break;
                case "D":
                case "E":
                    if (this.isDuplicateDeadlineOrEvent(arr, line)) {
                        isDuplicate = true;
                        break loop;
                    }
                    break;
                default:
                    assert false; // Execution should never reach this point!
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return isDuplicate;
    }

    private boolean isDuplicateTodo(String[] arr, String line) {
        String description = this.getTodoDescription(line);
        String newDescription = arr[1];
        return description.equals(newDescription);
    }

    private boolean isDuplicateDeadlineOrEvent(String[] arr, String line) {
        String description = this.getDescription(line);
        String time = this.getTime(line);
        String newDescription = arr[1];
        String newTime = arr[2];
        return description.equals(newDescription) &&
                time.equals(newTime);
    }
}
