import java.util.Scanner;
import java.util.ArrayList;

public class Chacha {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chacha\n" + "What can I do for you?");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        ArrayList<String> list = new ArrayList<String>();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                for (int i = 0; i < list.size();i++) { 		      
	                System.out.println(i + 1 + ". " + list.get(i)); 		
	            }   
            } else {
                list.add(s);  
                System.out.println("added: " + s);  
                 
            } 
            s = input.nextLine();    
        }
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }
}
