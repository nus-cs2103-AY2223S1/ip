import ted.ui.UiController;

import java.util.ArrayList;

public class UiStub extends UiController {

    private ArrayList<String> messages = new ArrayList<>();

    @Override
    public void output(String message) {
        messages.add(message);
    }

    @Override
    public void outputLine(String message) {
        messages.add(message + "\n");
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public String getLastMessage() {
        return this.messages.get(this.messages.size() - 1);
    }
}
