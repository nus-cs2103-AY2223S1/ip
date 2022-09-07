package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeInvalidSaveDataException;
import duke.note.Note;

public class NoteTest {
    @Test
    public void testCreateNote() {
        Note note = new Note("CS2103T is fun");
        assertEquals(note.toString(), "CS2103T is fun");
    }

    @Test
    public void testLoadNote() {
        Note note = Note.fromSaveString("N,\"CS2103T is fun\"");
        assertEquals(note.toString(), "CS2103T is fun");
    }

    @Test
    public void testLoadInvalidNote1() {
        assertThrows(DukeInvalidSaveDataException.class, () -> Note.fromSaveString(""));
    }
}
