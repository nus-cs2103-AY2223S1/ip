package duke;

import java.util.Scanner;

public class Ui {
    private Parser parser;
    private Storage storage;
    public Ui(Storage s, TaskList tasks) {
        this.storage = s;
        this.parser = new Parser(s, tasks);
    }
    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Oi, What u want?");
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String next = in.nextLine();
            String[] command = next.split(" ", 2);
            if (parser.parse(command)) {
                return;
            }
        }
    }

    public void showLoadingError() {
        System.out.println("ERROR: No File Found... \nCreating New File");
    }

}
