package ado.storage;

/*import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;*/

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class StorageTest {


    @Test
    public void string_format_mediumDateFormat(){
        Storage storage = new Storage("data/tasks.txt");
        String output = storage.stringToMediumDateFormat("2022-07-08");
        assertEquals("8 Jul 2022", output);
    }

}
