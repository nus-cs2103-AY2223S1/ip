package duke.storage;

import java.io.FileWriter;
import java.io.IOException;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.MainWindow;

/**
 * Assists with the saving of a `TaskList` to a txt format.
 * Note that this class is package-private.
 *
 * @author Kang Wei
 */
class SaveFile {

    /**
     * Writes a `TaskList` to a txt file.
     *
     * @param tasks The `TaskList` to write.
     * @param filePath The location of the file to write to.
     * @throws DukeExceception Throws a DukeException if there is an IOException
     *                         during the filewriting process.
     */
    public static void save(TaskList tasks, String filePath) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                writer.write(task.toString());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Honey! There was a problem with saving your list of "
                    + "tasks to " + filePath + " ! :(");
        }
    }
}
