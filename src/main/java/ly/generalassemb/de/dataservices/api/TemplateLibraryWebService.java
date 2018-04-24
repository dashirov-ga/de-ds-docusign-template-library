package ly.generalassemb.de.dataservices.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ly.generalassemb.de.dataservices.constants.Metro;
import ly.generalassemb.de.dataservices.constants.PaymentOption;
import ly.generalassemb.de.dataservices.constants.ProgramFormat;
import ly.generalassemb.de.dataservices.model.TemplateReferenceInquiry;
import ly.generalassemb.de.dataservices.model.TemplateReferenceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
public class TemplateLibraryWebService {
    private static Logger LOGGER = LoggerFactory.getLogger(TemplateLibraryWebService.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    TemplateLibraryService templateLibraryService;

    public void setTemplateLibraryService(TemplateLibraryService templateLibraryService) {
        this.templateLibraryService = templateLibraryService;
    }

    @RequestMapping(value = "/templates", method = RequestMethod.GET)
    TemplateReferenceInquiry templates (
                     @RequestParam(value="metro") Metro metro,
                     @RequestParam(value="paymentOption", defaultValue = "STANDARD") PaymentOption paymentOption,
                     @RequestParam(value="programFormat", defaultValue = "IMMERSIVE")ProgramFormat programFormat
    ) {
        LOGGER.info("Resolving {}/{}/{}", metro.name(),paymentOption.name(),programFormat.name());
        TemplateReferenceRequest request = new TemplateReferenceRequest();
        request.setMetro(metro);
        request.setPaymentOption(paymentOption);
        request.setProgramFormat(programFormat);
        request.setRequestedAt(LocalDateTime.now());
        TemplateReferenceInquiry inquiry = new TemplateReferenceInquiry();
        inquiry.setRequest(request);


        inquiry= templateLibraryService.retrieveTemplateReferences(inquiry);
        try {
            LOGGER.info("Resolving {}/{}/{} -  use template {}", metro.name(), paymentOption.name(), programFormat.name(), mapper.writeValueAsString(inquiry.getResponse().getTemplateReferenceList()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return inquiry;
    }
}
