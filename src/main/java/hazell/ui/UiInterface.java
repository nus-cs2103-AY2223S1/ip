package hazell.ui;

public interface UiInterface {
    boolean hasNextUserInput();

    String getNextUserInput();

    void displayUserInput(String input);

    void displayBotResponse(String response);

}
