package duke.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

enum LaunchFlagEnum {
    // @formatter:off
    NO_GUI("Launches Duke with a GUI", "", "--no-gui", "-ng"),
    SAVE_FILE("Specifies a custom save path", "<path>", "--use-save", "-s"),
    HELP("Displays this message", "", "--help", "-h"),
    GUI("Lauches Duke without a GUI", "", "--gui", "-g"),
    NULL_FLAG("", "");
    //@formatter:on

    private static final String HELP_HEADER = "Duke\n\nUsage: duke.jar [options] [paths...]\n\nOptions\n";

    private final Set<String> aliases;
    private final String paramSettings;
    private final String description;

    LaunchFlagEnum(String description, String paramSettings, String... launchFlags) {
        aliases = new HashSet<String>(launchFlags.length);
        this.description = description;
        this.paramSettings = paramSettings;
        for (String flag : launchFlags) {
            aliases.add(flag);
        }
    }

    static LaunchFlagEnum getFlag(String query) {
        for (LaunchFlagEnum flagEnum : LaunchFlagEnum.values()) {
            if (flagEnum.aliases.contains(query)) {
                return flagEnum;
            }
        }

        return NULL_FLAG;
    }

    static String getHelp() {
        int maxLength = 0;
        Map<String, String> mapper = new HashMap<>();
        for (LaunchFlagEnum flagEnum : LaunchFlagEnum.values()) {
            if (flagEnum == NULL_FLAG) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            flagEnum.aliases.forEach(x -> {
                sb.append(x);
                sb.append(" ");
            });
            sb.append(flagEnum.paramSettings);
            maxLength = Math.max(maxLength, sb.length());
            mapper.put(sb.toString(), flagEnum.description);
        }
        maxLength += 4;

        StringBuilder helpMsg = new StringBuilder(HELP_HEADER);
        for (var kv : mapper.entrySet()) {
            helpMsg.append(String.format("%-" + maxLength + "s%s%n", kv.getKey(), kv.getValue()));
        }
        return helpMsg.toString();
    }
}
