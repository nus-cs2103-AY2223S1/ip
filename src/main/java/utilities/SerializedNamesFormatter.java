package utilities;

/**
 * SerializedNamesFormatter formats a string name for serialisable objects
 */
public class SerializedNamesFormatter {

    public static String createFileNameForUser(String userName) {
        return String.format("Duke_%s", userName);
    }
}
