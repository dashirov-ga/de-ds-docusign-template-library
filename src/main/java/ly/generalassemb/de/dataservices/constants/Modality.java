package ly.generalassemb.de.dataservices.constants;

public enum Modality {
    FULL_TIME("FT"),
    PART_TIME("PT"),
    HYBRID("HY");

    private final String modalityAbbreviation;

    Modality(String modalityAbbreviation) {
        this.modalityAbbreviation = modalityAbbreviation;
    }

    public String getModalityAbbreviation() {
        return modalityAbbreviation;
    }
}
