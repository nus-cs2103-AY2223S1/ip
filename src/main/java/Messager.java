public abstract class Messager {
    protected IOHelper ioHelper;

    public Messager() {
        ioHelper = new IOHelper();
    }

    public String getMessage() {
        String text = ioHelper.getText();
        return text.strip().toLowerCase();
    }

    protected void closeScanner() {
        ioHelper.closeScanner();
    }

    public void message(Object message) {
        ioHelper.print(message);
    }
}
