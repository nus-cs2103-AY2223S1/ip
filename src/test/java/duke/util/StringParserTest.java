package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringParserTest {
    @Test
    public void addIndentTest1() {
        String test = "a b c";
        assertEquals(StringParser.addIndent(test), "    a b c");
    }

    @Test
    public void addIndentTest2() {
        String test = "";
        assertEquals(StringParser.addIndent(test), "    ");
    }

    @Test
    public void addIndentTest3() {
        String test = "a \nb\n c";
        assertEquals(StringParser.addIndent(test), "    a \n    b\n     c");
    }

    @Test
    public void addWrapperTest() {
        String test = "123\nasdf   \n asdf";
        assertEquals(StringParser.addWrapper(test),
                "   ______________________________\n123\nasdf   \n asdf\n   ______________________________\n");
    }

    @Test
    public void isAlphanumericVariableNameTest1() {
        assertEquals(false, StringParser.isNotAlphanumericVariableName("txt"));
    }

    @Test
    public void isAlphanumericVariableNameTest2() {
        assertEquals(false, StringParser.isNotAlphanumericVariableName("t3x12t3"));
    }

    @Test
    public void isAlphanumericVariableNameTest3() {
        assertEquals(true, StringParser.isNotAlphanumericVariableName("tx t"));
    }

    @Test
    public void isAlphanumericVariableNameTest4() {
        assertEquals(false, StringParser.isNotAlphanumericVariableName("t131xt"));
    }

    @Test
    public void isAlphanumericVariableNameTest5() {
        assertEquals(true, StringParser.isNotAlphanumericVariableName("1txt"));

    }

    @Test
    public void isAlphanumericVariableNameTest6() {
        assertEquals(false, StringParser.isNotAlphanumericVariableName("txt12  "));
    }

    @Test
    public void isAlphanumericVariableNameTest7() {
        assertEquals(true, StringParser.isNotAlphanumericVariableName("t_xt"));
    }
}
