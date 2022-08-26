package duke.utils;

public class Ui {
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

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
