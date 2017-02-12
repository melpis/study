package com.github.melpis.commons.file;

import com.github.melpis.attachfile.vo.AttachfileVO;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class FileUtil {
    public static void removeFile(String parentPath, String fileName) {
        File file = new File(parentPath, fileName);
        file.delete();
    }

    public static void downloadFile(HttpServletResponse response, String parentPath, AttachfileVO attachfile) {
        response.setContentType(attachfile.getContentType() + "; charset=" + "EUC-KR");
        response.setHeader("Content-Length", "" + attachfile.getFileSize());
        try {
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(attachfile.getFileName().getBytes("EUC-KR"), "latin1") + ";");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        File file = new File(parentPath, attachfile.getTempName());
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(fis);

            bos = new BufferedOutputStream(response.getOutputStream());

            int readCount = 0;
            byte[] buffer = new byte[4096];

            while ((readCount = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, readCount);
            }
            bos.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }
}







