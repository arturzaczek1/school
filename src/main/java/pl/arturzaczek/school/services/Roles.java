package pl.arturzaczek.school.services;

import lombok.Getter;


@Getter
public enum Roles {
    ROLE_USER("USER"),
    ROLE_TEACHER("TEACHER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_PRINCIPAL("PRINCIPAL"),
    ROLE_STUDENT("STUDENT"),
    ROLE_PARENT("PARENT");

    private String role;

    Roles (String role_name){
        role= role_name;
    }
}
