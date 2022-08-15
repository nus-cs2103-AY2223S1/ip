public class Tumu {
    public static void main(String[] args) {
        greeting();
    }

    private static void greeting() {
        String logo = "" +
                " ▄▄▄▄▄▄▄ ▄▄   ▄▄ ▄▄   ▄▄ ▄▄   ▄▄ \n" +
                "█       █  █ █  █  █▄█  █  █ █  █\n" +
                "█▄     ▄█  █ █  █       █  █ █  █\n" +
                "  █   █ █  █▄█  █       █  █▄█  █\n" +
                "  █   █ █       █       █       █\n" +
                "  █   █ █       █ ██▄██ █       █\n" +
                "  █▄▄▄█ █▄▄▄▄▄▄▄█▄█   █▄█▄▄▄▄▄▄▄█\n\n";
        String greetingMessage = "Hi! I am Tumu. Nice to meat you.\n" +
                "What is on your mind today?\n";

        System.out.println(logo + greetingMessage);
    }
}
