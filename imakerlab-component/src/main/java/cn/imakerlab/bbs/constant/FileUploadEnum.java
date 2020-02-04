package cn.imakerlab.bbs.constant;

public enum FileUploadEnum {
    FIGURE("figure", 1024*1024, "D:\\IDEA-workspace\\imakerlab-bbs\\uploadfile"),
    PICTURE("figure", 1024*1024*3, "D:\\IDEA-workspace\\imakerlab-bbs\\uploadfile");

    private String value;
    private Integer maxSize;
    private String uploadUrl;

    FileUploadEnum(String value, Integer maxSize, String uploadUrl) {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getMaxSize() {
        return maxSize;
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
