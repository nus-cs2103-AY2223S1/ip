package duke;

import javafx.application.Application;

public class Launcher {
    private static final String DEFAULT_APPLICATION_NAME = "Duke";
    private static final String DEFAULT_CACHE_PATH = "./.duke.cache";

    public static void main(String[] args) {
        Application.launch(Main.class, DEFAULT_APPLICATION_NAME, DEFAULT_CACHE_PATH);
    }
}
