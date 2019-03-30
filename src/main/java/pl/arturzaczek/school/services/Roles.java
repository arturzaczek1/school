package pl.arturzaczek.school.services;


public enum Roles {
    USER("ROLE_USER"),
    TEACHER("ROLE TEACHER"),
    ADMIN("ROLE_ADMIN"),
    PRINCIPAL("ROLE_PRINCIPAL"),
    PUPIL("ROLE_PUPIL"),
    PARENT("ROLE_PARENT");

    private String role;

    Roles (String role_name){
        role= role_name;
    }
}
