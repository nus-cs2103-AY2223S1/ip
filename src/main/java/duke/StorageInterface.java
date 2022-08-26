package duke;

import java.util.List;

import duke.task.Task;

/**
 * Interface for storage functions.
 */
public interface StorageInterface {

    /**
     * Read storage file and return data in file as a list of Tasks.
     *
     * @return list of tasks stored in file.
     * @throws DukeException when error reading storage file.
     */
    public List<Task> readFile() throws DukeException;

    /**
     * Saves task in storage file.
     *
     * @param task task to save.
     * @throws DukeException when error updating storage file.
     */
    public void save(Task task) throws DukeException;

    /**
     * Updates line in storage file.
     *
     * @param lineIndex index to update (0-based indexing).
     * @throws DukeException when error deleting storage file.
     */
    public void deleteLine(int lineIndex) throws DukeException;

    /**
     * Updates line in storage file.
     *
     * @param lineIndex index to update (0-based indexing).
     * @param updatedLine new line to update existing line in file.
     * @throws DukeException when error updating storage file.
     */
    public void updateLine(int lineIndex, String updatedLine) throws DukeException;
}
