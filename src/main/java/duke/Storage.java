package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/** Represents a storage which saves the list of tasks. */
public class Storage {
    private String pathName;
    private File file;
    private TaskList previousTaskList;

    /**
     * Initialises the Storage object.
     *
     * @param pathName Path name of the text file where the list of tasks is stored.
     * @param previousTaskList ArrayList of tasks.
     * @throws IOException If an I/O error occurred.
     */
    public Storage(String pathName, TaskList previousTaskList) throws IOException {
        this.pathName = pathName;
        this.file = new File(pathName);
        this.previousTaskList = previousTaskList;

        //Create the taskList text file if it does not exist
        if (!Files.exists(Path.of(pathName))) {
            file.createNewFile();
        }
    }

    /**
     * Updates the text file where the list of tasks is stored.
     *
     * @throws IOException If an I/O error occurred.
     */
    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("taskList.txt", false);
        fw.write(this.previousTaskList.printList());
        fw.close();
    }

    /**
     * Loads the list of tasks from the text file.
     *
     * @throws IOException From loadEvent, loadDeadline and loadToDo methods.
     */
    public void loadUpData() throws IOException {
        //Initialise scanner object for the file
        Scanner sc = new Scanner(this.file);

        //Return empty task list
        if (!sc.hasNextLine()) {
            return;
        }

        //Load the list of tasks from the text file
        String header = sc.nextLine();
        while (sc.hasNextLine()) {
            //Get the next task
            String task = sc.nextLine();
            char type = task.charAt(3);
            char status = task.charAt(6);
            char priority = task.charAt(9);

            //Store task as Event and load it
            if (type == 'E') {
                this.loadEvent(task, status, priority);

            //Store task as Deadline and load it
            } else if (type == 'D') {
                this.loadDeadline(task, status, priority);

            //Store task as Todo and load it
            } else {
                assert type == 'T';
                this.loadToDo(task, status, priority);
            }
        }
    }

    /**
     * Loads the Event task from the file.
     *
     * @param task String containing the event task.
     * @param status char indicating if the task is marked as done or not.
     * @param priority char indicating priority of the task.
     * @throws IOException From writeToFile() method.
     */
    public void loadEvent(String task, char status, char priority) throws IOException {
        //Check that the loaded event has a date and time
        assert task.contains("[E]");

        //Get the description, date and time fields of event
        int at = task.indexOf("(at:");
        String dateAndTime = task.substring(at + 5, task.lastIndexOf(")"));

        //Add the task to the task list array and update the file contents
        this.previousTaskList.addTask(new Event(" " + task.substring(12, at - 1), dateAndTime));

        //Check if task is marked as done
        if (status == 'X') {
            this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
        }

        //Set priority of the task
        this.readAndSetPriority(this.previousTaskList.getTaskAtCurrentIndex(), priority);

        //Update the contents of the task list file
        this.writeToFile();

        //Increment the index of the array
        this.previousTaskList.incrementIndex();
    }

    /**
     * Sets priority of task read from the file.
     *
     * @param task Task that is read from the file.
     * @param priority char that indicates priority of the task.
     */
    public void readAndSetPriority(Task task, char priority) {
        if (priority == 'H') {
            task.setPriority("high");
        }

        if (priority == 'M') {
            task.setPriority("medium");
        }

        if (priority == 'L') {
            task.setPriority("low");
        }
    }

    /**
     * Loads the Deadline task from the file.
     *
     * @param task String containing the deadline task.
     * @param status char indicating if the task is marked as done or not.
     * @param priority char indicating priority of the task.
     * @throws IOException From the writeToFile() method.
     */
    public void loadDeadline(String task, char status, char priority) throws IOException {
        //Check if task is a deadline
        assert task.contains("[D]");

        //Get the description, date and time fields
        int by = task.indexOf("(by:");
        String timingWithBracket = task.substring(by + 5, task.lastIndexOf(")"));

        //Add the task to the task list array and update the file contents
        this.previousTaskList.addTask(new Deadline(" " + task.substring(12, by - 1), timingWithBracket));

        //Check if task is marked as done
        if (status == 'X') {
            this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
        }

        //Set priority of the task
        this.readAndSetPriority(this.previousTaskList.getTaskAtCurrentIndex(), priority);

        //Update the contents of the task list file
        this.writeToFile();

        //Increment the index of the array
        this.previousTaskList.incrementIndex();
    }

    /**
     * Loads the ToDo task from the file.
     *
     * @param task String containing the ToDo task.
     * @param status char indicating if the task is marked as done or not.
     * @param priority char indicating priority of the task.
     * @throws IOException From the writeToFile() method.
     */
    public void loadToDo(String task, char status, char priority) throws IOException {
        //Get description of ToDo
        String description = task.substring(12);

        //Add ToDo to task list
        this.previousTaskList.addTask(new ToDo(" " + description));

        //Check if task is marked as done
        if (status == 'X') {
            this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
        }

        this.readAndSetPriority(this.previousTaskList.getTaskAtCurrentIndex(), priority);

        //Update the contents of the task list file
        this.writeToFile();

        //Increment index of the task list
        this.previousTaskList.incrementIndex();
    }

    /**
     * Returns string of list of tasks with the keyword.
     *
     * @param keyword Keyword to find the relevant tasks.
     * @return List of tasks with the keyword.
     * @throws FileNotFoundException If file does not exist.
     */
    public String findTasks(String keyword) throws FileNotFoundException {
        //Initialise a scanner object
        Scanner scanner = new Scanner(this.file);

        //Create header for the list
        String output = "Here are the matching tasks in your list:\n";
        int index = 1;

        //Add the relevant tasks to the list
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(keyword)) {
                line = index + line.substring(1);
                output += (line + "\n");
                index += 1;
            }
        }
        return output;
    }
}

