package processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import employee1.Employee1;
import employee2.Employee2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;


public class ProcessJsonToXml {


    public static void main(String ... args){
        ObjectMapper mapper = new ObjectMapper();
        try {

            Employee2 e2 = mapper.readValue(new File("Transformation/src/main/resources/data/employee2.json"), Employee2.class);


            //
            Employee1 e1 = new  Employee1();

            e1.setFirstname(e2.getGivenname());
            //e1.getLastname(e2.getFamilyname());


            File fileOut = new File("Transformation/src/main/resources/outbox/employee1toemployee2.xml");
            JAXBContext jaxbContextOut = JAXBContext.newInstance(Employee2.class);
            Marshaller jaxbMarshaller = jaxbContextOut.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(e1, fileOut);




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

