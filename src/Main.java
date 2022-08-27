import java.io.File;

import duke.Duke;

public class Main {
    public static void main(String[] args) {
        String path = "data/duke.txt";
        new File("data").mkdir();
        Duke duke = new Duke(path);
        duke.runDuke();
    }
}
