package com.levy.oa.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

/**
 * 文件控制器
 * 
 * @author levy
 *
 */
public class FileController extends Controller {

    public void fileUpload() {
        render("/pages/front/file/file_manager.html");
    }

    /**
     * 获取前端传来的文件，保存在本地
     */
    public void saveFile() {
        UploadFile file = getFile("file", "", 1024 * 1024 * 1024);
        renderHtml("上传成功");
    }

    public void listFiles() {
       
        
        List<String> fileNames = new ArrayList<String>();
        String basepath = getAttr("ctx");
        String  uploadFilePath = basepath+"upload";
        File fileDir = new File(uploadFilePath);
        System.out.println(uploadFilePath);
        File[] files = fileDir.listFiles();
        if((files.length)>0){
             for(File file:files){
            String fileName = file.getName();
            System.out.println("文件名："+fileName);
            fileNames.add(fileName);
        }
        }
       
    }

}
