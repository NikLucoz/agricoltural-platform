package it.unicam.cs.agricultural_platform.content;

public enum Certificate {

    DOP("Denominazione di Origine Protetta"),
    IGP("Indicazione Geografica Protetta"),
    STG("Specialità Tradizionale Garantita"),
    BIO("Biologico"),
    FAIR_TRADE("Commercio Equo e Solidale"),
    PAT("Prodotto Agroalimentare Tradizionale"),
    DOC("Denominazione di Origine Controllata"),
    DOCG("Denominazione di Origine Controllata e Garantita"),
    IGT("Indicazione Geografica Tipica"),
    SQNPI("Sistema di Qualità Nazionale Produzione Integrata");

    private String description;

    Certificate(String description){
        this.description = description;
    }

    private String getDescription(){return this.description;}

}
