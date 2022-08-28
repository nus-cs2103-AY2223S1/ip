import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import duke.Command;
import duke.ToDoList;
import duke.Ui;
import duke.FileLoaderSaver;
import java.nio.file.Files;
import java.nio.file.Path;

public class CommandTest {

    @Test
    public void execute_createEventAndMarkDone_eventSaved(){
        try {
            String filePath = "/Users/shaune/Desktop/NUS/CS2103T/ip-1/src/test/actualOutput.txt";
            ToDoList toDoList = new ToDoList();
            Ui ui = new Ui();
            FileLoaderSaver storage = new FileLoaderSaver(filePath);

            String userInput = "event Justin Bieber Concert /at 2019-08-19";
            Command c = new Command(Command.CommandTypes.EVENT, userInput);
            c.execute(toDoList, ui, storage);

            userInput = "mark 1";
            c = new Command(Command.CommandTypes.MARK, userInput);
            c.execute(toDoList, ui, storage);

            String expectedOutput = "E # true # Justin Bieber Concert # 2019-08-19";
            List<String> taskList = storage.loadFile();
            String actualOutput = String.join(" ", taskList);

            Files.deleteIfExists(Path.of(filePath));
            assertEquals(expectedOutput, actualOutput);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}