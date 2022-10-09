package duke.storage;

import duke.DukeException;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.TaskList;
import duke.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Loads our stored data and saves updated tasks into the file
 *  Handles exceptions where the file does not exist and creates a new file to write into
 */
public class Storage {
    private String pathFile;

    public Storage(String pathFile) {
        this.pathFile = pathFile;
    }

    /**
     * Introduce a method to mark the d
     * @param task
     * @param status
     */
    public void updateTaskSatus(Task task, int status) {
        if (status == 1) {
            task.mark();
        }
    }

    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<Task>();
        try {
            File taskFile = new File(pathFile);
            Scanner taskReader = new Scanner(taskFile);
            while (taskReader.hasNextLine()) {
                // we first read the input from our stored file and parse it into a string array
                String data = taskReader.nextLine();
                String[] parts = data.split("\\|");

                //we then assign variables to each part of our input
                //The task type specifies the class it belongs to
                //Status tells us if the task is done or not
                String taskType = parts[0];
                int status = Integer.valueOf(parts[1]);
                String task = parts[2];

                switch (taskType) {
                case "D":
                    Deadline newDeadline = new Deadline(task, Parser.parseDateTime(data));
                    tasks.add(newDeadline);
                    updateTaskSatus(newDeadline, status);
                    break;

                case "E":
                    Event newEvent = new Event(task, Parser.parseDateTime(data));
                    tasks.add(newEvent);
                    updateTaskSatus(newEvent, status);
                    break;

                case "T":
                    Todo newTodo = new Todo(task);
                    tasks.add(newTodo);
                    updateTaskSatus(newTodo, status);
                    break;
                }
            }
            taskReader.close();
        } catch (FileNotFoundException e) {
            try {
                //@@ryanlml JordanChua - reused
                // Reused a snippet of the code to create duke.txt and its parent folders
                //if we cannot find the file we simply create a new file
                // along with the parent directories to the file
                File taskFile = new File("./data/duke.txt");
                if (!taskFile.exists()) {
                    Files.createDirectories(Paths.get("./data/duke.txt").getParent());
                    taskFile.createNewFile();
                }
            } catch (IOException ex) {
                //Print the error if the new file cannot be created in the specific filepath
                System.out.println("File cannot be created!");
                ex.printStackTrace();
            }
        } finally {
            return tasks;
        }
    }

    /**
     * Write back to our file to update our task list by manually overwriting
     * manually overwriting our current file content with the updated task list
     * @param tasks updated task list
     * @throws DukeException if file cannot be written to
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            PrintWriter prw = new PrintWriter(new File(pathFile));
            for (Task task : tasks.getTasks()) {
                prw.println(task.writeToFile());
            }
            prw.close();
        } catch (Exception E) {
            System.out.println("File cannot be written to!");
        }
    }
}
