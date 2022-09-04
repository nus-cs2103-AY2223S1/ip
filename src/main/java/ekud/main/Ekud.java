package ekud.main;

import ekud.exception.EkudException;
import ekud.notes.Note;
import ekud.notes.NoteList;
import ekud.task.TaskList;
import ekud.util.ParseResult;
import ekud.util.Parser;
import ekud.util.Storage;
import ekud.util.Ui;

import java.util.ArrayList;

public class Ekud {

    private final Storage storage;
    private final TaskList taskList;
    private final NoteList noteList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor that instantiates new Ekud instance.
     */
    public Ekud() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser();
        TaskList taskList = null;
        try {
            taskList = new TaskList(this.storage.getTasksFromFile());
        } catch (EkudException exception) {
            ui.showErrorMessage(exception);
            taskList = new TaskList();
        }
        this.taskList = taskList;
        NoteList noteList = null;
        try {
            noteList = new NoteList(this.storage.getNotesFromFile());
        } catch (EkudException exception) {
            ui.showErrorMessage(exception);
            noteList = new NoteList(new ArrayList<Note>());
        }
        this.noteList = noteList;
    }

    /**
     * Gets response from input.
     *
     * @param input The input string.
     * @return The response to be shown.
     */
    public String getResponse(String input) {
        try {
            ParseResult result = this.parser.parseCommand(input, this.taskList, this.noteList);
            if (result.terminate) {
                return "Bye!";
            }
            if (result.saveStorage) {
                storage.writeTasksToFile(this.taskList.getTaskList());
                storage.writeNotesToFile(this.noteList.getNoteList());
            }
            return result.message;
        } catch (EkudException exception) {
            return exception.getMessage();
        }
    }

}
