import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Base class substitute for String that is compatible with Text
 */
public class Span implements Text {
    private final String text;

    public Span(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public int length() {
        return text.length();
    }

    @Override
    public Stream<Span> lines() {
        return text.lines().map(Span::new);
    }

    @Override
    public Text[] split(String regex) {
        return Stream.of(text.split(regex)).map(Span::new).toArray(Span[]::new);
    }

    @Override
    public Text strip() {
        return new Span(text.strip());
    }

    @Override
    public String toString() {
        return text;
    }
}
