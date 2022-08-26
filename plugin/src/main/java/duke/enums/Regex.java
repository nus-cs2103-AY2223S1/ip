package duke.enums;

public enum Regex {
    INPUT_REGEX("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: ([^/]*))?$"),
    LOADING_REGEX_WITH_DEADLINE(
            "^\\[(.)\\]\\[(.)\\]([a-zA-Z].+)?(?: ([^<]*))?(\\<.(.+)\\:([0-9]+)\\s(.+)\\s(.+)\\s(.+)\\s(.+)\\s\\>)?$");

    private String regex;

    Regex(String reg) {
        regex = reg;
    }

    @Override
    public String toString() {
        return regex;
    }
}
