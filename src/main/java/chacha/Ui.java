package chacha;
import java.util.ArrayList;
import java.util.Scanner;

import chacha.tasks.Task;

public class Ui {

public String readInput() {
    Scanner input = new Scanner(System.in);
    String s = input.nextLine();
    if (s == "bye") {
        input.close();
    }
    return s;
}

    public String printWelcome() {
        return "Welcome! I'm Chacha.\n" + "How may I assist you?";
    }

    public void printList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size();i++) {
            Task t = taskList.get(i);	      
            System.out.println(i + 1 + 
                "." + 
                t.toString()); 		
        }   
    }

    public void printError(String message) {
        System.out.println("The following error has occurred.\n" + message);
    }
}
