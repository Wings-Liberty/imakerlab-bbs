package cn.imakerlab.bbs.enums;

public enum RoleEnum {

    ROLE_ADMIN("ADMIN"), ROLE_SUPER("SUPER");

    private String authentication;

    RoleEnum(String authentication) {
        this.authentication = authentication;
    }


}