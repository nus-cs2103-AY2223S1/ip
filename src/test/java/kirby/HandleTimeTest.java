package kirby;

import static kirby.time.HandleTime.fromStringToDate;
import static kirby.time.HandleTime.isValidDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class HandleTimeTest {
    @Test
    public void isValidDate_false() {
        String nonDate = ("2022-21-12");
        boolean output = isValidDate(nonDate);
        assertEquals(false, output);
    }

    @Test
    public void isValidDate_true() {
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
