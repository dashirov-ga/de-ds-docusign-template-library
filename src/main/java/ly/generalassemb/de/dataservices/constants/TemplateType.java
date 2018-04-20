package ly.generalassemb.de.dataservices.constants;

public enum TemplateType {
    ENROLLMENT_AGREEMENT("EA"),
    FACT_SHEET("FS");
    private final String typeAbbreviation;

    TemplateType(String typeAbbreviation) {
        this.typeAbbreviation = typeAbbreviation;
    }

    public String getTypeAbbreviation() {
        return typeAbbreviation;
    }
}
