package duke.components;

import java.nio.charset.StandardCharsets;

/**
 * Emoji list.
 */
public class Emoji {
    public static final String SNOW_FLAKE = new String(
            new byte[]{(byte) 0xE2, (byte) 0x9D, (byte) 0x84},
            StandardCharsets.UTF_8
    );
    public static final String CHERRY_BLOSSOM = new String(
            new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x8C, (byte) 0xB8},
            StandardCharsets.UTF_8
    );
    public static final String CONFETTI_BALL = new String(
            new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x8E, (byte) 0x8A},
            StandardCharsets.UTF_8
    );
    public static final String VICTORY_HAND = new String(
            new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x8C},
            StandardCharsets.UTF_8
    );
    public static final String CHECK_MARK = new String(
            new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x85},
            StandardCharsets.UTF_8
    );
}
