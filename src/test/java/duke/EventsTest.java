package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    public void anotherDummyTest(){
        try {
            assertEquals(new Events("sample"));
        } catch (Exception e) {
            System.out.println("Exception'd");
        }
    }

    private void assertEquals(Events sample) {
    }
}
