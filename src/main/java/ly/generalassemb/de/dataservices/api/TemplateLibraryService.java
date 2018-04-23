package ly.generalassemb.de.dataservices.api;

import ly.generalassemb.de.dataservices.model.TemplateReferenceInquiry;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateLibraryService {
    private KieContainer kieContainer;

    public void setKieContainer(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public TemplateReferenceInquiry retrieveTemplateReferences(TemplateReferenceInquiry inquiry) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(inquiry);
        kieSession.fireAllRules(); // parallel ?
        kieSession.dispose();
        return inquiry;
    }
}
