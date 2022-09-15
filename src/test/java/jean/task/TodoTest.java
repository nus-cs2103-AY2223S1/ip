package jean.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_normalInput_success() {
        assertEquals("[T][ ] something", new Todo("something").toString());
    }

}
