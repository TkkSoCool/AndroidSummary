package com.tkk.androidsummary.knowledgepoint.fourcomponents;

/**
 * Created  on 2017/11/9
 *
 * @author 唐开阔
 * @describe 歌曲实体类
 */

public class Song {
    private String fileName;
    private String fileUrl;

    public Song(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {

        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
