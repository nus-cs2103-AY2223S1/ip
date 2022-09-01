package poolsheen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void load_noFile_exceptionThrown() {
        try {
            Storage s = new Storage("src/main/java/poolsheen/SAVE.TXT");
            s.load();
        } catch (Exception e) {
            assertEquals("The Poolsheen program has encountered an error.", e.getMessage());
        }
    }
}
