import Duke.Duke;
import javafx.application.Application;

/**
 * A Launcher class to workaround classpath issues.
 */
public class Launcher {

    public static void main(String[] args) {
        System.out.println("Hello!");
        Application.launch(Main.class, args);
    }

}
