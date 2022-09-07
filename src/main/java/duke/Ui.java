package duke;

public class Ui {
    private static final String BREAK_LINE = "    ____________________________________________________________";

    public void showWelcome() {
        System.out.println(BREAK_LINE +
                "\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                BREAK_LINE +
                "\n");
    }

    public void farewell() {
        System.out.println(BREAK_LINE +
                "\n" +
                "     Goodbye! Have a nice Day!\n" +
                BREAK_LINE +
                "\n");
    }

    public void showLoadingError() {
        System.out.println(BREAK_LINE +
                "\n" +
                "     ☹ OOPS!!! I cannot load your file!" +
                "\n" +
                BREAK_LINE +
                "\n");
    }
}