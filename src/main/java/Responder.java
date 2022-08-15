public class Responder {

    public Responder() {
        System.out.println(
            formatResponse("I AM ALFRED. HOW MAY I ASSIST YOU TODAY?"));
    }

    public String respond(String input) {
        return formatResponse(input);
    }

    public String formatResponse(String input) {
        return "____________________________________________________________" +
               "\n \uD83E\uDD16: " + input + "\n" +
               "____________________________________________________________";
    }
}
