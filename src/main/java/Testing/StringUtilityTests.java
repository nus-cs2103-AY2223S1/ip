package Testing;

import Utilities.StringUtilities;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilityTests {

    @Test
    public void splitGenericStringArray() {
        String[] arr = {"a", "b", "c", "b", "d"};
        assertArrayEquals(
                new String[][] {{"a"}, {"c"}, {"d"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    @Test
    public void splitStringArrayWithRepeatedDelimiter() {
        String[] arr = {"a", "c", "b", "d", "b", "b", "e"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}, {"e"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    @Test
    public void splitStringArrayEndingWithDelimiter() {
        String[] arr = {"a", "c", "b", "d", "b", "b", "b", "b", "e", "b"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}, {"e"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    @Test
    public void splitStringArrayEndingWithRepeatedDelimiter() {
        String[] arr = {"a", "c", "b", "d", "b", "b", "b", "b"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    @Test
    public void splitStringArrayStartingWithDelimiter() {
        String[] arr = {"b", "a", "c", "b", "d", "b", "b", "b", "b", "e", "b"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}, {"e"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    @Test
    public void splitStringArrayStartingWithRepeatedDelimiter() {
        String[] arr = {"b", "b", "b", "a", "c", "b", "d", "b", "b", "b", "b", "e", "b"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}, {"e"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    @Test
    public void splittingWithNonexistentDelimiter() {
        String[] arr = {"a", "c", "b", "d", "b", "b", "e"};
        assertArrayEquals(
                new String[][] {{"a", "c", "b", "d", "b", "b", "e"}},
                StringUtilities.splitStringArray(arr, "q")
        );
    }

    @Test
    public void splittingAnEmptyArray() {
        String[] arr = {};
        assertArrayEquals(
                new String[][] {},
                StringUtilities.splitStringArray(arr, "o")
        );
    }
}
