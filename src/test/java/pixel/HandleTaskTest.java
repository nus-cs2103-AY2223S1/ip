package pixel;

import org.junit.jupiter.api.Test;
import pixel.util.IncorrectFormatException;
import pixel.util.Storage;
import pixel.util.TaskList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandleTaskTest {

    String filePath = "C:/!Education/CS2103/gitFolderOne/data/pixel.txt";
    Pixel pixelBot = new Pixel(filePath); // output file address
    TaskList taskList = new TaskList(filePath);

//    @Test
//    public void testAddingTasks() throws FileNotFoundException {
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
            pixelBot.parser.parse("dsfdsfdsfdsf");
        } catch (Exception exception) {
            //System.out.println(exception.toString());
            assertTrue(exception instanceof IncorrectFormatException);
            assertEquals(exception.toString(), "Input should be a task or a command!");
        }
    }

    @Test
    public void testInvalidDue() {
        try {
            taskList.handleNewTask("deadline /as jjjj", "D");
        } catch (Exception exception) {
            // System.out.println(exception);
            assertTrue(exception instanceof IncorrectFormatException);
            assertEquals(exception.toString(), "pixel.util.IncorrectFormatException: Slash should be followed by \"by\" or \"at\"!");
        }
    }

    @Test
    public void testDeleteTask() {
        try {
            taskList.handleNewTask("deadline CS2103 assignment /by tomorrow", "D");
            taskList.handleNewTask("todo meet Wayne for dinner /at 2022-26-08 1850", "D");
            assertEquals(2, Storage.INPUT_TASKS.size());
            Storage.deleteEntry("delete 2", this.filePath);
            assertEquals(1, Storage.INPUT_TASKS.size());
        } catch (Exception exception) {
            // System.out.println(exception);
            assertTrue(exception instanceof IOException);
            // assertEquals("pixel.util.IncorrectFormatException: Slash should be followed by \"by\" or \"at\"!", exception.toString());
        }
    }

    // Unit tests
/*
DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US)
    .withResolverStyle(ResolverStyle.STRICT);
DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

assertTrue(validator.isValid("2019-02-28"));
assertFalse(validator.isValid("2019-02-30"));
    */
}