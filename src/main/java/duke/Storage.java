package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class Storage {

    private String fileDestination;

    /**
     * Constructor for the Storage class
     * @param fileDestination the destination filepath of the file
     */
    public Storage(String fileDestination) {
        this.fileDestination = fileDestination;
    }

    /**
     * Saves the tasks to the text file
     * @throws IOException exception to be thrown
     */
    public void saveTasks() throws IOException {
        FileWriter fileWriter = new FileWriter(fileDestination);
        String tasksString = "";
        for (Task task: TaskList.taskArrayList) {
            tasksString += task.toString() + "\n";
        }

        fileWriter.write(tasksString);
        fileWriter.close();
    }

    /**
     * Loads the task to the TaskList array list
     * @throws IOException exception to be thrown regarding IOException
     * @throws DukeException
     */
    public void loadTasks() throws IOException, DukeException {

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
                String description = "";
                if (keywords.length > 1) {
                    description = Parser.joinString(keywords, 1);
                    description = description.substring(0, description.length() - 1);
                }
                Todo newTodo = new Todo(description);

                if (keywords[0].indexOf("X") == 1) {
                    newTodo.markAsDone();
                }
                TaskList.taskArrayList.add(newTodo);
            } else if (keywords[0].contains("D")) {
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
                    by = remainingWords[1];
                    dateTimeArray = by.split(" ");
                    // Cut down a white spacing at the end
                    by = by.substring(0, by.length() - 1);
                }
                System.out.println(Arrays.toString(remainingWords));
                assert dateTimeArray != null;
                System.out.println(dateTimeArray[0].strip());
                LocalDate byDate = Parser.createLocalDate(dateTimeArray[0].strip());
                Deadline newDeadline = new Deadline(description, byDate, by);
                if (keywords[0].indexOf("X") == 1) {
                    newDeadline.markAsDone();
                }
                TaskList.taskArrayList.add(newDeadline);
            } else if (keywords[0].contains("E")) {
                String description = "";
                String[] remainingWords;
                String at = "";
                String remainingDescription = "";
                if (keywords.length > 1) {
                    // Remaining description are the words after the task description
                    remainingDescription = Parser.joinString(keywords, 1);
                    remainingWords = remainingDescription.split("at:");
                    description = remainingWords[0].replace("(", "");
                    at = remainingWords[1];
                    // Cut down a white spacing at the end
                    at = at.substring(0, at.length() - 1);
                }
                Event newEvent = new Event(description, at);
                if (keywords[0].indexOf("X") == 1) {
                    newEvent.markAsDone();
                }
                TaskList.taskArrayList.add(newEvent);
            } else {
                throw new DukeException("I'm sorry, I don't know what that means!");
            }

            readByLine = bufferedReader.readLine();
        }

        bufferedReader.close();
    }
}
