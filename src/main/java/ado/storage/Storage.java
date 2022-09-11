package ado.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ado.AdoException;
import ado.task.Deadline;
import ado.task.Event;
import ado.task.Task;
import ado.task.Todo;

/**
 * Handles loading and saving tasks in the file.
 */
public class Storage {
    private boolean isNewUser = false;
    private final String filePath;

    /**
     * Creates a new directory/file if it does not exist.
     * @param filePath directory of where tasks are saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        String[] pathSegment = filePath.split("/");
        try {
            File storageFolder = new File(pathSegment[0]);
            File storageFile = new File(this.filePath);

            //make a data folder if it does not exist
            if (!storageFolder.exists()) {
                storageFolder.mkdir();
            }

            //create task.txt if it does not exist
            if (!storageFile.exists()) {
                isNewUser = true;
                storageFile.createNewFile();
                //Reused from https://www.w3schools.com/java/java_files_create.asp with minor modifications
                try {
                    FileWriter myWriter = new FileWriter(this.filePath);
                    myWriter.write("T|X|Feed cat\n" + "E| |Team meeting|2022-09-22@19:30\n"
                            + "D| |Do assignment|2022-10-02");
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("Error in inserting sample data" + e.getMessage());
                }
            }

        } catch (IOException error) {
            System.out.println("Error in Storage constructor:" + error.getMessage());
        }
    }

    /**
     * Loads saved task from data.txt to a list.
     * @return previously saved tasks.
     * @throws AdoException If error loading file.
     */
    public List<Task> load() throws AdoException {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            BufferedReader storageReader = new BufferedReader(new FileReader(this.filePath));
            String savedTaskString = storageReader.readLine();

            while (savedTaskString != null) {
                addTaskFromString(savedTaskString, loadedTasks);
                savedTaskString = storageReader.readLine();
            }
            storageReader.close();
        } catch (IOException error) {
            System.out.println("error loading:" + error.getMessage());
        }
        return loadedTasks;
    }

    /**
     * Converts string to task object to be added to array of tasks.
     * @param savedTaskString
     */
    public void addTaskFromString(String savedTaskString, List<Task> loadedTasks) {

        String[] taskSegment = savedTaskString.split("\\|");
        String taskType = taskSegment[0];
        String taskStatus = taskSegment[1];
        String taskDescription = taskSegment[2];

        //To store date from deadline tasks
        LocalDate taskLocalDate = null;
        LocalDateTime taskLocalDateTime = null;

        boolean isDeadlineTask = taskSegment.length >= 4 && taskType.equals("D");
        boolean isEventTask = taskSegment.length >= 4 && taskType.equals("E");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        if (isDeadlineTask) {
            try {
                String date = stringToMediumDateFormat(taskSegment[3]);
                taskLocalDate = LocalDate.parse(date, formatterDate);
            } catch (Exception ex) {
                System.out.println("Error in converting deadline date " + ex.getMessage());
            }
        } else if (isEventTask) {
            String[] dateSplit = taskSegment[3].split("@", 2);
            try {
                String date = stringToMediumDateFormat(dateSplit[0]);
                taskLocalDate = LocalDate.parse(date, formatterDate);

                String[] timeSplit = dateSplit[1].split(":", 2);
                taskLocalDateTime = taskLocalDate
                        .atTime(Integer.parseInt(timeSplit[0]), Integer.parseInt(timeSplit[1]));
            } catch (Exception ex) {
                System.out.println("Error in converting event date " + ex.getMessage());
            }
        }

        Task currentSavedTask = null;
        switch(taskType) {
        case "T":
            currentSavedTask = new Todo(taskDescription);
            break;
        case "D":
            currentSavedTask = new Deadline(taskDescription, taskLocalDate);
            break;
        case "E":
            currentSavedTask = new Event(taskDescription, taskLocalDateTime);
            break;
        default: assert false : "Unknown taskType: " + taskType;
            break;
        }

        if (taskStatus.equals("X")) {
            currentSavedTask.markAsDone();
        }

        loadedTasks.add(currentSavedTask);
    }

    /**
     * Converts string from data.txt to a parsable string to LocalDate.
     * @param date from data.txt.
     * @return parsable string to LocalDate.
     */
    public String stringToMediumDateFormat(String date) {
        LocalDate tempDate = LocalDate.parse(date);
        String formattedDate = tempDate.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM));
        formattedDate.replace("-", " ");
        return formattedDate;
    }

    /**
     * Saves all existing tasks into data.txt.
     * @param taskToSave list of tasks to be saved.
     */
    public void saveTasksInStorage(List<Task> taskToSave) {
        try {
            FileWriter storageWriter = new FileWriter(this.filePath);
            for (Task task : taskToSave) {
                storageWriter.write(task.toStringForStorage() + "\n");
            }
            storageWriter.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    /**
     * Returns true if user is new and false otherwise.
     * @return true if it is a new user.
     */
    public boolean checkIfNewUser() {
        return isNewUser;
    }
}
