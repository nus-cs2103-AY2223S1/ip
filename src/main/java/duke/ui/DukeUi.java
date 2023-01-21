package duke.ui;

/**
 * Encapsulates a DukeUi
 */
public class DukeUi {
    private static String response;
    /**
     * Prints customised Duke message
     */
    public static void dukePrint(String str) {
        System.out.println("===========================================\n");
        System.out.println(str);
        System.out.println("===========================================\n");
        response = str;
    }

    public static String getResponse() {
        return response;
    }
}
