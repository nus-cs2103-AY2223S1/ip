package Main;

public class LunaException extends Exception {
    private static String sep = "\n✧  ✡︎✮ ✰ ✦ ✨️ ❍  ✫   ✣❈ ✶  ✧︎ ✱✬ ✨   ❇︎ ✫❍   ❈ ✶  ❍✶  ✯❃  ✨\n";

    public LunaException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return sep + "\n" + getMessage() + "\n" + sep;
    }
}