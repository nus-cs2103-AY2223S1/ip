package pixel;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
//    public static void main(String[] args) {
//        // Application.launch(Pixel.class, "C:/!Education/CS2103/gitFolderOne/data/pixel.txt");
//
//        final Pixel pixelBot = new Pixel("C:/!Education/CS2103/gitFolderOne/data/pixel.txt");
//        pixelBot.run();
//
//        Platform.startup(() -> {
//            // create primary stage
//            Stage stage = new Stage();
//
//            pixelBot.start(stage);
//        });
//
//    }
}
