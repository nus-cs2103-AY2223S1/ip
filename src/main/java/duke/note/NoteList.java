package duke.note;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;

/**
 * Represents a list of notes.
 */
public class NoteList extends ArrayList<Note> {
    public NoteList() {
        super();
    }

    public NoteList(ArrayList<Note> notes) {
        super(notes);
    }

    public String toSaveData() {
        return this.stream().map(Note::toSaveData).collect(joining("\n"));
    }

    /**
     * Finds all notes in the list that contain the given string.
     * @param toFind
     * @return
     */
    public ArrayList<Note> find(String toFind) {
        return this.stream().filter(note -> note.descriptionContains(toFind))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
