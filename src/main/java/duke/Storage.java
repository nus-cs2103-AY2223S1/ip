package duke;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 * This clas is responsible for storing and loading the tasks
 *
 * @author Kang Zong Xian
 */
public class Storage {

    private String fileDestination;

    /**
     * Constructor for the Storage class
     * @param fileDestination the destination filepath of the file
     */
    public Storage(String fileDestination) throws IOException {
        Path filePath = Paths.get(fileDestination);
        this.fileDestination = fileDestination;
        if (Files.exists(filePath)) {
            return;
        }
        File newFile = new File(fileDestination);
        newFile.getParentFile().mkdir();
        newFile.createNewFile();
    }

    /**
     * Saves the tasks to the text file
     * @throws IOException exception to be thrown
     */
    public void saveTasks() throws IOException {
        List<Task> taskArrayList = TaskList.getTaskArrayList();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileDestination));
        String tasksString = "";
        for (Task task: taskArrayList) {
            tasksString += task.saveToDisk() + "\n";
        }

        bufferedWriter.write(tasksString);
        bufferedWriter.close();
    }

    /**
     * Loads the task to the TaskList array list
     * @throws IOException exception to be thrown regarding IOException
     * @throws DukeException
     */
    public void loadTasks() throws IOException, DukeException {
        List<Task> taskArrayList = TaskList.getTaskArrayList();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileDestination));
        String readByLine = bufferedReader.readLine();
        while (readByLine != null) {
            String taskDetails = readByLine;
            String[] keywords = readByLine.split(" ");
            Task currentTask = null;
            // Check what kind of task it is
            if (keywords[0].contains("T")) {
                // If user is trying to add a to-do, save the description
                // Have a default value in case the user did not add any description
                addTodo(keywords, taskArrayList);
            } else if (keywords[0].contains("D")) {
                addDeadline(keywords, taskArrayList);
            } else if (keywords[0].contains("E")) {
                addEvent(keywords, taskArrayList);
            } else {
                throw new DukeException("I'm sorry, I don't know what that means!");
            }

            readByLine = bufferedReader.readLine();
        }

        bufferedReader.close();
    }

    private void addTodo(String[] keywords, List<Task> taskArrayList) throws DukeException {
        String description = "";
        if (keywords.length > 1) {
            description = Parser.joinString(keywords, 1);
            description = description.substring(0, description.length() - 1);
        }
        Todo newTodo = new Todo(description);

        // 4 is the position of the marking if it exists
        if (keywords[0].indexOf("X") == 4) {
            newTodo.markAsDone();
        }
        taskArrayList.add(newTodo);
    }

    private void addDeadline(String[] keywords, List<Task> taskArrayList) throws DukeException {
        String remainingDescription = "";
        String description = "";
        String[] remainingWords = null;
        String[] dateTimeArray = null;
        String by = "";
        if (keywords.length > 1) {
            // Remaining description are the words after the task description
            remainingDescription = Parser.joinString(keywords, 1);
            remainingWords = remainingDescription.split("by: ");
            description = remainingWords[0].replace("(", "");
            description = description.substring(0, description.length() - 1);
            by = remainingWords[1];
            dateTimeArray = by.split(" ");
            // Cut down a white spacing at the end
            by = dateTimeArray[1];
            by = by.substring(0, by.length() - 1);
        }
        assert dateTimeArray != null;
        LocalDate byDate = Parser.createLocalDate(dateTimeArray[0].strip());
        Deadline newDeadline = new Deadline(description, byDate, by);

        // 4 is the position of the marking if it exists
        if (keywords[0].indexOf("X") == 4) {
            newDeadline.markAsDone();
        }
        taskArrayList.add(newDeadline);
    }

    private void addEvent(String[] keywords, List<Task> taskArrayList) throws DukeException {
        String description = "";
        String[] remainingWords;
        String at = "";
        String remainingDescription = "";
        if (keywords.length > 1) {
            // Remaining description are the words after the task description
            remainingDescription = Parser.joinString(keywords, 1);
            remainingWords = remainingDescription.split("at: ");
            description = remainingWords[0].replace("(", "");
            description = description.substring(0, description.length() - 1);
            at = remainingWords[1];
            // Cut down a white spacing and bracket at the end
            at = at.substring(0, at.length() - 2);
        }
        Event newEvent = new Event(description, at);

        // 4 is the position of the marking if it exists
        if (keywords[0].indexOf("X") == 4) {
            newEvent.markAsDone();
        }
        taskArrayList.add(newEvent);
    }

}
