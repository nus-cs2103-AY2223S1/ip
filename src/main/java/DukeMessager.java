public class DukeMessager extends Messager{
    private static final String DUKE_INTRODUCTION = "Hello, I'm your personal assistance, Duke.";
    private static final String DUKE_HELP = "How can I assist you today?";
    private static final String DUKE_END = "Pleasure to be at your service! Run me again if you need more assistance! :)";

    public DukeMessager() {
        super();
    }

    public String getMessage() {
        String text = super.getText();
        return text.strip();
    }

    public void introduction() {
        message(DUKE_INTRODUCTION);
        message(DUKE_HELP);
    }

    public void bye() {
        message(DUKE_END);
        closeScanner();
    }

}
