package ly.generalassemb.de.dataservices.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Let’s also define another business object which will be used for
 * representing template reference response:
 */
public class TemplateReferenceResponse {
    private List<TemplateReference> templateReferenceList = new ArrayList<>();
    private Boolean enrollmentAuthorized;
    private Boolean includeFactSheet;

    public Boolean getEnrollmentAuthorized() {
        return enrollmentAuthorized;
    }

    public void setEnrollmentAuthorized(Boolean enrollmentAuthorized) {
        this.enrollmentAuthorized = enrollmentAuthorized;
    }

    public Boolean getIncludeFactSheet() {
        return includeFactSheet;
    }

    public void setIncludeFactSheet(Boolean includeFactSheet) {
        this.includeFactSheet = includeFactSheet;
    }

    public List<TemplateReference> getTemplateReferenceList() {
        return templateReferenceList;
    }

    public void setTemplateReferenceList(List<TemplateReference> templateReferenceList) {
        this.templateReferenceList = templateReferenceList;
    }
}
