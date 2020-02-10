package cn.imakerlab.bbs.enums;

public enum LabelEnum {

    JAVA("JAVA", false), C("C/C++", false), PHP("PHP", false),
    HTML("前端", false), Notices("公告", true), Activities("活动", true);

    private String des;
    private boolean isAdminLabel;

    LabelEnum(String des, boolean isAdminLabel) {
        this.des = des;
        this.isAdminLabel = isAdminLabel;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isAdminLabel() {
        return isAdminLabel;
    }

    public void setIsAdminLabel(boolean adminLabel) {
        isAdminLabel = adminLabel;
    }
}
