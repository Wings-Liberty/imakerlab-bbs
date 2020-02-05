package cn.imakerlab.bbs.enums;

public enum UserAuthorityEnum {
    Admin("ROLE_ADMIN"),
    USER("");

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    private String authority;
    UserAuthorityEnum(String authority) {
        this.authority=authority;
    }
}
