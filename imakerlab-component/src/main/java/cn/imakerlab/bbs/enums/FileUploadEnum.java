package cn.imakerlab.bbs.enums;

public enum FileUploadEnum {
    FIGURE("figure", 1024*1024, "D:\\IDEA-workspace\\imakerlab-bbs\\imakerlab-web\\src\\main\\resources\\static\\uploadfile"),
    PICTURE("picture", 1024*1024*3, "D:\\IDEA-workspace\\imakerlab-bbs\\imakerlab-web\\src\\main\\resources\\static\\uploadfile");

    private String value;
    private Integer maxSize;
    private String uploadUrl;

    FileUploadEnum(String value, Integer maxSize, String uploadUrl) {
        this.value = value;
        this.maxSize = maxSize;
        this.uploadUrl = uploadUrl;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getMaxSize() {
        return this.maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }
}