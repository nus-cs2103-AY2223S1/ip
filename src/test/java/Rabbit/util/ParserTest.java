package Rabbit.util;

import Rabbit.Rabbit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import Rabbit.RabbitException.RabbitException;
import java.time.LocalDateTime;
public class ParserTest {

    @Test
    public void parseTime_string_correctLocalDateTime() {
        try {
            Parser parser = new Parser();
            String input = "2012-12-12-1200";
            LocalDateTime time = parser.parseTime(input);
            assertEquals(time, LocalDateTime.of(2012, 12, 12, 12, 00));
        } catch (RabbitException e) {
            System.out.println("Test failed due to exception.");
            fail();
        }

    }
}
