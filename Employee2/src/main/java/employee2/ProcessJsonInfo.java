package employee2;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ProcessJsonInfo {

        public static void main(String ... args){
            ObjectMapper mapper = new ObjectMapper();

            try {

                // Convert JSON string from file to Object
                Employee2 e2 = mapper.readValue(new File("Employee2/src/main/resources/data/employee2.json"), Employee2.class);
                System.out.println(e2.getFamilyname());
                System.out.println(e2.getGivenname());

                //Pretty print
                String prettyemp2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(e2);
                System.out.println(prettyemp2);



            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }