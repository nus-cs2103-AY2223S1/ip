package duke.oop;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    /**
     * A constructor to specify the path
     * @param filePath the path to save the file and load the file from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return List of tasks.
     * @throws DukeException  If there is an error during file parsing.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] splitInput = input.split(" \\| ");

                switch (splitInput[0]) {
                case "T":
                    Todo todo = new Todo(splitInput[2]);
                    if (Integer.parseInt(splitInput[1]) == 1) {
                        todo.mark();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(splitInput[2], splitInput[3]);
                    if (Integer.parseInt(splitInput[1]) == 1) {
                        deadline.mark();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(splitInput[2], splitInput[3]);
                    if (Integer.parseInt(splitInput[1]) == 1) {
                        event.mark();
                    }
                    tasks.add(event);
                    break;
                default:
                    throw new DukeException("Task is of unknown type :(");
                }
            }
        } catch (IOException e) {
            throw new DukeException("An IOException occurred!! :(");
        } catch (NumberFormatException e) {
            throw new DukeException("An error occurred during file parsing, unexpected done value encountered.");
        }

        return tasks;
    }

    /**
     * Updates the file
     * @param str The strings to be written in the file
     */
    public void update(String str) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(str);
            fw.close();
        }
        // Catch block to handle the exception
        catch (IOException ex) {
            System.out.print("Invalid Path");
        }
    }
}
