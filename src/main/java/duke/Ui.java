package duke;

public class Ui {
    public void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you ?");
    }
    public void showLoadingError() {
        System.out.println("Could not load any existing task files");
        System.out.println("New task file will be created :) ");
    }

}
