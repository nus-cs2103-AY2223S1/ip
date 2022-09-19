package task;

import java.util.ArrayList;

public class NotesList extends TaskList {
    protected ArrayList<Task> notesList;

    /**
     * Constructor for NotesList if there is a ArrayList.
     *
     * @param notes ArrayList of the tasks.
     */
    public NotesList(ArrayList<Task> notes) {
        this.notesList = notes;
    }

    /**
     * Constructor for NotesList if there is no ArrayList.
     */
    public NotesList() {
        this.notesList = new ArrayList<>(100);
    }

    /**
     * Returns the note at the index provided.
     *
     * @param index The index of the note to be returned.
     * @return Returns the note at the index provided.
     */
    public Task get(int index) {
        return this.notesList.get(index);
    }

    /**
     * Adds the note to the notesList.
     *
     * @param note Note to be added to the notesList.
     */
    public void add(Task note) {
        this.notesList.add(note);
    }

    /**
     * Prints out all the tasks in the taskList.
     */
    public void forEach() {
        notesList.forEach(n -> System.out.println((notesList.indexOf(n) + 1) + "."
                + n.toString()));
    }

    /**
     * Removes the note at the index provided.
     *
     * @param index The index of the note to be removed.
     */
    public void remove(int index) {
        notesList.remove(index);
    }

    public boolean isEmpty() {
        if (taskList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Changes the notesList to an ArrayList.
     *
     * @return An ArrayList containing all the notes in the
     * notesList.
     */
    public ArrayList<Task> toArray() {
        return this.notesList;
    }

    /**
     * Returns the size of the notesList.
     *
     * @return Returns the size of the notesList.
     */
    public int size() {
        return this.notesList.size();
    }
}
