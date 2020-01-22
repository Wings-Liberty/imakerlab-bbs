package cn.imakerlab.bbs.properties;

public class FileProperties {

    private String figureFolderUrl = "D:/IDEA-workspace/imakerlab-bbs/uploadfile";

    private long figureMaxSize = 1024*1024;

    public long getFigureMaxSize() {
        return figureMaxSize;
    }

    public void setFigureMaxSize(long figureMaxSize) {
        this.figureMaxSize = figureMaxSize;
    }

    public String getFigureFolderUrl() {
        return figureFolderUrl;
    }

    public void setFigureFolderUrl(String figureFolderUrl) {
        this.figureFolderUrl = figureFolderUrl;
    }
}
