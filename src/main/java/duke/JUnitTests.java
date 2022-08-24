package duke;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;


public class JUnitTests {
    @Test
    public void dummyTest(){
        assertEquals(2, 3);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}
