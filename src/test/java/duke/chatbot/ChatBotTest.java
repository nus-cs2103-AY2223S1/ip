package duke.chatbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ChatBotTest {
    @Test
    public void initialize() {
        ChatBot chatBot = new ChatBot("ChatBot");
        chatBot.initialize();
        assertTrue(chatBot.isRunning());
    }

    @Test
    public void terminate() {
        ChatBot chatBot = new ChatBot("ChatBot");
        chatBot.terminate();
        assertFalse(chatBot.isRunning());
    }
}
