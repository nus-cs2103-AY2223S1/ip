package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

class ParserTest {

    @Test
    void formatDate() {
        assertEquals(LocalDate.of(2022, 2, 2), Parser.formatDate("deadline return book/20220202"));
    }

    @Test
    void isTaskKeyword() {
        assertEquals(true, Parser.isTaskKeyword("todo"));
    }

    @Test
    void isKeyword() {
        assertEquals(false, Parser.isTaskKeyword("haha"));
    }
}