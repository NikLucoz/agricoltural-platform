package it.unicam.cs.agricultural_platform.builder;

import it.unicam.cs.agricultural_platform.content.Certificate;
import it.unicam.cs.agricultural_platform.content.Product;
import it.unicam.cs.agricultural_platform.content.TransformationProcess;

import java.util.List;

public class ProductBuilder {
    private long id;
    private String name;
    private String description;
    private List<Certificate> certificates;
    private List<TransformationProcess> processes;

    public ProductBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
        return this;
    }

    public ProductBuilder setProcesses(List<TransformationProcess> processes) {
        this.processes = processes;
        return this;
    }

    public Product createProduct() {
        Product product = new Product(id);
        product.setName(name);
        product.setDescription(description);

        if (certificates != null) {
            product.addCertificateList(certificates);
        }
        if (processes != null) {
            product.addProcessList(processes);
        }

        return product;
    }
}
