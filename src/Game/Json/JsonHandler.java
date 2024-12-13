package Game.Json;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JsonHandler {
    private static String FILE_NAME = "./Json/";


    /**
     * gets a section from the json file
     * @param section that we want to get
     * @return an Object with the content of the section
     *
     * @throws IOException          if an I/O error occurs
     * @throws ParseException       if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    public static Object getFromFile(String section) throws IOException, ParseException, KeyNotFoundException {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(new FileReader(FILE_NAME));

        if (obj.get(section) == null) {
            throw new KeyNotFoundException("A chave inserida não existe");
        }

        return obj.get(section);
    }

    /**
     * gets an int from the json file
     * @param sectionInt that we want to get
     * @return an int of that section
     *
     * @throws IOException          if an I/O error occurs
     * @throws ParseException       if a parsing error occurs
     * @throws KeyNotFoundException if a required key is not found in the JSON data
     */
    public static int getInt(String sectionInt) throws IOException, ParseException, KeyNotFoundException {
        Long value = (Long) getFromFile(sectionInt);

        return value.intValue();
    }

    public static void setFile(String file) {
        FILE_NAME += file;
    }
}
