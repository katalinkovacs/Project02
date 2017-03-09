package routetests;

import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import routes.Route2;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;


public class Route2Test extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder(){
        return new Route2();
    }


    //Set up before running test
    @Override
    public void setUp() throws Exception{
        super.setUp();

        RouteDefinition route2Definition = context.getRouteDefinition("route2");

        //override existing route with this
        route2Definition.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:in");
                interceptSendToEndpoint("file:Transformation/src/main/resources/data/outbox")
                        .skipSendToOriginalEndpoint().to("mock:out");
            }
        });
    }

    @Test
    public void route2_whenValidInput_thenValidOutput() throws Exception{
        // reading in xml request and expected with some
        String request = FileUtils.readFileToString(ResourceUtils.getFile("classpath:input/employee1.xml"));
        String expected = FileUtils.readFileToString(ResourceUtils.getFile("classpath:expected/expectedResultRoute2.json"));

        template.sendBody("direct:in", request);

        Exchange ex = getMockEndpoint("mock:out").getExchanges().get(0);

        String result = ex.getIn().getBody(String.class);

        /*
        Diff diff = new Diff(expected, result);

        List<Difference> differences = new DetailedDiff(diff).getAllDifferences();

        for (Object object : differences) {
            Difference difference = (Difference)object;
            System.out.println("***********************");
            System.out.println(difference);
            System.out.println("***********************");
        }


        assertThat(differences.size(), is(0));

         */
    }
}
