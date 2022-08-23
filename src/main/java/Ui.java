public class Ui {
    private static final String separator = "===================================================";
    public Ui() {}

    public void print(String msg) {
        System.out.println(msg);
        System.out.println(separator);
    }

    public void printWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(separator);
    }
}
