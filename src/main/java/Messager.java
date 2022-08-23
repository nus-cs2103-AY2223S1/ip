public abstract class Messager {
    protected IOHelper ioHelper;

    public Messager() {
        ioHelper = new IOHelper();
    }

    protected void closeScanner() {
        ioHelper.closeScanner();
    }

    protected void message(Object message) {
        ioHelper.print(message);
    }

    protected String getText() {
        return ioHelper.getText();
    }
}
