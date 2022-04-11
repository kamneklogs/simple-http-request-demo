package com.camilo.dto.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyCreationDTO {
    private final String name;
    private final String email;
    private final long document;
    private final String password;
    private final String createdWithinDomain;

    public CompanyCreationDTO(@JsonProperty("name") final String name, @JsonProperty("email") final String email,
            @JsonProperty("document") final long document, @JsonProperty("password") final String password,
            @JsonProperty("createdWithinDomainBase") final String createdWithinDomain) {
        this.name = name;
        this.email = email;
        this.document = document;
        this.password = password;
        this.createdWithinDomain = createdWithinDomain;
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

    public String getPassword() {
        return password;
    }

    public String getCreatedWithinDomain() {
        return createdWithinDomain;
    }

    @Override
    public String toString() {
        return "CompanyCreationDTO [createdWithinDomain=" + createdWithinDomain + ", document=" + document + ", email="
                + email + ", name=" + name + ", password=" + password + "]";
    }
}
