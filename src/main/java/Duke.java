import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⣀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢀⣴⣿⡿⠟⠋⠉⠀⠀⠀⠀⠀⠉⠛⠻⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣰⣿⠟⠉⠀⠀⠀⠀⠀⠀⢀⣠⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀\n" +
                "⠀⠀⣰⣿⠋⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀\n" +
                "⠀⢰⣿⠇⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀\n" +
                "⠀⣿⡿⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀\n" +
                "⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀\n" +
                "⠀⣿⣷⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀\n" +
                "⠀⢸⣿⡄⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀\n" +
                "⠀⠀⢻⣿⡄⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀\n" +
                "⠀⠀⠀⠹⣿⣦⡀⠀⠀⠀⠀⠀⠀⠈⠛⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠈⠻⣿⣷⣤⣄⣀⠀⠀⠀⠀⠀⣀⣠⣴⣾⣿⣿⣿⡿⠟⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠈⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠉⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⠛⠛⠛⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
        String lineBreak = "°.✩┈┈∘*┈\uD83C\uDF19┈*∘┈┈✩.°";
        System.out.println(logo + "\n" + "Hello I am LUNA!\n" + "How can I be of help?\n" + lineBreak);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            String farewell = lineBreak + "\n" +"゜✧*̣̩☽⋆゜LUNA bids farewell ゜✧*̣̩☽⋆゜\n\n May the Moon\uD83C\uDF19 shine bright and illuminate your night.";

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(farewell);
                break;
            } else {

                System.out.println(lineBreak+ "\n" + input + "\n" + lineBreak);
            }
        }
    }
}
