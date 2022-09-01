public class CleverNotBotException extends Exception{
    private String errorMessage;
    private UI textBox;

    public CleverNotBotException(String errorMessage, UI textBox){
        this.errorMessage = errorMessage;
        this.textBox = textBox;
    }

    @Override
    /*public String toString(){
        return textBox.coverText(errorMessage);
    }
     */
    public String toString(){
        return errorMessage;
    }
}
