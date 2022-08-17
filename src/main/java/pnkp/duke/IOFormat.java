package pnkp.duke;

public class IOFormat {
    public static void say(String text) {
        String[] lines = new String[1];
        lines[0] = text;
        say(lines);
    }

    public static void say(String[] lines) {
        for (int i=0; i < lines.length; i++) {
            if (i==0) System.out.println("\uD83D\uDCAC " + lines[i]);
            else      System.out.println("   " + lines[i]);
        }
        System.out.println("─────");
    }
}
