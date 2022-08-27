package utilities;

public class SerializedNamesFormatter {

    public static String createFileNameForUser(String userName) {
        return String.format("Duke_%s", userName);
    }

    public static String createTaskListName(String taskListName) {
        return String.format("TaskList_%s", taskListName);
    }
}
