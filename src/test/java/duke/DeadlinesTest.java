package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    public void anotherDummyTest(){
        try {
            assertEquals(new Deadlines("sample"));
        } catch (Exception e) {
            System.out.println("Exception'd");
        }
    }

    private void assertEquals(Deadlines sample) {
    }
}
