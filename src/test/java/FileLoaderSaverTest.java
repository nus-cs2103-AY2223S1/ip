import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.FileLoaderSaver;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileLoaderSaverTest {
    private String filePath = "/Users/shaune/Desktop/NUS/CS2103T/ip-1/src/test/testData.txt";
    private String createFilePath = "/Users/shaune/Desktop/NUS/CS2103T/ip-1/src/test/actualOutput.txt";

    @Test
    public void loadFile_properlyStoredTwoTasks_tasksExtractedProperly(){
        try{
            FileLoaderSaver fls = new FileLoaderSaver(filePath);
            List<String> taskList = fls.loadFile();
            String actualOutput = String.join(" ", taskList);
            String expectedOutput = "T # false # hello E # false # hello concert # 2019-10-08";
            assertEquals(expectedOutput, actualOutput);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void writeToFile_writeTwoTasks_tasksStoredProperly()
    {
        try{
            FileLoaderSaver fls = new FileLoaderSaver(createFilePath);
            String input = "T # false # hello\nE # false # hello concert # 2019-10-08";
            fls.writeToFile(input);

            List<String> actualOutputList = Files.readAllLines(Path.of(filePath));
            String expectedOutput = "T # false # hello E # false # hello concert # 2019-10-08";
            Files.deleteIfExists(Path.of(createFilePath));
            assertEquals(expectedOutput, String.join(" ", actualOutputList));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}