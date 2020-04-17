package com.wc.board.vo;

import java.util.Date;

public class ImageVO {
    private int imageFileNO;
    private String imageFileName;
    private String writeDate;
    private int articleNO;

    public int getImageFileNO() {
        return imageFileNO;
    }

    public void setImageFileNO(int imageFileNO) {
        this.imageFileNO = imageFileNO;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public int getArticleNO() {
        return articleNO;
    }

    public void setArticleNO(int articleNO) {
        this.articleNO = articleNO;
    }
}
