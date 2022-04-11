package com.camilo.dto.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyDTO {

    private final long id;
    private final String name;
    private final String email;
    private final long document;

    public CompanyDTO(@JsonProperty("companyClientId") long id,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("document") long document) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getDocument() {
        return document;
    }

}
