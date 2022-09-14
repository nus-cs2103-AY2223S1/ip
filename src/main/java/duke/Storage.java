package duke;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exception.DukeException;
import duke.exception.DukeInvalidSaveDataException;
import duke.note.Note;
import duke.note.NoteList;

/**
 * Handles saving and loading of list objects to and from the hard disk.
 */
public class Storage {
    private boolean isFirstLoad;
    private String tasksFilePath;
    private String notesFilePath;

    /**
     * Create a new Storage object to handle saving and loading of list objects.
     *
     * @param tasksFilePath path to the save file for tasks
     * @param notesFilePath path to the save file for notes
     */
    public Storage(String tasksFilePath, String notesFilePath) {
        isFirstLoad = false;
        this.tasksFilePath = tasksFilePath;
        this.notesFilePath = notesFilePath;
    }

    /**
     * Loads tasks and notes from the filepaths.
     *
     * @param tasks The TaskList to load the tasks into.
     * @param notes The NoteList to load the notes into.
     * @throws DukeInvalidSaveDataException if there is an error reading the file
     */
    public boolean load(TaskList tasks, NoteList notes) throws DukeInvalidSaveDataException {
        Path tasksSaveLocation = Paths.get(tasksFilePath);
        try {
            Files.lines(tasksSaveLocation).forEach((taskString) -> {
                String type = taskString.split(",")[0];
                switch (type) {
                case "T":
                    tasks.add(Task.fromSaveString(taskString));
                    break;
                case "E":
                    tasks.add(Event.fromSaveString(taskString));
                    break;
                case "D":
                    tasks.add(Deadline.fromSaveString(taskString));
                    break;
                default:
                    throw new DukeInvalidSaveDataException();
                }
            });
        } catch (IOException ignored) {
            // Save file does not exist, don't try to continue loading.
            isFirstLoad = true;
        }

        Path notesSaveLocation = Paths.get(notesFilePath);
        try {
            Files.lines(notesSaveLocation).forEach((notesString) -> {
                String type = notesString.split(",")[0];
                switch (type) {
                case "N":
                    notes.add(Note.fromSaveString(notesString));
                    break;
                default:
                    throw new DukeInvalidSaveDataException();
                }
            });
        } catch (IOException ignored) {
            // Save file does not exist, don't try to continue loading.
            isFirstLoad = true;
        }

        return isFirstLoad;
    }

    /**
     * Saves the tasks and notes to their respective files.
     *
     * @param tasks the list of tasks to save
     * @param notes the list of notes to save
     * @throws DukeException if there is an error writing to the file
     */
    public void save(TaskList tasks, NoteList notes) throws DukeException {
        try {
            Files.createDirectories(Paths.get(tasksFilePath).getParent());
            Path tasksSaveLocation = Paths.get(tasksFilePath);
            Files.createDirectories(Paths.get(notesFilePath).getParent());
            Path notesSaveLocation = Paths.get(notesFilePath);
            try {
                Files.createFile(tasksSaveLocation);
            } catch (FileAlreadyExistsException ignored) {
                // Tasks save file already exists, overwrite it in the next step.
            }
            try {
                Files.createFile(notesSaveLocation);
            } catch (FileAlreadyExistsException ignored) {
                // Notes save file already exists, overwrite it in the next step.
            }
            Files.write(tasksSaveLocation, tasks.toSaveData().getBytes());
            Files.write(notesSaveLocation, notes.toSaveData().getBytes());
        } catch (IOException e) {
            throw new DukeException("IOException: " + e.toString());
        }
    }
}
