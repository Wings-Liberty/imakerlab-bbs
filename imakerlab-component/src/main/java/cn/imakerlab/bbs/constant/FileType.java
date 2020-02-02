package cn.imakerlab.bbs.constant;

public enum FileType {
    FIGURE("figure", 1024*1024);

    private String value;
    private Integer maxSize;

    FileType(String value, Integer maxSize) {
    }
}