package doke;

public class Ui {

    private static String MESSAGE_HELLO = "_________________________ \n" + "    Hi, my name is Doke" +
            "\n" + "    What can I do for you? \n" + "    Enter a String!! \n"
            + "_________________________" + "\n";

    private static String MESSAGE_BYE ="_________________________ \n" + "    Bye bye! \n" +
            "_________________________ \n";

    public void printOut(String message){
        if (message.equals("hello")) {
            System.out.println(MESSAGE_HELLO);
        } else if (message.equals("bye")) {
            System.out.println(MESSAGE_BYE);
        } else {
            System.out.println(message);
        }
    }

}
