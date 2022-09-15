package belly;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processor.TaskDatetimeFormatter;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tobtob.TobTobException;

/**
 * Represents a storage system to load data from or save data to local hard disk.
 */
public class Belly {
    public static final String FILE_DIRECTORY = "data/";
    public static final String FILE_NAME = "tobtob.txt";
    public static final String FILE_PATH = FILE_DIRECTORY + FILE_NAME;

    /**
     * Returns an {@link ArrayList} of {@link Task}s inside the file in the specified path.
     *
     * @return {@link ArrayList}&lt;{@link Task}&gt;
     * @throws TobTobException If there is no file found in the path
     */
    public ArrayList<Task> puke() throws TobTobException {
        try {
            File file = new File(FILE_PATH);
            Scanner sc = new Scanner(file);
            ArrayList<Task> taskList = new ArrayList<>();

            String[] taskDetails;
            Task task;
            while (sc.hasNextLine()) {
                taskDetails = sc.nextLine().split(" @@ ");

                String taskCode = taskDetails[0];
                String taskIsDone = taskDetails[1];
                String taskName = taskDetails[2];
                String taskDatetimeString;

                switch (taskCode) {
                case "T":
                    task = new Todo(taskIsDone, taskName);
                    break;
                case "D":
                    taskDatetimeString = taskDetails[3];
                    task = new Deadline(taskIsDone, taskName,
                            TaskDatetimeFormatter.convertStringToDatetime(taskDatetimeString));
                    break;
                default:
                    taskDatetimeString = taskDetails[3];
                    task = new Event(taskIsDone, taskName,
                            TaskDatetimeFormatter.convertStringToDatetime(taskDatetimeString));
                }

                taskList.add(task);
            }
            sc.close();

            return taskList;
        } catch (FileNotFoundException e) {
            throw TobTobException.fileNotFoundError(FILE_PATH);
        } catch (IndexOutOfBoundsException e) {
            throw TobTobException.fileLoadingError(FILE_PATH);
        }
    }

    /**
     * Saves {@code txt} input to hard disk.
     *
     * @param txt {@link String} that will be saved
     */
    public void saveToHardDisk(String txt) {
        try {
            Files.createDirectories(Paths.get(FILE_DIRECTORY));
            File file = new File(FILE_PATH);
            file.createNewFile();

            PrintWriter out = new PrintWriter(FILE_PATH);
            if (txt.equals("")) {
                file.delete();
            } else {
                out.println(txt);
            }
            out.close();
        } catch (IOException e) {
            ;
        }
    }
}
