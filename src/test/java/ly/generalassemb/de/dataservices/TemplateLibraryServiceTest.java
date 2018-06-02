package ly.generalassemb.de.dataservices;

import ly.generalassemb.de.dataservices.api.TemplateLibraryService;
import ly.generalassemb.de.dataservices.constants.Metro;
import ly.generalassemb.de.dataservices.constants.PaymentOption;
import ly.generalassemb.de.dataservices.constants.ProgramFormat;
import ly.generalassemb.de.dataservices.constants.State;
import ly.generalassemb.de.dataservices.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TemplateLibraryConfiguration.class)
public class TemplateLibraryServiceTest {

    @Autowired
    private TemplateLibraryService templateLibraryService;


    @Test
    public void contextLoads() throws Exception {
    }

    @Test
    public void templatesTest(){

        Person person = new Person();
        person.setStateOfResidence(State.CALIFORNIA);
        Instance instance = new Instance();
        instance.setStockKeepingUnit("43466");


        TemplateReferenceResponse outcome = templateLibraryService.retrieveTemplateReferences(person,instance);
        Assert.assertNotNull(outcome);

        Assert.assertNotNull(outcome.getTemplateReferenceList());

    }

}