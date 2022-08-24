import java.io.File;
import java.io.FileNotFoundException;
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
     * @throws DukeException If the parsing is not successful
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
                    listOfTasks.add(new ToDos(lineSplit[2].strip()));
                    break;
                case "D":
                    listOfTasks.add(new Deadlines(lineSplit[2].strip(), lineSplit[3].strip()));
                    break;
                case "E":
                    listOfTasks.add(new Events(lineSplit[2].strip(), lineSplit[3].strip()));
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
}