import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TaskReader {
    /**
     * Processes the input file into a list of tasks.
     *
     * @param filePath The file path
     * @return List of tasks inside the file path specified
     * @throws FileNotFoundException If file is not found
     */
    public static List<Task> convertToTaskList(String filePath)
            throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        List<Task> listOfTasks = new ArrayList<Task>();
        while (s.hasNext()) {
            String[] lineSplit = s.nextLine().split("\\|");
            switch (lineSplit[0].strip()) {
                case "T":
                    listOfTasks.add(new ToDo(lineSplit[2].strip()));
                    break;
                case "D":
                    listOfTasks.add(new Deadline(lineSplit[2].strip(), lineSplit[3].strip()));
                    break;
                case "E":
                    listOfTasks.add(new Event(lineSplit[2].strip(), lineSplit[3].strip()));
                    break;
                default:
                    break;
            }
            Task newlyAddedTask = listOfTasks.get(listOfTasks.size() - 1);
            if (lineSplit[1].strip().equals("1")) {
                newlyAddedTask.markAsDone();
            }
        }
        return listOfTasks;
    }

    /**
     * Creates the data file if one doesn't exist.
     *
     * @param filePath The file path of the data file
     * @throws IOException If there is an I/O issue
     */
    public void createDataFile(String filePath) throws IOException {
        String[] pathList = filePath.split("/");
        String currentDirectory = "";
        for (int i = 0; i < pathList.length - 1; i++) {
            currentDirectory += pathList[i];
            File directory = new File(currentDirectory);
            if (!directory.exists()) {
                directory.mkdir();
            }
            currentDirectory += "/";
        }
        File newFile = new File(filePath);
        newFile.createNewFile();
    }

    /**
     * (Re)writes the task list into the given filepath.
     *
     * @param taskList The list of tasks
     * @param filePath The destination file path
     * @throws IOException If any I/O issue happens
     */
    public void writeTaskListToFile(List<Task> taskList, String filePath)
        throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList) {
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }
}