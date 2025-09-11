package it.unicam.cs.agricultural_platform.content;

import it.unicam.cs.agricultural_platform.content.user.User;

import java.util.ArrayList;
import java.util.List;

public class Product extends Content{

    private String name;
    private String description;
    private User author;
    private List<Certificate> certificates;
    private List<TransformationProcess> processes;

    public Product(long id) {
        super(id);
        this.certificates = new ArrayList<>();
        this.processes = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Certificate> getCertificates() {
        return this.certificates;
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

    public List<TransformationProcess> getProcesses() {
        return this.processes;
    }

    public boolean addProcess(TransformationProcess process) {
        if(this.processes.contains(process)){
            System.out.println("Processo già esistente");
            return false;
        }
        else {
            this.processes.add(process);
            return true;
        }
    }

    public boolean addProcessList(List<TransformationProcess> processes) {
        if(this.processes.containsAll(processes)){
            System.out.println("Processi già presenti");
            return false;
        }
        else{
            this.processes.addAll(processes);
            return true;
        }
    }


    public boolean removeCertificate(Certificate certificate) {
        if (certificates.contains(certificate)) {
            certificates.remove(certificate);
            return true;
        }
        return false;
    }

    public boolean removeProcess(TransformationProcess process) {
        if(processes.contains(process)) {
            processes.remove(process);
            return true;
        }
        return false;
    }
}
