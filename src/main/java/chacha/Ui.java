package chacha;
import java.util.Scanner;

import chacha.tasks.Task;

public class Ui {

    public String readInput() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        return s;
    }

    public String printWelcome() {
        return "Welcome! I'm Chacha.\n" + "How may I assist you?";
    }

    public void printList(TaskList taskList) {
        for (int i = 0; i < taskList.getSize();i++) {
            Task t = taskList.get(i);	      
            System.out.println(i + 1 + 
                "." + 
                t.toString()); 		
        }   
    }

    public void printError(String message) {
        System.out.println("Chacha error: " + message);
    }
}
