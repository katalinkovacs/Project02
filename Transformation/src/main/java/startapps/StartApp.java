package startapps;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class StartApp {
    public static void main(String ... args){

        ObjectMapper mapper = new ObjectMapper();

        try {

            // Convert JSON string from file to Object
            ProcessJsonInfo p1 = mapper.readValue(new File("JsonPersonInfo/src/main/resources/data/personInfoJson.json"), ProcessJsonInfo.class);
            System.out.println("The FirstName is: "+ p1.getFirstName());


            //ProcessJsonInfo p2 = mapper.writeValue(new File("JsonPersonInfo/src/main/resources/data/personInfoJson_In.json"), ProcessJsonInfo.class);
            //System.out.println("The FirstName is: "+ p1.getFirstName());

            //Full info print
            String fullInfo = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(p1);
            System.out.println(fullInfo);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

