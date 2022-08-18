import java.util.*;

public class Bro {
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
        else{
            System.out.println(str);
            return output1();
        }
        return str;
    }
}
