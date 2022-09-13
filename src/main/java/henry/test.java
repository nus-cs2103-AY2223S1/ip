package henry;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

public class test {

    public static void main(String[] args) {
        String botname = "henry";
        String resourcesPath = getResourcesPath();
        Bot bot = new Bot(botname, resourcesPath);
        Chat chatsession = new Chat(bot);
        String request = "Hello. What is your name?";
        String response = chatsession.multisentenceRespond(request);
        System.out.println(response);
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        return path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
    }
}
