package Testing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import utilities.StringUtilities;

/**
 * Testcases that involve the StringUtilties class
 */
public class StringUtilityTests {

    /**
     * Checks if splitStringArray can split a generic string array by a character
     */
    @Test
    public void splitGenericStringArray() {
        String[] arr = {"a", "b", "c", "b", "d"};
        assertArrayEquals(
                new String[][] {{"a"}, {"c"}, {"d"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    /**
     * Checks if splitStringArray can split a generic string array by a character
     * when two delimiters are next to each other with nothing in between
     */
    @Test
    public void splitStringArrayWithRepeatedDelimiter() {
        String[] arr = {"a", "c", "b", "d", "b", "b", "e"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}, {"e"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    /**
     * Checks if splitStringArray can split a generic string array by a character
     * when the entire array ends with a delimiter
     */
    @Test
    public void splitStringArrayEndingWithDelimiter() {
        String[] arr = {"a", "c", "b", "d", "b", "b", "b", "b", "e", "b"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}, {"e"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    /**
     * Checks if splitStringArray can split a generic string array by a character
     * when the entire array ends with multiple delimiters
     */
    @Test
    public void splitStringArrayEndingWithRepeatedDelimiter() {
        String[] arr = {"a", "c", "b", "d", "b", "b", "b", "b"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    /**
     * Checks if splitStringArray can split a generic string array by a character
     * when it begins with a delimiter
     */
    @Test
    public void splitStringArrayStartingWithDelimiter() {
        String[] arr = {"b", "a", "c", "b", "d", "b", "b", "b", "b", "e", "b"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}, {"e"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    /**
     * Checks if splitStringArray can split a generic string array by a character
     * when it begins with multiple delimiters
     */
    @Test
    public void splitStringArrayStartingWithRepeatedDelimiter() {
        String[] arr = {"b", "b", "b", "a", "c", "b", "d", "b", "b", "b", "b", "e", "b"};
        assertArrayEquals(
                new String[][] {{"a", "c"}, {"d"}, {"e"}},
                StringUtilities.splitStringArray(arr, "b")
        );
    }

    /**
     * Checks if splitStringArray can split a generic string array by a
     * non-existent character as a delimiter
     */
    @Test
    public void splittingWithNonexistentDelimiter() {
        String[] arr = {"a", "c", "b", "d", "b", "b", "e"};
        assertArrayEquals(
                new String[][] {{"a", "c", "b", "d", "b", "b", "e"}},
                StringUtilities.splitStringArray(arr, "q")
        );
    }

    /**
     * Checks if splitStringArray can operate without failure on
     * an empty array
     */
    @Test
    public void splittingAnEmptyArray() {
        String[] arr = {};
        assertArrayEquals(
                new String[][] {},
                StringUtilities.splitStringArray(arr, "o")
        );
    }
}
