package duke;

public class Parser {
    
    private String[] formattedInput;
    
    public Parser() {
    }
    
    protected String[] parseInput(String input)  {
        formattedInput = input.split(" ", 2);
        return formattedInput;
    }
    
    protected String getKeyword(String input) {
        parseInput(input);
        return formattedInput[0];
    }
    
    protected int getTaskIndex(String[] words) {
        return Integer.parseInt(words[1]) - 1;
    }
}
