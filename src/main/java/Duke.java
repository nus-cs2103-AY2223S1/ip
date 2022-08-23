import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    public void run() {
        UI.getGREETING();
        UI.getLINE();
        Parser.parseInput();
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
