package ly.generalassemb.de.dataservices.model;


public class TemplateReference {
    private String template;
    private Object payload;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public TemplateReference() {
    }

    public TemplateReference(String template) {
        this.template = template;
    }

    public TemplateReference(String template, Object payload) {
        this.template = template;
        this.payload = payload;
    }
}
