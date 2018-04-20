package ly.generalassemb.de.dataservices;

import ly.generalassemb.de.dataservices.model.TemplateReferenceRequest;
import ly.generalassemb.de.dataservices.model.TemplateReferenceResponse;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateLibrarySerfvice {
    @Autowired
    private KieContainer kieContainer;
    public TemplateReferenceResponse retrieveTemplateReferences(TemplateReferenceRequest request, TemplateReferenceResponse response) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("TemplateReferenceResponse", response);
        kieSession.insert(request);
        kieSession.fireAllRules(); // parallel ?
        kieSession.dispose();
        return response;
    }
}
