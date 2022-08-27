package kirby.commands;
import kirby.TaskList;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static kirby.HandleTime.fromStringToDate;
import static kirby.HandleTime.isValidDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandleTimeTest {
    @Test
    public void negativeCheckDate() {
      String nonDate = ("2022-21-12");
      boolean output = isValidDate(nonDate);
      assertEquals(false, output);
    }

    @Test
    public void positiveCheckDate() {
        String date = ("1990-01-19");
        boolean output = isValidDate(date);
        assertEquals(true, output);
    }

    @Test
    public void fromStringToDateTest() {
        String date = ("1990-01-19");
        int[] output = fromStringToDate(date);
        int[] correctOutput = {19, 1, 1990};
        assertEquals(Arrays.toString(correctOutput), Arrays.toString(output));
    }
}
