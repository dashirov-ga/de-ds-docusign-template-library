package ly.generalassemb.de.dataservices.model;

import java.util.UUID;

public class TemplateReferenceInquiry {
    private TemplateReferenceRequest request;
    private TemplateReferenceResponse response = new TemplateReferenceResponse();
    private UUID inquiryId = UUID.randomUUID();

    public TemplateReferenceRequest getRequest() {
        return request;
    }

    public void setRequest(TemplateReferenceRequest request) {
        this.request = request;
    }

    public TemplateReferenceResponse getResponse() {
        return response;
    }

    public void setResponse(TemplateReferenceResponse response) {
        this.response = response;
    }

    public UUID getInquiryId() {
        return inquiryId;
    }
}
