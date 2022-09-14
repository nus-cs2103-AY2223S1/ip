package duke.gui;

import java.nio.charset.Charset;

/**
 * Utility class for displaying emojis in JavaFX.
 */
public class EmojiUtil {
    /**
     * Fixes emoji encoding issues for JavaFX
     * @param input the input string
     * @return the string with correctly displayed emojis
     */
    public static String displayEmoji(String input) {
        return input.replace("☹", sweatEmoji()).replace("☺", smileEmoji());
    }

    /**
     * Returns sweat emoji in UTF-8 encoding.
     * @return String sweat emoji
     */
    public static String sweatEmoji() {
        byte[] emojiBytes = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x93};
        return new String(emojiBytes, Charset.forName("UTF-8"));
    }

    /**
     * Returns smile emoji in UTF-8 encoding.
     * @return String smile emoji
     */
    public static String smileEmoji() {
        byte[] emojiBytes = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x8A};
        return new String(emojiBytes, Charset.forName("UTF-8"));
    }
}
