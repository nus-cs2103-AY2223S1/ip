public class BobbyChat {
//        HELLO("\t" + "Hello! I'm Bobby\n" +
//               "\t" + "What can I do for you?"),
//        BYE("\t" + "Byebyeeee. Hope to see you soon!"),
//        LINE("\t------------------------------------");

    private static final String hello = "Hello! I'm Bobby\n" +
                           "\t" + "What can I do for you?";
    private static final String bye = "Byebyeeee. Hope to see you soon!";
    private static final String line = "\t------------------------------------";

    private static void printChat(String c) {
        System.out.println(line);
        System.out.println("\t" + c);
        System.out.println(line);
    }
    public static void echo(String s) {
        printChat(s);
    }
    public static void sayHello() {
        printChat(hello);
    }
    public static void sayBye() {
        printChat(bye);
    }
    public static void added(String s) {
        printChat("added: " + s);
    }
}