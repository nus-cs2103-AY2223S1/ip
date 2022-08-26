package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParsedDataTest {
    @Test
    public void getSavedStringTest() {
        ParsedData tmp;
        tmp = new ParsedData("A", "b", "c");
        assertEquals("A <<<< b <<<< c", tmp.getSavedString());

        tmp = new ParsedData("Tx", "this is a deadline hw", "03-33-4222");
        assertEquals("Tx <<<< this is a deadline hw <<<< 03-33-4222", tmp.getSavedString());

        tmp = new ParsedData("Tx", "this is a deadline hw");
        assertEquals("Tx <<<< this is a deadline hw <<<< ", tmp.getSavedString());

        tmp = new ParsedData("Tx");
        assertEquals("Tx <<<<  <<<< ", tmp.getSavedString());
        tmp = new ParsedData();
        assertEquals(" <<<<  <<<< ", tmp.getSavedString());
    }
}
