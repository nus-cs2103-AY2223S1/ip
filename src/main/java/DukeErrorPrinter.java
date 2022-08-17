public class DukeErrorPrinter extends DukePrinter {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void print(String error) {
        System.out.print(DukeErrorPrinter.ANSI_RED);
        DukePrinter.print(error);
        System.out.print(DukeErrorPrinter.ANSI_RESET);
    }
}
