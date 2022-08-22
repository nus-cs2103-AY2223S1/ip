package jean.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toString_normalInput_success(){
        assertEquals("[T][ ] something", new Todo("something").toString());
    }

}
