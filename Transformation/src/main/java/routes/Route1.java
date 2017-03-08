package routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;//
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import processors.Processor1;


public class Route1 extends RouteBuilder {

    Processor1 processor1Object = new Processor1();

    public void configure() throws Exception {

        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();


        //marshall method needs a java object called jaxbDataFormat2
        JaxbDataFormat jaxbDataFormat2 = new JaxbDataFormat("employeeversion2");


        //from("file:C:/Kati/Marlo/GitRepo/FileReads/Project01/From")
        from("file:Transformation/src/main/resources/data/inbox1?noop=true")
                .unmarshal(xmlJsonFormat)
                .bean(processor1Object, "processor1Method")   // object + method
                .marshal(jaxbDataFormat2)
                .to("file:Transformation/src/main/resources/data/outbox");
        }

}