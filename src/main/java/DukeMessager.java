public class DukeMessager extends Messager{
    private static final String DUKE_INTRODUCTION = "Hello, I'm your personal assistance, Duke.";
    private static final String DUKE_HELP = "How can I assist you today?";
    private static final String DUKE_END = "Pleasure to be at your service! Run me again if you need more assistance! :)";

    public DukeMessager() {
        super();
    }

    public void introduction() {
        ioHelper.print(DUKE_INTRODUCTION);
        ioHelper.print(DUKE_HELP);
    }

    public void bye() {
        ioHelper.print(DUKE_END);
        closeScanner();
    }

}
