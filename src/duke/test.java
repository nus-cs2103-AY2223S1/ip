package duke;

import java.nio.file.Files;
import java.nio.file.Path;

public class test {
    public static void main(String[] args) {

        String currentDir = System.getProperty("user.dir");
        Path path = java.nio.file.Paths.get(currentDir, "docsd");

        System.out.println(path);
        System.out.println(Files.exists(path));
    }
}
