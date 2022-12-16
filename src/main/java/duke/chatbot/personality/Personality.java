package duke.chatbot.personality;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.yaml.snakeyaml.Yaml;

import duke.chatbot.personality.exceptions.LoadPersonalityException;

/**
 * Personality class provides a personality with unique responses.
 */
public class Personality {
    private final String name;
    private Map<String, Map<String, List<String>>> responseTable;

    /**
     * Creates a personality with the name provided.
     *
     * @param name name of the personality to load
     */
    public Personality(String name) {
        this.name = name;
        this.responseTable = new HashMap<>();
    }

    /**
     * Loads the personality from a yaml file.
     *
     * @throws LoadPersonalityException thrown when the yaml does not exist
     */
    public void loadPersonality() throws LoadPersonalityException {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/personalities/" + this.name + ".yml");
            Yaml yaml = new Yaml();
            this.responseTable = yaml.load(inputStream);
        } catch (Exception exception) {
            throw new LoadPersonalityException();
        }
    }

    /**
     * Returns the formulated response according to the personality and response content.
     *
     * @param command the command to respond to
     * @param responseContent the content of the response
     * @return A formutead response based on personality
     */
    public String formulateResponse(String command, String... responseContent) {
        Random random = new Random(System.currentTimeMillis());
        List<String> responseHeadList = this.responseTable.get(command).get("HEAD");
        List<String> responseTailList = this.responseTable.get(command).get("TAIL");
        String responseHead = responseHeadList.get(random.nextInt(responseHeadList.size()));
        String responseTail = responseTailList.get(random.nextInt(responseTailList.size()));

        if (responseContent.length == 0) {
            return responseHead;
        }

        if (responseContent.length == 1) {
            return responseHead + responseContent[0] + responseTail;
        }

        if (responseContent.length == 2) {
            List<String> responseBodyList = this.responseTable.get(command).get("BODY");
            String responseBody = responseBodyList.get(random.nextInt(responseBodyList.size()));
            return responseHead + responseContent[0] + responseBody + responseContent[1] + responseTail;
        }
        return "";
    }
}
