import java.util.stream.Stream;

public interface Text {
    String getText();
    int length();
    Stream<? extends Text> lines();
    Text[] split(String regex);
    Text strip();
}