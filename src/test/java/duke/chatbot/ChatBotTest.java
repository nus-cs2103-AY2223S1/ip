package duke.chatbot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ChatBotTest {
    @Test
    public void initialize() {
        ChatBot chatBot = new ChatBot("Christina");
        chatBot.initialize();
        assertTrue(chatBot.isRunning());
    }

    @Test
    public void terminate() {
        ChatBot chatBot = new ChatBot("Christina");
        chatBot.terminate();
        assertFalse(chatBot.isRunning());
    }
}
