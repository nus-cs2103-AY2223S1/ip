import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles logging of strings
 */
public class Log {
    private List<String> logs;
    private int count;

    Log() {
        this.logs = new ArrayList<>();
        this.count = 1;
    }

    Log(List<String> log, int count) {
        this.logs = new ArrayList<>(log);
        this.count = count;
    }

    void add(String message) {
        String formattedMessage = String.format("%d. %s", count, message);
        logs.add(formattedMessage);
        count++;
    }

    List<String> getLogs() {
        return new ArrayList<>(this.logs);
    }

    @Override
    public String toString() {
        return String.join("\n", logs);
    }
}
