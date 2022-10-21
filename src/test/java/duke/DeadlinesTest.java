package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DeadlinesTest {

    @Test
    public void assertEquals(){
        try {
            assertEquals(new Deadlines("sample"));
        } catch (Exception e) {
            System.out.println("Exception'd");
        }
    }

    private void assertEquals(Deadlines sample) {
    }
}
