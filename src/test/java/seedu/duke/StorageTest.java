package seedu.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void getLocalDateTest() {
        assertEquals(LocalDate.parse("2022-10-02"), Storage.getLocalDate("2-10-2022"));
        assertEquals(LocalDate.parse("2022-10-12"), Storage.getLocalDate("12-10-2022"));
    }

}
