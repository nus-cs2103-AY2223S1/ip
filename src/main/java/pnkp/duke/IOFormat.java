package pnkp.duke;

import java.util.List;

public class IOFormat {
    public static void say(String text) {
        say(List.of(text));
    }

    public static void say(List<String> lines) {
        for (int i=0; i < lines.size(); i++) {
            if (i==0) System.out.println("\uD83D\uDCAC " + lines.get(i));
            else      System.out.println("   " + lines.get(i));
        }
        System.out.println("─────");
    }
}
