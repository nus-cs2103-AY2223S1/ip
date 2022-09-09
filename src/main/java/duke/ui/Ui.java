package duke.ui;

public interface Ui {
    void showWelcome();
    String getUserInput();
    void sendMessage(String text);
    void showError(String text);
}
