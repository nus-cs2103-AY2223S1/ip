package brain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrainTest {
    @Test
    public void brainConstructor_newBrainHasNoTask_success() {
        assertEquals(0, new Brain().size());
    }
}