package clevernotbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parseTextTest(){
        Parser testParser = new Parser();
        String txtTest1 = "todo read book";
        String txtTest2 = "deadline return book /by 02-12-2022 18:00";
        String txtTest3 = "mark 1";
        String txtTest4 = "event project meeting /at Aug 6th 2-4pm";
        String txtTest5 = "unmark 1";
        String txtTest6 = "list";
        String txtTest7 = "delete";
        String txtTest8 = "greet";
        String txtTest9 = "Random command 32123";

        Command testCommand1 = testParser.parseText(txtTest1);
        Command testCommand2 = testParser.parseText(txtTest2);
        Command testCommand3 = testParser.parseText(txtTest3);
        Command testCommand4 = testParser.parseText(txtTest4);
        Command testCommand5 = testParser.parseText(txtTest5);
        Command testCommand6 = testParser.parseText(txtTest6);
        Command testCommand7 = testParser.parseText(txtTest7);
        Command testCommand8 = testParser.parseText(txtTest8);
        Command testCommand9 = testParser.parseText(txtTest9);

        assertTrue(testCommand1 instanceof AddCommandToDo);
        assertTrue(testCommand2 instanceof AddCommandDeadLine);
        assertTrue(testCommand3 instanceof MarkCommand);
        assertTrue(testCommand4 instanceof AddCommandEvent);
        assertTrue(testCommand5 instanceof UnmarkCommand);
        assertTrue(testCommand6 instanceof ListCommand);
        assertTrue(testCommand7 instanceof DeleteCommand);
        assertTrue(testCommand8 instanceof GreetCommand);
        assertTrue(testCommand9 instanceof DefaultCommand);
    }
}
