package it.unicam.cs.agricultural_platform.content;

import java.util.List;

public class TransformationProcess {
    private TransformationProcessType type;
    private double duration;
    private List<Certificate> certificates;

    public TransformationProcess(){}

    public TransformationProcessType getType() {
        return this.type;
    }

    public void setType(TransformationProcessType type) {
        this.type = type;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public List<Certificate> getCertificates() {
        return this.certificates;
    }

    public boolean addCertificateList(List<Certificate> certificates) {
        if(this.certificates.containsAll(certificates)){
            System.out.println("Certificati già presenti");
            return false;
        }
        else{
            this.certificates.addAll(certificates);
            return true;
        }
    }

    public boolean addCertificate(Certificate certificate){
        if(this.certificates.contains(certificate)){
            System.out.println("Certificato già esistente");
            return false;
        }
        else {
            this.certificates.add(certificate);
            return true;
        }
    }

    public boolean removeCertificate(Certificate certificate){
        if(this.certificates.contains(certificate)) {
            this.certificates.remove(certificate);
            return true;
        }
        else {
            System.out.println("Nessun certificato da rimuovere");
            return false;
        }
    }

}
