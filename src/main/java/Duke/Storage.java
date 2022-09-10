package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage object class.
 */
public class Storage {

    private File file;

    /**
     * Creates a Storage object.
     *
     * @param filePath The path of the file where the list of tasks is stored in a .txt file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Creates file and subdirectories leading to the file if they have not been created.
     *
     * @param file The specified path to the file.
     * @throws DukeException when a subdirectory or the file is unable to be created.
     */
    private static void createFileAndSubdirectoriesIfFileNotFound(File file) throws DukeException {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new DukeException("Unable to create storage file");
        }
    }

    /**
     * Decodes task from storage file and returns the corresponding task.
     *
     * @param taskData The specified task data.
     * @return The corresponding task based on the taskData.
     */
    public static Task decodeTask(String taskData) {

        String[] currLineArr = taskData.split(" \\| ");
        String taskType = currLineArr[0];
        String taskDone = currLineArr[1];
        Task decodeTask;
        assert (taskType.equals("T") || taskType.equals("D") || taskType.equals("E"))
                : "Type of task is invalid in data file";
        assert (taskDone.equals("1") || taskDone.equals("0")) : "Flag for done is invalid in data file!";

        if (taskType.equals("T")) {
            decodeTask = new Todo(currLineArr[2]);
        } else if (taskType.equals("D")) {
            LocalDate date = LocalDate.parse(currLineArr[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
            String formattedDate = date.toString();
            decodeTask = new Deadline(currLineArr[2], formattedDate);
        } else {
            LocalDate date = LocalDate.parse(currLineArr[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
            String formattedDate = date.toString();
            decodeTask = new Event(currLineArr[2], formattedDate);
        }
        if (taskDone.equals("1")) {
            decodeTask.markAsDone();
        }
        return decodeTask;
    }

    /**
     * Writes task to storage file.
     *
     * @param taskString String of task data.
     * @return The corresponding string of task data.
     */
    public static String writeTask(String taskString) {

        String[] taskArr = taskString.split("]");
        String taskType = taskArr[0];
        String taskDone = taskArr[1];
        String taskDescription = taskArr[2];
        String taskWritten = "";
        assert (taskType.equals("[T") || taskType.equals("[D") || taskType.equals("[E"))
                : "Type of task is invalid in data file";
        assert (taskDone.equals("[X") || taskDone.equals("[ ")) : "Flag for done is invalid in data file!";

        if (taskType.equals("[T")) {
            taskWritten += "T | ";
            taskWritten += taskDone.equals("[X") ? "1 |" : "0 |";
            taskWritten += taskDescription;
        } else if (taskType.equals("[D")) {
            taskWritten += "D | ";
            taskWritten += taskDone.equals("[X") ? "1 |" : "0 |";
            String[] subTaskArr = taskDescription.split("\\(by:");
            taskWritten += subTaskArr[0] + "|" + subTaskArr[1].substring(0, subTaskArr[1].length() - 1);
        } else {
            taskWritten += "E | ";
            taskWritten += taskDone.equals("[X") ? "1 |" : "0 |";
            String[] subTaskArr = taskDescription.split("\\(at:");
            taskWritten += subTaskArr[0] + "|" + subTaskArr[1].substring(0, subTaskArr[1].length() - 1);
        }
        taskWritten = taskWritten + "\n";
        return taskWritten;
    }

    /**
     * Loads the ArrayList of Tasks from duke.txt.
     *
     * @return ArrayList of Tasks.
     * @throws FileNotFoundException when file is not found.
     * @throws DukeException         when command is invalid.
     */
    public ArrayList<Task> load() throws DukeException {

        ArrayList<Task> listOfTasks = new ArrayList<>();

        try {
            createFileAndSubdirectoriesIfFileNotFound(this.file);
            Scanner fsc = new Scanner(this.file);
            while (fsc.hasNextLine()) {
                String currLine = fsc.nextLine();
                listOfTasks.add(decodeTask(currLine));
            }
        } catch (IOException e) {
            throw new DukeException("Error opening files");
        }
        return listOfTasks;
    }

    /**
     * Writes taskList to duke.txt.
     *
     * @param taskList that is to be written to file.
     * @throws IOException when file does not exist.
     */
    public void writeToTextFile(TaskList taskList) throws IOException {

        PrintWriter toWrite = new PrintWriter(this.file);
        String resultToWrite = "";

        for (int i = 0; i < taskList.getSize(); i++) {
            String taskString = taskList.getTaskAsString(i);
            String taskWritten = writeTask(taskString);
            resultToWrite = resultToWrite + taskWritten;
        }
        toWrite.write(resultToWrite);
        toWrite.close();
    }
}
