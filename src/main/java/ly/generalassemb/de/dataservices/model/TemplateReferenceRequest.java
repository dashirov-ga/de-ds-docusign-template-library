package ly.generalassemb.de.dataservices.model;

import ly.generalassemb.de.dataservices.constants.Metro;
import ly.generalassemb.de.dataservices.constants.PaymentOption;
import ly.generalassemb.de.dataservices.constants.ProgramFormat;
import ly.generalassemb.de.dataservices.constants.State;

import java.time.LocalDateTime;

/**
 * Define the data which will be used with our service
 * We're going to locate (which includes validation that the object exists and can be used)
 * a template based on the inventory item(s) being sold, delivery location, payment option, etc.
 *
 * This is an object that will be used as a Fact
 */
public class TemplateReferenceRequest {
    private Metro metro;
    private PaymentOption paymentOption;
    private ProgramFormat programFormat;
    private LocalDateTime requestedAt;
    private String programAbbreviation;
    private Boolean isFactSheetRequired = false;
    private State usState;

    public State getUsState() {
        return usState;
    }

    public void setUsState(State usState) {
        this.usState = usState;
    }

    public void setFactSheetRequired(Boolean factSheetRequired) {
        isFactSheetRequired = factSheetRequired;
    }

    public Boolean getFactSheetRequired() {
        return isFactSheetRequired;
    }

    public String getProgramAbbreviation() {
        return programAbbreviation;
    }

    public void setProgramAbbreviation(String programAbbreviation) {
        this.programAbbreviation = programAbbreviation;
    }

    public Metro getMetro() {
        return metro;
    }

    public void setMetro(Metro metro) {
        this.metro = metro;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public ProgramFormat getProgramFormat() {
        return programFormat;
    }

    public void setProgramFormat(ProgramFormat programFormat) {
        this.programFormat = programFormat;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }
}
