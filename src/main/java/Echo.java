import java.util.Scanner;

public class Echo {
    private final String echoText;

    Echo(String echoText) {
        this.echoText = echoText;
    }

    @Override
    public String toString() {
        return this.echoText;
    }
}
