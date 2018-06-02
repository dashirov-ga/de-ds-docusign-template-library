package ly.generalassemb.de.dataservices.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import ly.generalassemb.de.dataservices.model.*;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateLibraryService {
    private static final  Logger LOGGER = LoggerFactory.getLogger(TemplateLibraryService.class);
    private KieContainer kieContainer;

    private ObjectMapper mapper = new ObjectMapper();

    public void setKieContainer(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public TemplateReferenceResponse retrieveTemplateReferences(Person person, Instance instance) {
        KieSession kieSession = kieContainer.newKieSession();
        TemplateReferenceResponse response = new TemplateReferenceResponse();

        kieSession.insert(response);
        kieSession.insert(person);
        kieSession.insert(instance);

        kieSession.fireAllRules(); // parallel ?
        kieSession.dispose();

        return response;
    }
}
