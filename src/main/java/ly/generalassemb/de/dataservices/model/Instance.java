package ly.generalassemb.de.dataservices.model;

public class Instance {
    private String inventoryManagementSystem;
    private String stockKeepingUnit;
    private String listCurrency;
    private Double listPrice;
    private String metroName;
    private String programFormat;
    private String programAbbreviation;

    public Instance() {
    }

    public String getInventoryManagementSystem() {
        return inventoryManagementSystem;
    }

    public void setInventoryManagementSystem(String inventoryManagementSystem) {
        this.inventoryManagementSystem = inventoryManagementSystem;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public String getListCurrency() {
        return listCurrency;
    }

    public void setListCurrency(String listCurrency) {
        this.listCurrency = listCurrency;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public String getMetroName() {
        return metroName;
    }

    public void setMetroName(String metroName) {
        this.metroName = metroName;
    }

    public String getProgramFormat() {
        return programFormat;
    }

    public void setProgramFormat(String programFormat) {
        this.programFormat = programFormat;
    }

    public String getProgramAbbreviation() {
        return programAbbreviation;
    }

    public void setProgramAbbreviation(String programAbbreviation) {
        this.programAbbreviation = programAbbreviation;
    }
}
