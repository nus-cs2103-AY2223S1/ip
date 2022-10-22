package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class StorageTest {

    @Test
    public void invalidArgs() {
        List<Task> dummyList  = new ArrayList<>();
        dummyList.add(new Deadlines("test"));
        String expectedMessage = "1.[D][✘] test(by: null)";
        String testingMessage = Storage.displayListOfMessages(dummyList);
        assertNotEquals(testingMessage,expectedMessage);
    }

}