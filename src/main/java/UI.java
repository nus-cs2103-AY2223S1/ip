import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "____________________________________________________________";
    
    private final Scanner in;
    private final PrintStream out;
    
    UI() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }
    
    private boolean shouldIgnore(String rawInput) {
        return rawInput.trim().isEmpty();
    }
    
    public String getUserCommand() {
        String rawInput = in.nextLine();
        
        while (shouldIgnore(rawInput)) {
            rawInput = in.nextLine();
        }
        
        return rawInput;
    }
    
    public void showToUser(String... message) {
        out.println(DIVIDER);
        for (String m : message) {
            out.println("\t" + m.replace("\n", LS + "\t"));
        }
        out.println(DIVIDER);
    }
    
    public void showIntroMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Hello I am Duke";
        
        showToUser(logo, intro);
    }
    
    public void showOutroMsg() {
        String outro = "See ya";
        showToUser(outro);
    }
    
}
