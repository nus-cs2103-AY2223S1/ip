package utils;

public class IOUtils {

    static String line = "──────────────────────────────────────────";
    public static void printContentWithHR(String s) {
        System.out.println(IOUtils.line);
        System.out.println(s);
        System.out.println(IOUtils.line);
    }
}
