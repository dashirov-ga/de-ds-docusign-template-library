package ly.generalassemb.de.dataservices;

import ly.generalassemb.de.dataservices.model.TemplateReferenceInquiry;
import ly.generalassemb.de.dataservices.model.TemplateReferenceRequest;
import ly.generalassemb.de.dataservices.model.TemplateReferenceResponse;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateLibraryService {
    @Autowired
    private KieContainer kieContainer;

    public TemplateReferenceInquiry retrieveTemplateReferences(TemplateReferenceInquiry inquiry) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(inquiry);
        kieSession.fireAllRules(); // parallel ?
        kieSession.dispose();
        return inquiry;
    }
}
