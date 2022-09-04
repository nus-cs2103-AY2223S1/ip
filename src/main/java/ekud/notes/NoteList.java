package ekud.notes;

import ekud.exception.EkudException;

import java.util.List;

/**
 * Class to store list of notes.
 */
public class NoteList {
    private final List<Note> noteList;

    /**
     * Constructor that instantiates an instance of noteList.
     *
     * @param noteList List of notes.
     */
    public NoteList(List<Note> noteList) { this.noteList = noteList; }

    /**
     * Gets the note list.
     *
     * @return List of notes.
     */
    public List<Note> getNoteList() { return this.noteList; }

    /**
     * Deletes a note.
     *
     * @param message Command in the form of deletenote [index].
     * @return Message to be printed to the user.
     * @throws EkudException Error message.
     */
    public String deleteNote(String message) throws EkudException {
        if (!message.matches("deletenote \\d+$")) {
            throw new EkudException("Invalid syntax. Use deletenote <index>");
        }
        int idx = Integer.parseInt(message.substring("deletenote ".length()));
        if (idx > this.noteList.size() || idx < 1) {
            throw new EkudException("Invalid index. Type 'listnote' to see available notes and their indexes.");
        }
        Note note = this.noteList.remove(idx - 1);
        return (String.format("Noted. I've removed this note:\n%s\nNow you have %d notes in the list.",
                note.toString(),
                noteList.size()));
    }

    /**
     * Adds a note.
     *
     * @param description Description of note.
     * @return Message to be printed to the user.
     * @throws EkudException Error message.
     */
    public String addNote(String description) throws EkudException {
        if (this.noteList.size() == 100) {
            throw new EkudException("Sorry, you've reached the limit of 100 notes.");
        }
        Note note = new Note(description);
        this.noteList.add(note);
        return String.format("Got it. I've added this note: %s", note.toString());
    }

    /**
     * Gets string representation of all notes.
     *
     * @return String representation of all notes.
     */
    public String printNotes() {
        StringBuilder builder = new StringBuilder("Here are the notes in your list:\n");
        for (int i = 0; i < noteList.size(); i++) {
            builder.append(String.format("%d.%s\n", i + 1, noteList.get(i).toString()));
        }
        return builder.toString();
    }
}
