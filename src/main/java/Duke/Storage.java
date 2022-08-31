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
                String[] currLineArr = currLine.split(" \\| ");
                if (currLineArr[0].equals("T")) {
                    Todo td = new Todo(currLineArr[2]);
                    if (currLineArr[1].equals("1")) {
                        td.markAsDone();
                    }
                    listOfTasks.add(td);
                }
                if (currLineArr[0].equals("D")) {
                    LocalDate date = LocalDate.parse(currLineArr[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
                    String formattedDate = date.toString();
                    Deadline d = new Deadline(currLineArr[2], formattedDate);
                    if (currLineArr[1].equals("1")) {
                        d.markAsDone();
                    }
                    listOfTasks.add(d);
                }
                if (currLineArr[0].equals("E")) {
                    LocalDate date = LocalDate.parse(currLineArr[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
                    String formattedDate = date.toString();
                    Event e = new Event(currLineArr[2], formattedDate);
                    if (currLineArr[1].equals("1")) {
                        e.markAsDone();
                    }
                    listOfTasks.add(e);
                }
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
            String taskString = taskList.getTask(i).toString();
            String[] taskArr = taskString.split("]");
            String taskToString = "";
            if (taskArr[0].equals("[T")) {
                taskToString += "T | ";
                if (taskArr[1].equals("[X")) {
                    taskToString += "1 |";
                } else {
                    taskToString += "0 |";
                }
                taskToString += taskArr[2];
            }

            if (taskArr[0].equals("[D")) {
                taskToString += "D | ";
                if (taskArr[1].equals("[X")) {
                    taskToString += "1 |";
                } else {
                    taskToString += "0 |";
                }
                String[] subTaskArr = taskArr[2].split("\\(by:");
                taskToString += subTaskArr[0] + "|" + subTaskArr[1].substring(0, subTaskArr[1].length() - 1);
            }

            if (taskArr[0].equals("[E")) {
                taskToString += "E | ";
                if (taskArr[1].equals("[X")) {
                    taskToString += "1 |";
                } else {
                    taskToString += "0 |";
                }
                String[] subTaskArr = taskArr[2].split("\\(at:");
                taskToString += subTaskArr[0] + "|" + subTaskArr[1].substring(0, subTaskArr[1].length() - 1);
            }

            taskToString = taskToString + "\n";
            resultToWrite = resultToWrite + taskToString;
        }
        toWrite.write(resultToWrite);
        toWrite.close();
    }
}
