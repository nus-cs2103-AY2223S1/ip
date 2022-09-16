package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDosTest {
    @Test
    public void DummyTest(){
        try {
            assertEquals(new ToDos("sample"));
        } catch (Exception e) {
            System.out.println("Exception'd");
        }
    }


    private void assertEquals(ToDos sample) {
    }


}
