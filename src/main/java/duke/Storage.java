package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

import java.io.File;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /** File that is saved */
    private File dataFile;

    public Storage(String filePath) {
        this.dataFile = new File(filePath);
    }

    /**
     * Loads data from the hard disk.
     *
     * @return list of tasks saved previously.
     * @throws DukeException if the any of the tasks saved are not ToDos or Deadline or Event.
     * @throws FileNotFoundException if file is not found in user's hard disk.
     */
    public List<Task> load() throws DukeException, FileNotFoundException {

        List<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(dataFile);

        while (scanner.hasNext()) {
            String string = scanner.nextLine();
            String[] token = string.split(Pattern.quote(" | "));

            String firstLetter = token[0];
            boolean isDone = token[1].equals("1");
            String taskContent = token[2];

            switch (firstLetter) {
            case "T":
                ToDos todo = new ToDos(taskContent);
                if (isDone) {
                    todo.setDone(true);
                }
                taskList.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(taskContent, token[3]);
                if (isDone) {
                    deadline.setDone(true);
                }
                taskList.add(deadline);
                break;
            case "E":
                Event event = new Event(taskContent, token[3]);
                if (isDone) {
                    event.setDone(true);
                }
                taskList.add(event);
                break;
            default:
                throw new DukeException();
            }
        }
        return taskList;
    }

    /**
     * Saves the tasks in the hard disk.
     *
     * @param taskList list of tasks
     */
    public void save(TaskList taskList) {

        try {
            FileWriter filewriter = new FileWriter(dataFile.getPath());

            for (Task task : taskList.getList()) {
                filewriter.write(task.toMemoryString() + System.lineSeparator());
            }
            filewriter.close();
        } catch (IOException e) {
            System.out.println("There is some problem saving your duke.task(s) ☹");
        }
    }
}
