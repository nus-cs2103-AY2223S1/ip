package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;



/**
 * Represents class that stores and loads tasks
 *
 * @author benjytan45678
 * @version 0.1
 */

public class Storage {
    private File file;
    private String filepath;

    /**
     * Creates a storage object of the specified file path.
     *
     * @param filepath Path from root directory to the file.
     * @throws DukeException If unable to create file.
     */
    public Storage(String filepath) throws DukeException {
        this.filepath = filepath;
        File newFile = new File(filepath);
        if (!newFile.exists()) {
            try {
                boolean result = newFile.createNewFile();
                if (result) {
                    throw new DukeException("New file created since no duke.txt is found");
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.file = newFile;
    }

    /**
     * Returns a list of tasks from file.
     *
     * @return A list of tasks.
     * @throws DukeException If file is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String message = sc.nextLine();
                String[] messageArr = message.split("#");
                if (messageArr[0].equals("T")) {
                    ToDo todo = new ToDo(messageArr[2]);
                    if (messageArr[1].equals("1")) {
                        todo.setCompleted();
                    }
                    taskList.add(todo);
                }

                if (messageArr[0].equals("D")) {
                    Deadline deadline = new Deadline(messageArr[2], messageArr[3], messageArr[4]);
                    if (messageArr[1].equals("1")) {
                        deadline.setCompleted();
                    }
                    taskList.add(deadline);
                }

                if (messageArr[0].equals("E")) {
                    Event event = new Event(messageArr[2], messageArr[3], messageArr[4]);
                    if (messageArr[1].equals("1")) {
                        event.setCompleted();
                    }
                    taskList.add(event);
                }
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        }
        return taskList;

    }

    /**
     * Saves specified list of tasks to local file.
     *
     * @param taskList the list of tasks
     */
    public void store(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filepath);
            String message = "";
            for (Task item : taskList) {
                message += item.parse();
                message += "\n";
            }
            fw.write(message);
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
