package ly.generalassemb.de.dataservices;

import ly.generalassemb.de.dataservices.api.TemplateLibraryService;
import ly.generalassemb.de.dataservices.constants.Metro;
import ly.generalassemb.de.dataservices.constants.PaymentOption;
import ly.generalassemb.de.dataservices.constants.ProgramFormat;
import ly.generalassemb.de.dataservices.model.TemplateReferenceInquiry;
import ly.generalassemb.de.dataservices.model.TemplateReferenceRequest;
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


        TemplateReferenceRequest request = new TemplateReferenceRequest();
        request.setMetro(Metro.LONDON);
        request.setProgramFormat(ProgramFormat.IMMERSIVE);
        request.setPaymentOption(PaymentOption.EXTENDED);
        request.setRequestedAt(LocalDateTime.now());

        TemplateReferenceInquiry inquiry = new TemplateReferenceInquiry();
        inquiry.setRequest(request);
        inquiry = templateLibraryService.retrieveTemplateReferences(inquiry);

        Assert.assertNotNull(inquiry);

        Assert.assertNotNull(inquiry.getInquiryId());

    }

}