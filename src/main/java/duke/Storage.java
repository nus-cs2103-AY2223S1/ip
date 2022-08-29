package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods that deal with storing and loading Tasks from memory.
 */
public class Storage {
    private String filePath;
    private String tempFilePath;

    /**
     * Constructor for creating Storage.
     *
     * @param filePath The filePath to the file that stores Tasks in a .txt file.
     * @param tempFilePath The filePath for a temporary file for use when main file has to be rewritten.
     */
    public Storage(String filePath, String tempFilePath) {
        this.filePath = filePath;
        this.tempFilePath = tempFilePath;
    }

    /**
     * Creates files for storage of Tasks if they do not exist. If they exist, returns list of Tasks.
     *
     * @return An ArrayList of Tasks added previously.
     */
    public ArrayList<Task> load() {
        // Ensure file exists
        File hardDiskTasks = new File(filePath);
        File tempTasks = new File(tempFilePath);
        try {
            hardDiskTasks.createNewFile();
            tempTasks.createNewFile();
        } catch (IOException | SecurityException e) {
            System.out.println("     " + e.getMessage());
        }

        // Add disk info to taskList
        ArrayList<Task> pastTasks = loadTasksFromDisk(hardDiskTasks);
        return pastTasks;
    }

    /**
     * Loads Tasks previously added from memory and adds into an ArrayList.
     *
     * @param file File which contains Tasks added previously.
     * @return ArrayList of Tasks which were added previously.
     */
    public ArrayList<Task> loadTasksFromDisk(File file) {
        ArrayList<Task> pastTasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String memo = s.nextLine();

                // first letter to identify task
                String task = memo.substring(0, 1);

                // Retrieve task status
                int indexOfFirstBreak = memo.indexOf("|");
                String status = memo.substring(indexOfFirstBreak + 2, indexOfFirstBreak + 3);
                boolean statusIsDone = status.equals("1");

                // skip "| x | " to get task description
                String descriptionAndTime = memo.substring(indexOfFirstBreak + 6);
                int indexOfThirdBreak = descriptionAndTime.indexOf("|");
                String description = descriptionAndTime;
                String time = "";

                // if time exists, retrieve time and update task description
                if (indexOfThirdBreak != -1) {
                    description = descriptionAndTime.substring(0, indexOfThirdBreak - 1);
                    time = descriptionAndTime.substring(indexOfThirdBreak + 2);
                }

                // update list
                Task currentTask = createTask(task, description, time, statusIsDone);
                pastTasks.add(currentTask);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("     " + e.getMessage());
        }
        return pastTasks;
    }

    /**
     * Creates a Task object using information retrieved from memory.
     *
     * @param task Task identifier.
     * @param description Task description.
     * @param time Time associated with Task.
     * @param isDone Status of Task.
     * @return Task object.
     */
    public Task createTask(String task, String description, String time, boolean isDone) {
        Task newTask = null;
        LocalDate date = null;
        if (time != "") {
            date = LocalDate.parse(time);
        }
        switch (task) {
        case "T":
            newTask = new ToDo(description);
            break;
        case "D":
            newTask = new Deadline(description, date);
            break;
        case "E":
            newTask = new Event(description, date);
            break;
        default:
            throw new DukeException("OOPS!!! The Disk memory is invalid");
        }
        newTask.setTaskStatus(isDone);
        return newTask;
    }

    /**
     * Stores Task information onto memory.
     *
     * @param task Task information.
     * @throws IOException if unable to write to file.
     */
    public void addTaskToDisk(String task) throws IOException {
        appendToFile(filePath, task);
    }

    /**
     * Rewrites the .txt file that contains all the tasks added in order to reflect changes in a task status.
     *
     * @param taskNumber the row of Task which changed status.
     * @param isDone the new status of the task.
     * @throws IOException if unable to write to file.
     */
    public void setTaskStatusOnDisk(int taskNumber, boolean isDone)throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File(tempFilePath);
        Scanner s = new Scanner(inputFile);
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            if (taskNumber == 1) {
                // before status "x | description"
                int indexOfFirstBreak = currentLine.indexOf("|");
                String beforeStatus = currentLine.substring(0, indexOfFirstBreak + 2);

                // after " X | x"
                String afterStatus = currentLine.substring(indexOfFirstBreak + 3);
                String status = isDone ? "1" : "0";
                currentLine = beforeStatus + status + afterStatus;
            }
            appendToFile(tempFilePath, currentLine + System.lineSeparator());
            taskNumber -= 1;
        }
        s.close();
        boolean successful = tempFile.renameTo(inputFile);
    }

    /**
     * Removes Task information from memory.
     *
     * @param taskNumber Row of Task to delete from memory.
     * @throws IOException if unable to write to file.
     */
    public void deleteTaskFromDisk(int taskNumber) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File(tempFilePath);
        Scanner s = new Scanner(inputFile);
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            if (taskNumber != 1) {
                appendToFile(tempFilePath, currentLine + System.lineSeparator());
            }
            taskNumber -= 1;
        }
        s.close();
        boolean successful = tempFile.renameTo(inputFile);
    }

    /**
     * Appends String to a file.
     *
     * @param file The filepath of file to be appended.
     * @param textToAdd The String to be appended to file.
     * @throws IOException if unable to write to file.
     */
    public void appendToFile(String file, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(textToAdd);
        fw.close();
    }
}
