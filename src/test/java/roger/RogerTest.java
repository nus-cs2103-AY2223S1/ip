package roger;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import roger.storage.Storage;
import roger.ui.Parser;
import roger.ui.Response;


public class RogerTest {

    @Test
    public void testGetGreeting() {
        Roger roger = new Roger(new Parser(), new Storage(Paths.get("random path")));
        Response greetingResponse = roger.getGreeting();
        assertFalse(greetingResponse.isException());
        assertFalse(greetingResponse.isExit());
    }
}