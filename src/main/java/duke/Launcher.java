package duke;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        if (args.length >= 1 && args[0].equals("cli")) {
            Duke.main(args);
        } else {
            Application.launch(Main.class, args);
        }
    }
}
