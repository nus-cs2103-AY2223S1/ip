package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/** A class that creates the Storage object. */
public class Storage {
    private String pathName;
    private File file;
    private TaskList previousTaskList;

    /**
     * A constructor that initialises the Storage object.
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
        if(!sc.hasNextLine()) {
            return;
        }

        //Load the list of tasks from the text file
        String header = sc.nextLine();
        while(sc.hasNextLine()) {
            //Get the next task
            String task = sc.nextLine();
            char type = task.charAt(3);
            char status = task.charAt(6);

            //Store task as Event and load it
            if (type == 'E') {
                this.loadEvent(task, type, status);

            //Store task as Deadline and load it
            } else if (type == 'D') {
                this.loadDeadline(task, type, status);

            //Store task as Todo and load it
            } else {
                this.loadToDo(task, type, status);
            }
        }
    }

    /**
     * Load the event task from the file.
     *
     * @param task String containing the event task.
     * @param type char indicating the type of task.
     * @param status char indicating if the task is marked as done or not.
     * @throws IOException From writeToFile() method.
     */
    public void loadEvent(String task, char type, char status) throws IOException {
        //Check that the loaded event has a date and time
        assert task.contains("/at");

        //Get the description and date and time fields of event
        int at = task.indexOf("(at:");
        String dateAndTime = task.substring(at + 5, task.lastIndexOf(")"));

        //Add the task to the task list array and update the file contents
        this.previousTaskList.addTask(new Event(" " + task.substring(9, at - 1), dateAndTime));

        //Check if task is marked as done
        if (status == 'X') {
            this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
        }

        //Update the contents of the task list file
        this.writeToFile();

        //Increment the index of the array
        this.previousTaskList.incrementIndex();
    }

    /**
     * Loads the deadline task from the file.
     *
     * @param task String containing the deadline task.
     * @param type char indicating the type of task.
     * @param status char indicating if the task is marked as done or not.
     * @throws IOException From the writeToFile() method.
     */
    public void loadDeadline(String task, char type, char status) throws IOException {
        //Check if task is a deadline
        assert task.contains("/by");

        //Get the description and date and time fields
        int by = task.indexOf("(by:");
        String timingWithBracket = task.substring(by + 5, task.lastIndexOf(")"));

        //Add the task to the task list array and update the file contents
        this.previousTaskList.addTask(new Deadline(" " + task.substring(9, by - 1), timingWithBracket));

        //Check if task is marked as done
        if (status == 'X') {
            this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
        }

        //Update the contents of the task list file
        this.writeToFile();

        //Increment the index of the array
        this.previousTaskList.incrementIndex();
    }

    /**
     * Loads the ToDo task from the file.
     *
     * @param task String containing the ToDo task.
     * @param type char indicating the type of task.
     * @param status char indicating if the task is marked as done or not.
     * @throws IOException From the writeToFile() method.
     */
    public void loadToDo(String task, char type, char status) throws IOException {
        //Check if task is a ToDo
        assert type == 'T';

        //Get description of ToDo
        String description = task.substring(9);

        //Add ToDo to task list
        this.previousTaskList.addTask(new ToDo(" " + description));

        //Check if task is marked as done
        if (status == 'X') {
            this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
        }

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
        while(scanner.hasNextLine()) {
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

