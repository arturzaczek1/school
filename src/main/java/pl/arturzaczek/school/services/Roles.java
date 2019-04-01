package pl.arturzaczek.school.services;

import lombok.Getter;


@Getter
public enum Roles {
    USER("ROLE_USER"),
    TEACHER("ROLE TEACHER"),
    ADMIN("ROLE_ADMIN"),
    PRINCIPAL("ROLE_PRINCIPAL"),
    STUDENT("ROLE_STUDENT"),
    PARENT("ROLE_PARENT");

    private String role;

    Roles (String role_name){
        role= role_name;
    }
}
