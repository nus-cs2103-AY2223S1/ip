import java.util.*;
import java.util.ArrayList;

public class Bro {

    ArrayList<String> list1 = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm THE BRO\n" + "What can I do for you?");
        Bro d = new Bro();
        d.output1();

    }
    public String output1(){
        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine();
        if(str.equals("bye")){
            System.out.println("Bye. See you later broo!");
            System.exit(0);
        }
        else if(str.equals("list")){
            int count = 1;
            for(String x: list1){
                System.out.println(count + ". " + x);
                count++;
            }
            return output1();
        }
        else{
            list1.add(str);
            System.out.println("added: " + str);
            return output1();
        }
        return str;
    }
}

