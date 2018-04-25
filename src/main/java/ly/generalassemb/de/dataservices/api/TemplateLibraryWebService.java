package ly.generalassemb.de.dataservices.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ly.generalassemb.de.dataservices.InstanceCacheItem;
import ly.generalassemb.de.dataservices.constants.Metro;
import ly.generalassemb.de.dataservices.constants.PaymentOption;
import ly.generalassemb.de.dataservices.constants.ProgramFormat;
import ly.generalassemb.de.dataservices.model.TemplateReference;
import ly.generalassemb.de.dataservices.model.TemplateReferenceInquiry;
import ly.generalassemb.de.dataservices.model.TemplateReferenceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
public class TemplateLibraryWebService {
    private static Logger LOGGER = LoggerFactory.getLogger(TemplateLibraryWebService.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    TemplateLibraryService templateLibraryService;
    SkuLookupService skuLookupService;

    public void setSkuLookupService(SkuLookupService skuLookupService) {
        this.skuLookupService = skuLookupService;
    }

    public void setTemplateLibraryService(TemplateLibraryService templateLibraryService) {
        this.templateLibraryService = templateLibraryService;
    }

    @RequestMapping(value = "/templates", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<TemplateReferenceInquiry> templates (
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
        } catch (JsonProcessingException ignored) { }
        return ResponseEntity.ok(inquiry);
    }

    @RequestMapping(value="/templates/sku", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<TemplateReferenceInquiry> templatesForInstance (
            @RequestParam(value="sku" ) String sku,
            @RequestParam(value="paymentOption" , defaultValue = "STANDARD") PaymentOption paymentOption
    ) {
        LOGGER.info("Resolving SKU {} with payment option {}", sku, paymentOption.name());
        Optional<InstanceCacheItem> item = skuLookupService.lookup(sku);
        if (item.isPresent()){
            // found a matching item in cache
            InstanceCacheItem payload = item.get();
            ProgramFormat programFormat=null;
            try {
                 programFormat = ProgramFormat.valueOf(payload.getProgramFormat());
            } catch (Exception ignored){/* */}

            Metro metro = null;
            try{
                metro = Metro.findByName(payload.getMetroName());
            } catch (Exception ignored){/* */}

            TemplateReferenceRequest request = new TemplateReferenceRequest();
            request.setPaymentOption(paymentOption);
            request.setRequestedAt(LocalDateTime.now());
            if (programFormat!=null)
               request.setProgramFormat(programFormat);
            if (metro!=null)
                request.setMetro(metro);
            TemplateReferenceInquiry inquiry = new TemplateReferenceInquiry();
            inquiry.setRequest(request);
            inquiry= templateLibraryService.retrieveTemplateReferences(inquiry);
            try {
                LOGGER.info("Resolving SKU {} with payment option {} -  use template {}", sku, paymentOption.name(), mapper.writeValueAsString(inquiry.getResponse().getTemplateReferenceList()));
            } catch (JsonProcessingException ignored) {/* */}
            return ResponseEntity.ok(inquiry);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
