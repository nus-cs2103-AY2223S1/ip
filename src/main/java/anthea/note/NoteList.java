package anthea.note;

import java.util.ArrayList;
import java.util.List;

import anthea.ChatbotResponse;
import anthea.Pair;
import anthea.exception.ChatbotException;

/**
 * Holds the list of tasks.
 */
public class NoteList {
    /** List of tasks to remember */
    private static ArrayList<Note> noteList = new ArrayList<>();

    /**
     * Initializes the task list.
     */
    public static void initializeNoteList() {
        noteList = NoteStorage.getNotes();
    }

    /**
     * Finalizes the note list.
     */
    public static void finalizeNoteList() {
        NoteStorage.saveNotes(noteList);
    }

    /**
     * Gets note from index as string.
     *
     * @param index Index as a string.
     * @return Note if successful.
     * @throws ChatbotException if not successful.
     */
    public static Note getNote(String index) throws ChatbotException {
        try {
            int idx = Integer.parseInt(index);
            return noteList.get(idx - 1);
        } catch (NumberFormatException ex) {
            throw new ChatbotException(new ChatbotResponse(
                    "Sorry, I didn't understand " + index + ", please give me a number."));
        } catch (IndexOutOfBoundsException ex) {
            throw new ChatbotException(new ChatbotResponse(
                    "Sorry, the number " + index + ", wasn't in the range."));
        }
    }

    /**
     * Gets notes that match the search term.
     *
     * @param query Search term.
     * @return List of indices and notes.
     */
    public static ArrayList<Pair<Integer, Note>> filterNotes(String query) {
        ArrayList<Pair<Integer, Note>> result = new ArrayList<>();
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if (note.isMatchingQuery(query)) {
                result.add(new Pair<>(i, note));
            }
        }
        return result;
    }

    /**
     * Gets the task list for other classes to work on.
     *
     * @return The task list.
     */
    public static List<Note> getNoteList() {
        return noteList;
    }
}
