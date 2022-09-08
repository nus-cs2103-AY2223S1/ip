package arc;

import java.util.ArrayList;

/**
 * Encapsulates a list of Notes
 */
public class NoteList {

    private ArrayList<Note> notes;

    /**
     * Constructor for NoteList
     * @param tasks An ArrayList of Notes
     */
    public NoteList(ArrayList<Note> notes) {
        this.notes = notes;
    }

    /**
     * Returns the number of notes currently in NoteList
     * @return The number of notes currently in NoteList
     */
    public int numNotes() {
        return this.notes.size();
    }

    /**
     * Gets the note at the given index
     * @param index The index of the note to retrieve
     * @return The note at the given index
     */
    public Note getNote(int index) {
        return this.notes.get(index);
    }

    /**
     * Prints out all Notes
     * @return List of Notes
     */
    public String listNotes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Listing your notes...\n");

        if (numNotes() == 0) {
            sb.append("\tYou have no current notes :-(");
        }

        for (int i = 0; i < this.numNotes(); i++) {
            sb.append(String.format("\t* %s\n", this.getNote(i)));
        }

        return sb.toString();
    }

    /**
     * Adds a new aRC.Note to arraylist
     * @param newNote The new aRC.Note to be added
     * @return An output message
     */
    public String addNote(Note newNote) {
        this.notes.add(newNote);
        return String.format(
                "Got it. I've added this note:\n\t%s\nNow you have %d note%s in the list.",
                newNote, this.numNotes(), this.numNotes() == 1 ? "" : "s");
    }

    /**
     * Deletes a aRC.Note from arraylist
     * @param index The index of the aRC.Note to be deleted
     * @return An output message
     */
    public String deleteNote(int index) {
        Note deletedNote = this.notes.remove(index);
        return String.format(
                "I've removed this note:\n\t%s\nNow you have %d note%s in the list.",
                deletedNote, this.numNotes(), this.numNotes() == 1 ? "" : "s");
    }
}
