import duke.Duke;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String path = "data/duke.txt";
        new File("data").mkdir();
        Duke duke = new Duke(path);
        duke.runDuke();
    }
}
