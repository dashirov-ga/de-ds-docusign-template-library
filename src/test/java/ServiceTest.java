import ly.generalassemb.de.dataservices.App;
import ly.generalassemb.de.dataservices.TemplateLibrarySerfvice;
import ly.generalassemb.de.dataservices.constants.Metro;
import ly.generalassemb.de.dataservices.constants.PaymentOption;
import ly.generalassemb.de.dataservices.constants.ProgramFormat;
import ly.generalassemb.de.dataservices.model.TemplateReference;
import ly.generalassemb.de.dataservices.model.TemplateReferenceRequest;
import ly.generalassemb.de.dataservices.model.TemplateReferenceResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class ServiceTest {

    @Test
    @Ignore
    public void whenDeliveredInCalifornia_twoTemplatesAreIncluded() {
        TemplateReferenceRequest request = new TemplateReferenceRequest();
        request.setMetro(Metro.LONDON);
        request.setPaymentOption(PaymentOption.STANDARD);
        request.setProgramFormat(ProgramFormat.IMMERSIVE);
        request.setRequestedAt(LocalDateTime.now());

        TemplateLibrarySerfvice templateLibrarySerfvice = new TemplateLibrarySerfvice();
        TemplateReferenceResponse response =  templateLibrarySerfvice.retrieveTemplateReferences(request,new TemplateReferenceResponse());
        List<TemplateReference> action = response.getTemplateReferenceList();

        Assert.assertEquals(2, action.size() );

        List<TemplateReference> enrollmentAgreementDocuments = action.stream().filter(t->t.getTemplate().startsWith("EA")).collect(Collectors.toList());
        Assert.assertEquals(1, enrollmentAgreementDocuments.size());

        List<TemplateReference> factSheetDocuments = action.stream().filter(t->t.getTemplate().startsWith("FS:")).collect(Collectors.toList());
        Assert.assertEquals(1, factSheetDocuments.size());

        Assert.assertEquals("FS:USA:CA:LA:IMMERSIVE:DSI:V001.000.000", factSheetDocuments.get(0).getTemplate());
    }
}