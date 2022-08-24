package duke.parser;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final static ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();
    private final static ByteArrayOutputStream errContent =
            new ByteArrayOutputStream();
    private final static PrintStream originalOut = System.out;
    private final static PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void resetStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void exit() throws DukeException {
        Parser p = new Parser(new Scanner(
                new ByteArrayInputStream("Bye".getBytes())));
        outContent.reset();
        p.handleInput();
        assertEquals("Bye. Hope to see you again soon!",
                outContent.toString().trim());
    }

    @Test
    void invalidCommand() {
        Parser p = new Parser(new Scanner(
                new ByteArrayInputStream("go to school".getBytes())));
        outContent.reset();
        DukeException e = assertThrows(DukeException.class,
                () -> p.handleInput());
        assertEquals("Invalid command", e.getMessage().trim());
    }

}