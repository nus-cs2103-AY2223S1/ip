package anthea.note;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import anthea.Storage;

/**
 * Accesses a file for notes.
 */
public class NoteStorage {

    private static String noteStorageFileName = "./antheaNotes.txt";

    /**
     * Gets ArrayList of previously saved notes.
     *
     * @return ArrayList of notes.
     */
    public static ArrayList<Note> getNotes() {
        Storage storage = Storage.getFileState(noteStorageFileName);
        ArrayList<Note> notes = new ArrayList<>();
        for (String[] line : storage.getLines()) {
            assert line != null;
            notes.add(new Note(line[0], line[1]));
        }
        return notes;
    }

    /**
     * Saves a list of notes to the default file.
     *
     * @param notes List of notes.
     */
    public static void saveNotes(List<Note> notes) {
        List<String[]> lines = notes.stream().map(Note::getAsStringArray).collect(Collectors.toList());
        Storage storage = Storage.getFileState(noteStorageFileName);
        storage.saveLines((String[][]) lines.toArray(new String[][]{}));
    }
}
