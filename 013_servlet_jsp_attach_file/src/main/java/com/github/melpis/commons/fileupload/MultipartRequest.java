package com.github.melpis.commons.fileupload;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import com.github.melpis.fileupload.vo.AttachfileVO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MultipartRequest {

    private int threshold = 1024 * 100;
    private String tmepPath = "/temp/";
    private int sizeMax = 1024 * 1024 * 100;

    private Map<String, String> parameters = new HashMap<String, String>();
    private Map<String, AttachfileVO> files = new HashMap<String, AttachfileVO>();


    public MultipartRequest(HttpServletRequest request, String realPath) throws Exception {
        parseRequset(request, realPath);

    }


    private void parseRequset(HttpServletRequest request, String realPath) throws Exception {

        if (ServletFileUpload.isMultipartContent(request)) {

            File tempDir = new File(this.tmepPath);

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(this.threshold);
            factory.setRepository(tempDir);

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(this.sizeMax);

            List<FileItem> fileItemList = upload.parseRequest(request);

            for (int indexI = 0; indexI < fileItemList.size(); indexI++) {

                FileItem fileItem = fileItemList.get(indexI);

                if (fileItem.isFormField()) {
                    String key = fileItem.getFieldName();
                    String value = fileItem.getString();

                    this.parameters.put(key, value);


                } else {
                    String key = fileItem.getFieldName();
                    String fileName = fileItem.getName();
                    long fileSize = fileItem.getSize();
                    String contentType = fileItem.getContentType();
                    String tempName = UUID.randomUUID().toString();

                    AttachfileVO attachfileVO = new AttachfileVO();

                    attachfileVO.setFileName(fileName);
                    attachfileVO.setContentType(contentType);
                    attachfileVO.setFileSize(fileSize);
                    attachfileVO.setTempName(tempName);


                    this.files.put(key, attachfileVO);

                    File realDir = new File(realPath, tempName);

                    fileItem.write(realDir);
                }
            }
        }
    }


    public String getParamters(String key) {
        String returnValue = null;
        if (!this.parameters.containsKey(key)) {
            return returnValue;
        }

        returnValue = this.parameters.get(key);

        return returnValue;
    }


    public AttachfileVO getfiles(String key) {
        AttachfileVO returnValue = null;
        if (!this.files.containsKey(key)) {
            return returnValue;
        }

        returnValue = this.files.get(key);

        return returnValue;
    }

}
