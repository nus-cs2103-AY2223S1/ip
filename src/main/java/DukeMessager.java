import java.util.Locale;

public class DukeMessager {
    public static final String DUKE_INTRODUCTION = "Hello, I'm your personal assistance, Duke.";
    public static final String DUKE_HELP = "How can I assist you today?";
    public static final String DUKE_END = "Pleasure to be at your service! Run me again if you need more assistance! :)";
    private IOHelper ioHelper;

    public DukeMessager() {
        ioHelper = new IOHelper();
    }

    public void introduction() {
        ioHelper.print(DUKE_INTRODUCTION);
        ioHelper.print(DUKE_HELP);
    }

    public void bye() {
        ioHelper.print(DUKE_END);
        ioHelper.closeScanner();
    }

    public void message(String message) {
        ioHelper.print(message);
    }

    public String getMessage() {
        String text = ioHelper.getText();
        return text.strip().toLowerCase();
    }


}
