package duke;
public class Ui {
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void greeting() {
        String greet = "Hello! I'm Duke \n"
                + "What can I do for you? \n";

        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
    }

    public void farewell() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
