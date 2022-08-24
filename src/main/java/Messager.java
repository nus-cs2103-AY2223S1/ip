public class Messager {
    protected IOHelper ioHelper;

    public Messager() {
        ioHelper = new IOHelper();
    }

    protected void closeScanner() {
        ioHelper.closeScanner();
    }

    public void message(Object message) {
        ioHelper.print(message);
    }

    protected String getText() {
        return ioHelper.getText();
    }
}
