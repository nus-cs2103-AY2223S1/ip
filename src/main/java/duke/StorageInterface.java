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
     * @return List of tasks stored in file.
     * @throws DukeException When error reading storage file.
     */
    List<Task> readFile() throws DukeException;

    /**
     * Saves task in storage file.
     *
     * @param task Task to save.
     * @throws DukeException When error updating storage file.
     */
    void save(Task task) throws DukeException;

    /**
     * Updates line in storage file.
     *
     * @param lineIndex Index to update (0-based indexing).
     * @throws DukeException When error deleting storage file.
     */
    void deleteLine(int lineIndex) throws DukeException;

    /**
     * Updates line in storage file.
     *
     * @param lineIndex Index to update (0-based indexing).
     * @param updatedLine New line to update existing line in file.
     * @throws DukeException When error updating storage file.
     */
    void updateLine(int lineIndex, String updatedLine) throws DukeException;
}
