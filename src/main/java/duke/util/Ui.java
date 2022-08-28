package duke.util;

import iohelper.IoHelper;

public class Ui {
    private final String DUKE_INTRODUCTION = "Hello, I'm your personal assistance, Duke.";
    private final String DUKE_HELP = "How can I assist you today?";
    private final String DUKE_END = "Pleasure to be at your service! Run me again if you need more assistance! :)";
    private final String divider = "__________________________________________________________";
    private IoHelper ioHelper;


    public Ui() {
        ioHelper = new IoHelper();
    }

    public String readCommand() {
        String text = ioHelper.getText();
        return text.strip();
    }

    public void showWelcome() {
        ioHelper.print(DUKE_INTRODUCTION);
        ioHelper.print(DUKE_HELP);
    }

    public void showGoodbye() {
        ioHelper.print(DUKE_END);
        ioHelper.closeScanner();
    }

    public void showLine() {
        ioHelper.print(divider);
    }

    public void show(Object message) {
        ioHelper.print(message);
    }

}
