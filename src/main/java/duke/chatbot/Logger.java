package duke.chatbot;

import java.util.ArrayList;
import java.util.List;

/**
 * Logger class provides a logger to track all previous inputs of the currently active chatbot.
 */
public class Logger {
    private final List<String> logHistory;
    private int currentPointer;

    /**
     * Creates a Logger that has a log of previous inputs and a pointer to the current item.
     */
    public Logger() {
        this.logHistory = new ArrayList<>();
        this.currentPointer = 0;
    }

    /**
     * Updates the log with the new input and changes the pointer to point to the last item.
     * @param input string of the newest input
     */
    public void updateLog(String input) {
        this.logHistory.add(input);
        this.currentPointer = this.logHistory.size();
    }

    public String getNext() {
        if (logHistory.size() == 0) {
            return "";
        }

        this.currentPointer++;
        if (this.currentPointer >= logHistory.size()) {
            this.currentPointer = logHistory.size() - 1;
        }
        return logHistory.get(currentPointer);
    }


    public String getPrevious() {
        if (logHistory.size() == 0) {
            return "";
        }

        this.currentPointer--;
        if (this.currentPointer < 0) {
            this.currentPointer = 0;
        }
        return logHistory.get(currentPointer);
    }
}
