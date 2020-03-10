package com.koev.cardealerxml.domain.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Supplier extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "is_importer", nullable = false)
    private Boolean isImporter;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private Set<Part> parts;

    public Supplier(String name, boolean isImporter) {
        this();
        this.name = name;
        this.isImporter = isImporter;
    }

    public Supplier(){
        this.parts = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        this.isImporter = importer;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public void addPart(Part part){
        this.parts.add(part);
    }
}
