import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello from\n"  + LOGO + "\nHow can I help you ?\n" ;
    private static final List<String> INPUT_LIST = new ArrayList<>();

    public static void addWord (String word){
        Integer index = INPUT_LIST.size();
        INPUT_LIST.add(word);
        System.out.println("added: " + word);
    }

    public static void printList(){
        for(int i = 0; i<INPUT_LIST.size(); i++){
            Integer index = i+1;
            System.out.println(index + ". " + INPUT_LIST.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);

        while(scanner.hasNext()){
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if( userInput.equals("list")){
                printList();
            } else {
                addWord(userInput);
                continue;
            }
        }


    }
}
