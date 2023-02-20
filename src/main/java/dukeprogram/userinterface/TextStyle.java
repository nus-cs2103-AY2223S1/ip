package dukeprogram.userinterface;

/**
 * TextStyles dictate how the text should be rendered. In each managing class,
 * the text styles are mapped to a css style class
 */
public enum TextStyle {
    Regular("regular"),
    RegularLarge("regular-large"),
    Header("header"),
    Tag("tag"),
    Warning("warning"),
    SmallerTag("smaller-tag");

    public final String label;

    TextStyle(String label) {
        this.label = label;
    }
}
