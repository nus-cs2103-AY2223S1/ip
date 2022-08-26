package duke.utils;

public class DukeUtils {
    private static void makeLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2015");
        }
        System.out.println();
    }

    public static void wrapWithLines(String message) {
        makeLine();
        System.out.println(message);
        makeLine();
    }
}
