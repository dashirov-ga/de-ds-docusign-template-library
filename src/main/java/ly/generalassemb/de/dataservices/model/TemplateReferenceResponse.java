package ly.generalassemb.de.dataservices.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Let’s also define another business object which will be used for
 * representing template reference response:
 */
public class TemplateReferenceResponse {
    private List<TemplateReference> templateReferenceList = new ArrayList<>();

    public List<TemplateReference> getTemplateReferenceList() {
        return templateReferenceList;
    }

    public void setTemplateReferenceList(List<TemplateReference> templateReferenceList) {
        this.templateReferenceList = templateReferenceList;
    }
}
