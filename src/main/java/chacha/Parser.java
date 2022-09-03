package chacha;
public class Parser {
    public String parse(String userInput) {
        String[] inputArray = userInput.split(" ");
        String command = inputArray[0];
        switch (command) {
            case "todo":
                if (inputArray.length == 1) {
                    return "";
                }
        } return "";
    }
    
}
