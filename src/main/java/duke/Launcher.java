package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;

public class Launcher {
    private static final String DEFAULT_APPLICATION_NAME = "Duke";
    private static final Path DEFAULT_CACHE_PATH = Paths.get(".duke.cache");

    public static void main(String[] args) {
        Application.launch(Main.class, DEFAULT_APPLICATION_NAME, DEFAULT_CACHE_PATH.toString());
    }
}
