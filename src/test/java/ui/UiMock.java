package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import scottie.ui.Ui;

/**
 * A mock version of a Ui for testing other classes
 */
public class UiMock implements Ui {
    private static final String STARTUP_MESSAGE = "UiMock startup message";

    private final List<String> messagesShown = new ArrayList<>();
    private boolean programEnded = false;

    public void assertMessagesShown(List<String> messages) {
        assertEquals(messages, this.messagesShown);
    }

    public void assertProgramEnded() {
        assertTrue(this.programEnded);
    }

    @Override
    public void showMessages(String... messages) {
        this.messagesShown.addAll(Arrays.asList(messages));
    }

    @Override
    public void showFormattedMessage(String message, Object... args) {
        this.showMessages(String.format(message, args));
    }

    @Override
    public void showStartupMessage() {
        this.showMessages(STARTUP_MESSAGE);
    }

    @Override
    public void showOrderedList(Iterable<?> iterable) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Object obj : iterable) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(obj.toString());
            i++;
        }
        this.showMessages(String.format("OrderedList(%s)", stringBuilder));
    }

    @Override
    public void showError(String errorMessage) {
        this.showMessages(String.format("Error(%s)", errorMessage));
    }

    @Override
    public void showFormattedError(String message, Object... args) {
        this.showError(String.format(message, args));
    }

    @Override
    public void endProgram() {
        this.programEnded = true;
    }
}
