package duke.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import duke.tasks.Task;


public class Storage {

    /**
     * Constructor for storage class which initiates file saving process to store user input.
     * @param taskList
     * @param filePath
     */
    public Storage(TaskList taskList, String filePath) throws IOException {
        saveFile(taskList, filePath);
    }

    /**
     * Adds tasks from tasks array in Duke.main into a file.
     * A new file will be created if no existing files with the specified pathname
     * exists currently.
     *
     * @param taskList A Task array which contains the list of tasks inputted by the user.
     * @param pathname The path which the user wants to store their file containing their lists
     *                 of task in.
     * @throws IOException
     */
    public static void saveFile(TaskList taskList, String pathname) throws IOException {

        try {
            Task[] taskArr = taskList.getTaskArr();

            File dukeFile = new File(pathname);
            dukeFile.createNewFile();
            Scanner a = new Scanner(dukeFile);

            FileWriter fileWriter = new FileWriter(pathname);
            for (int i = 1; i <= Task.getNumberTasks(); i++) {
                fileWriter.write(taskArr[i].output() + "\n");
            }
            fileWriter.close();
        } catch (IOException ioException) {
            Ui.printFileSavingError();
        }

    }

    /**
     * Prints all tasks in the file with the specified file path as a string
     *
     * @param filePath The file path containing the file which in turn contains the list
     *                 of tasks the user wants to retrieve.
     * @throws FileNotFoundException
     */
    public static void printFile(String filePath) throws FileNotFoundException {
        File dukeFile = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(dukeFile); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void loadFile(String filePath) throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File dukeFile = new File(filePath);
        Scanner s = new Scanner(dukeFile);
        int i = 1;
        while (s.hasNext()) {
            taskList.getTaskArr()[i] = new Task(s.nextLine());
            i++;
        }
    }
}
