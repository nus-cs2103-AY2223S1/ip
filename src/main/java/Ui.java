public class Ui {
    private final static String BREAK = "    ____________________________________________________________";

    public void showWelcome() {
        System.out.println(BREAK +
                "\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                BREAK +
                "\n");
    }

    public void farewell() {
        System.out.println(BREAK +
                "\n" +
                "     Goodbye! Have a nice Day!\n" +
                BREAK +
                "\n");
    }

    public void showLoadingError() {
        System.out.println(BREAK +
                "\n" +
                "     ☹ OOPS!!! I cannot load your file!" +
                "\n" +
                BREAK +
                "\n");
    }
}