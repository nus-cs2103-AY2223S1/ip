package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private final Parser p = new Parser();
    private final TaskList t = new TaskList();

    @Test
    public void dummyTest(){
        try {
            assertEquals(Duke.Command.TODO, p.parse("todo abc").x);
        } catch (Exception e) {
            System.out.println("Exception'd");
        }
    }

    @Test
    public void anotherDummyTest(){
        try {
            assertEquals(new Todo("sample", false).getName(), t.addTodo("sample").getName());
        } catch (Exception e) {
            System.out.println("Exception'd");
        }
    }
}
