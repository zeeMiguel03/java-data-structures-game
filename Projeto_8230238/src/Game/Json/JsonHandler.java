package Game.Json;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JsonHandler {
    private static final String FILE_NAME = "./Json/Mission.json";
    private JSONParser parser;

    public static Object getFromFile(String section) throws IOException, ParseException, KeyNotFoundException {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader(FILE_NAME));

        if (obj.get(section) == null) {
            throw new KeyNotFoundException("A chave inserida não existe");
        }

        return obj.get(section);
    }

    public static int getInt(String what) throws IOException, ParseException, KeyNotFoundException {
        Long value = (Long) getFromFile(what);

        return value.intValue();
    }
}
