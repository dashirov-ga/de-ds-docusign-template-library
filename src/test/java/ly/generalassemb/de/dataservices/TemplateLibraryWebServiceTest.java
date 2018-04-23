package ly.generalassemb.de.dataservices;

import ly.generalassemb.de.dataservices.api.TemplateLibraryService;
import ly.generalassemb.de.dataservices.api.TemplateLibraryWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TemplateLibraryService.class, TemplateLibraryWebService.class})
@ComponentScan("ly.generalassemb.de.dataservices")
public class TemplateLibraryWebServiceTest {

    @Test
    public void contextLoads() throws Exception {
    }

    // TODO: Add REST API Tests Here!!!
}
