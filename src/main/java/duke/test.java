package duke;

public class test {
    public static void main(String[] args) {
        String str = "hello        1 2 3 4 5";
        String[] splitStr = str.split("\\s+", 2);
        for(String s: splitStr) {
            System.out.println(s);
        }
    }
}
