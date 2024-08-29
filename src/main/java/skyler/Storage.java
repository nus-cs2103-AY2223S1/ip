package skyler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents the interface that loads tasks from and saves tasks in the specified file
 */
public class Storage {

    int INDEX_OF_TASK_TYPE_IN_FILE = 0;
    int INDEX_OF_TASK_STATUS_IN_FILE = 4;
    int START_OF_DESCRIPTION_IN_FILE = 8;
    String SEPARATOR = "|";

    int INDEX_OF_TASK_TYPE_IN_TASK_TOSTRING = 1;
    int INDEX_OF_TASK_STATUS_IN_TASK_TOSTRING = 4;
    int START_OF_DESCRIPTION_IN_TASK_TOSTRING = 7;

    private String filePathChosen;

    /**
     * Creates a storage object
     *
     * @param filePathChosen Filepath for storage of task data, relative to project folder.
     */
    public Storage(String filePathChosen) {
        this.filePathChosen = filePathChosen;
        assert !this.filePathChosen.equals("") : "Filepath for storage of task data should be specified";
    }

    /**
     * Loads tasks from file and returns the corresponding list of tasks
     *
     * @return ArrayList of tasks previously stored in the file.
     * @throws SkylerException If directory or file not found.
     * @throws IOException If IO operation fails.
     */
    public ArrayList<Task> load() throws SkylerException, IOException {
        File file = getDataFile();
        ArrayList<Task> tasks = loadTasksFromFile(file);

        return tasks;
    }

    private File getDataFile() throws SkylerException, IOException {
        File folder = getFolderContainingDataFile();
        String chosenFileName = getChosenFileName();
        File dataFile = retrieveFileFromFolder(folder, chosenFileName);

        return dataFile;
    }

    private ArrayList<Task> loadTasksFromFile(File file) throws SkylerException, IOException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            String taskStr = sc.nextLine();

            // task parameters
            char taskType = taskStr.charAt(INDEX_OF_TASK_TYPE_IN_FILE);
            int taskStatus = Character.getNumericValue(taskStr.charAt(INDEX_OF_TASK_STATUS_IN_FILE));
            String taskDescription = taskStr.substring(START_OF_DESCRIPTION_IN_FILE);

            switch (taskType) {
            case 'T':
                Todo todo = new Todo(taskDescription);
                addTaskToArray(tasks, todo, taskStatus);
                break;
            case 'D':
                String[] descriptors = getTaskDescriptors(taskDescription);

                LocalDateTime dt = formatDateTime(descriptors[1]);
                Deadline deadline = new Deadline(descriptors[0], dt);
                addTaskToArray(tasks, deadline, taskStatus);
                break;
            case 'E':
                String[] descriptors1 = getTaskDescriptors(taskDescription);

                LocalDateTime dt1 = formatDateTime(descriptors1[1]);
                Event event = new Event(descriptors1[0], dt1);
                addTaskToArray(tasks, event, taskStatus);
                break;
            default:
                throw new SkylerException();
            }
        }
        return tasks;
    }

    //method below adapted from
    //https://www.geeksforgeeks.org/java-program-to-search-for-a-file-in-a-directory/ ,
    //https://www.educba.com/java-directories/ and
    //https://www.tutorialspoint.com/determine-if-file-or-directory-exists-in-java

    private File getFolderContainingDataFile() throws IOException {
        String[] dir = filePathChosen.split("/");
        String currPath = "";
        for (int i = 0; i < dir.length - 1; i++) {
            currPath += dir[i];
            String name = new File(currPath).getAbsolutePath();
            Path path = Paths.get(name);
            // if folder does not exist yet
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            currPath += "/";
        }

        String filePath = new File(currPath).getAbsolutePath();
        File directory = new File(filePath);
        return directory;
    }

    private String getChosenFileName() {
        String[] dir = filePathChosen.split("/");
        String file = dir[dir.length - 1];
        return file;
    }

    /**
     * Returns file of tasks from folder
     *
     * @param folder The folder containing the file.
     * @param chosenFileName The specified file name containing the tasks.
     * @return File of tasks.
     * @throws SkylerException If directory or file not found.
     * @throws IOException If IO operation fails.
     */
    private File retrieveFileFromFolder(File folder, String chosenFileName) throws SkylerException, IOException {
        File dataFile = new File("");

        String[] fileList = folder.list();
        boolean isFileFound = false;

        // directory is empty
        if (fileList == null) {
            String filePath = new File(filePathChosen).getAbsolutePath();
            dataFile = new File(filePath);
            dataFile.createNewFile();
            throw new SkylerException();
        }

        // directory is not empty, search for file
        for (int i = 0; i < fileList.length; i++) {
            String fileName = fileList[i];
            if (fileName.equals(chosenFileName)) {
                String filePath = new File(filePathChosen).getAbsolutePath();
                dataFile = new File(filePath);
                isFileFound = true;
            }
        }

        // directory is not empty but specified file not found
        if (!isFileFound) {
            String filePath = new File(filePathChosen).getAbsolutePath();
            dataFile = new File(filePath);
            dataFile.createNewFile();
            throw new SkylerException();
        }
        return dataFile;
    }

    private void addTaskToArray(ArrayList<Task> taskArray, Task task, int taskStatus) {
        if (taskStatus == 1) {
            task.markAsDone();
        }
        taskArray.add(task);
    }

    private String[] getTaskDescriptors(String taskDescription) {
        String pattern = String.format(" [%s] ", SEPARATOR);
        Pattern p = Pattern.compile(pattern);
        String[] descriptors = p.split(taskDescription);
        return descriptors;
    }

    /**
     * Formats date and time information and returns a LocalDateTime object
     *
     * @param strDateTime String representation of date and time.
     * @return Corresponding LocalDateTime object.
     */
    public static LocalDateTime formatDateTime(String strDateTime) {
        String[] timeInfo = strDateTime.split(" ", 2);

        String unformattedTime = timeInfo[1];
        String hour = unformattedTime.substring(0, 2);
        String minute = unformattedTime.substring(2, 4);
        String formattedTime = String.format("%s:%s", hour, minute);

        LocalDate date = LocalDate.parse(timeInfo[0]);
        LocalTime time = LocalTime.parse(formattedTime);
        return LocalDateTime.of(date, time);
    }

    /**
     * Saves tasks to file
     *
     * @param task ArrayList of tasks to save to file.
     * @throws IOException If IO operation fails.
     */
    public void saveTask(ArrayList<Task> task) throws IOException {
        String filePath = new File(filePathChosen).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath);

        for (Task currTask : task) {
            String taskStr = currTask.toStringUnformatted();

            // task parameters
            char taskType = taskStr.charAt(INDEX_OF_TASK_TYPE_IN_TASK_TOSTRING);
            int taskStatus = taskStr.charAt(INDEX_OF_TASK_STATUS_IN_TASK_TOSTRING) == 'X' ? 1 : 0;
            String taskDescription = taskStr.substring(START_OF_DESCRIPTION_IN_TASK_TOSTRING);

            StringBuilder output = new StringBuilder();
            String str = String.format("%c | %d | ", taskType, taskStatus);
            output.append(str);

            switch (taskType) {
            case 'T':
                output.append(taskDescription).append(System.lineSeparator());
                break;
            case 'D':
                int strLen = taskDescription.length();
                Pattern p = Pattern.compile(" [(]by: ");
                String[] descriptors = p.split(taskDescription.substring(0, strLen - 1));
                String str1 = String.format("%s | %s", descriptors[0], descriptors[1]);
                output.append(str1).append(System.lineSeparator());
                break;
            case 'E':
                int strLen1 = taskDescription.length();
                Pattern p1 = Pattern.compile(" [(]at: ");
                String[] descriptors1 = p1.split(taskDescription.substring(0, strLen1 - 1));
                String str2 = String.format("%s | %s", descriptors1[0], descriptors1[1]);
                output.append(str2).append(System.lineSeparator());
                break;
            default:
            }
            fw.write(output.toString());
            fw.flush();
        }

        fw.close();
    }

    /**
     * Returns a list of task indices whose corresponding task contains the given keyword
     *
     * @param keyword Keyword to search for.
     * @return List of task indices containing keyword.
     */
    public ArrayList<Integer> findTask(String keyword) {
        // return arraylist of task indices
        ArrayList<Integer> taskIndices = new ArrayList<>();
        File file = new File(filePathChosen);
        try {
            Scanner sc = new Scanner(file);
            int task = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains(keyword)) {
                    taskIndices.add(task);
                }
                task++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return taskIndices;
    }

}
