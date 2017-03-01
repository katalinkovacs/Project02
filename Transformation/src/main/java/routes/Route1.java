package routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;//
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import processors.Processor1;


public class Route1 extends RouteBuilder {

        Processor1 processor1Object = new Processor1();

        public void configure() throws Exception {


        /*  From XML to JSON
            from("direct:marshal")
            .marshal(xmlJsonFormat)
            .to("mock:json");

            // From JSON to XML
               from("direct:unmarshal")
              .unmarshal(xmlJsonFormat)
              .to("mock:xml");
         */

            XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();


            //marshall method needs a java object called jaxbDataFormat2
            JaxbDataFormat jaxbDataFormat2 = new JaxbDataFormat("employeeversion2");


            // FROM FILE TO FILE

            from("file:C:/Kati/Marlo/GitRepo/FileReads/Project01/From")
                    //unmarshalling is the process to read in xml and convert to java object using jaxb generated template
                    // classes
                    .unmarshal(jaxbDataFormat1)
                    //this is to invoke processor to transform person1 to person2
                    .bean(processor1Object, "processor1Method")   // object + method
                    //marshalling is the process to convert java to xml -- the processor from previous step returns
                    // a person2 which will be used to create the output xml
                    .marshal(jaxbDataFormat2)
                    .to("file:C:/Kati/Marlo/GitRepo/FileReads/Project01/To");



            // FROM AMQ TO AMQ

          /*  from("amq:data.in")
                    //unmarshalling is the process to read in xml and convert to java object using jaxb generated template
                    // classes
                    .unmarshal(jaxbDataFormatPerson1)
                    //this is to invoke processor to transform person1 to person2
                    .bean(processor, "myprocessor")
                    //marshalling is the process to convert java to xml -- the processor from previous step returns
                    // a person2 which will be used to create the output xml
                    .marshal(jaxbDataFormatPerson2)
                    .to("amq:data.out");
         */




        }

    }




