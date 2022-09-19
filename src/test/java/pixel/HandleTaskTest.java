package pixel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.junit.jupiter.api.Test;

import pixel.util.IncorrectFormatException;
import pixel.util.Parser;
import pixel.util.Storage;
import pixel.util.TaskList;

public class HandleTaskTest {

    private String filePath = "./data/pixel.txt";
    private Pixel pixelBot = new Pixel(filePath); // output file address
    private TaskList taskList = new TaskList(filePath);

    //@Test
    //public void testAddingTasks() throws FileNotFoundException {
    //String inputFileAddress = "C:/!Education/CS2103/gitFolderOne/data/testInput.txt";
    //File text = new File(inputFileAddress);
    //Scanner inputScanner = new Scanner(text);

    //Reading each line of the file using Scanner class
    //        int lineNumber = 1;
    //        while (inputScanner.hasNextLine()){
    //            String line = inputScanner.nextLine();
    //            System.out.println("line " + lineNumber + " :" + line);
    //            lineNumber++;
    //        }

    @Test
    public void testInvalidInput() {
        try {
            pixelBot.parserParse("dsfdsfdsfdsf");
        } catch (Exception exception) {
            //System.out.println(exception.toString());
            assertTrue(exception instanceof IncorrectFormatException);
            assertEquals(exception.toString(), "Input should be a task or a command!");
        }
    }

    @Test
    public void testInvalidDue() {
        try {
            taskList.handleNewTask("deadline /as jjjj", Parser.TaskType.DEADLINE);
        } catch (Exception exception) {
            // System.out.println(exception);
            assertTrue(exception instanceof IncorrectFormatException);
            assertEquals(exception.toString(),
                "pixel.util.IncorrectFormatException: Slash should be followed by \"by\" or \"at\"!");
        }
    }

    @Test
    public void testDeleteTask() {
        try {
            taskList.handleNewTask("deadline CS2103 assignment /by tomorrow", Parser.TaskType.DEADLINE);
            taskList.handleNewTask("todo meet Wayne for dinner /at 2022-26-08 1850", Parser.TaskType.DEADLINE);
            assertEquals(2, Storage.INPUT_TASKS.size());
            Storage.deleteEntry("delete 2", this.filePath);
            assertEquals(1, Storage.INPUT_TASKS.size());
        } catch (Exception exception) {
            // System.out.println(exception);
            assertTrue(exception instanceof IOException);
            // assertEquals("pixel.util.IncorrectFormatException: Slash should be followed by \"by\" or \"at\"!",
            //      exception.toString());
        }
    }

    @Test
    public void dateTimeFormatterWorking() {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)
        .withResolverStyle(ResolverStyle.SMART);
    DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);
    assertTrue(validator.isValid("2019-02-28"));
    assertFalse(validator.isValid("2019-02-30"));
}
