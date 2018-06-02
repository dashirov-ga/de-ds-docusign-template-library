package ly.generalassemb.de.dataservices.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ly.generalassemb.de.dataservices.constants.Metro;
import ly.generalassemb.de.dataservices.constants.ProgramFormat;
import ly.generalassemb.de.dataservices.constants.State;
import ly.generalassemb.de.dataservices.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class TemplateLibraryWebService {
    private static Logger LOGGER = LoggerFactory.getLogger(TemplateLibraryWebService.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    private TemplateLibraryService templateLibraryService;
    private InstanceRepository instanceRepository;

    public void setInstanceRepository(InstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    public void setTemplateLibraryService(TemplateLibraryService templateLibraryService) {
        this.templateLibraryService = templateLibraryService;
    }

    @RequestMapping(value = "/templates", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<TemplateReferenceResponse> templates (
                     @RequestParam(value="sku") String sku,
                     @RequestParam(value="state") String state

    ) {

        State usState;
        try {
            usState = State.valueOfAbbreviation(state.toUpperCase());
        } catch (Exception e){
            try {
                usState = State.valueOf(state.toUpperCase());
            }catch (Exception e2){
                usState = State.UNKNOWN;
            }
        }
        Person applicant = new Person();
        applicant.setStateOfResidence(usState);

        Optional<Instance> item = instanceRepository.getById(sku);
        if (item.isPresent()){
            // found a matching item in cache
            Instance payload = item.get();
            ProgramFormat programFormat=null;
            try {
                 programFormat = ProgramFormat.valueOf(payload.getProgramFormat());
            } catch (Exception ignored){/* */}

            Metro metro = null;
            try{
                metro = Metro.findByName(payload.getMetroName());
            } catch (Exception ignored){/* */}

            TemplateReferenceResponse response = templateLibraryService.retrieveTemplateReferences(applicant,payload);

            try {
                LOGGER.info("Resolving SKU {} for student  {} -  use templates {}",
                        sku,
                        mapper.writeValueAsString(applicant),
                        mapper.writeValueAsString(response.getTemplateReferenceList()));
            } catch (JsonProcessingException ignored) {/* */}
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(null);
    }

}
