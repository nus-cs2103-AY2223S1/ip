package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionTest {

    @Test
    public void testException() throws IOException {

        Duke duke = new Duke();
        assertEquals("Sorry, the index could go wrong ...\n Please check again : ) ", duke.getResponse("delete 100"));

    }

}