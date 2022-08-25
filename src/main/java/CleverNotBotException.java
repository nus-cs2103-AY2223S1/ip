public class CleverNotBotException extends Exception{
    private String errorMessage;
    private UITextBox textBox;

    public CleverNotBotException(String errorMessage, UITextBox textBox){
        this.errorMessage = errorMessage;
        this.textBox = textBox;
    }

    @Override
    public String toString(){
        return textBox.coverText(errorMessage);
    }
}
