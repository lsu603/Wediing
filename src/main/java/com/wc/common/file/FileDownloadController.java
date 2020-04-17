package com.wc.common.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
public class FileDownloadController {
    private static final String ARTICLE_IMAGE_REPO = "C:\\B_board\\article_image";

    @RequestMapping(value = "/download.do")
    protected void download(@RequestParam("imageFileName") String imageFileName,
//                            이미지파일이름 설정
                            @RequestParam("articleNO") String articleNO,
                            HttpServletResponse response)throws Exception {
        OutputStream out = response.getOutputStream();
        String downFile = ARTICLE_IMAGE_REPO + "\\" +articleNO+"\\"+ imageFileName;
//        글번호와 파일이름으로 다운로드할 경로 설정
        File file = new File(downFile); //다운로드할 글번호와 파일이름으로 경로를 설정한 파일생성

        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
        FileInputStream in = new FileInputStream(file); //다운로드할 파일을 InputStream에 저장
        byte[] buffer = new byte[1024 * 8];
        while (true) {
            int count = in.read(buffer);
            if (count == -1)
                break;
            out.write(buffer, 0, count);
        }
        in.close();
        out.close();
    }

}
