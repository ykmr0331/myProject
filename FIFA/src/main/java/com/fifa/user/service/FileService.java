package com.fifa.user.service;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("fileService")
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 파일 업로드
     */
    public FileUploadResult  uploadFile(MultipartHttpServletRequest multiRequest) throws Exception {

        // 파라미터 이름을 키로 파라미터에 해당하는 파일 정보를 값으로 하는 Map을 가져온다.
        Map < String, MultipartFile > files = multiRequest.getFileMap();

        // files.entrySet()의 요소를 읽어온다.
        Iterator < Entry<String, MultipartFile>> itr = files.entrySet().iterator();

        MultipartFile mFile;

        // 파일이 업로드 될 경로를 지정한다.
        // 아래의 경우 apple 사용자의 Downloads에 파일이 업로드된다.
//      String filePath = "C:\\Users\\apple\\Downloads\\";
        String filePath = "C:/2023-05-JAVA-DEVELOPER/eclipse-workspaceSpring/FIFA/src/main/resources/static/images/";

        // 파일명이 중복되었을 경우, 사용할 스트링 객체
        String saveFileName = "", savaFilePath = "";

        
        // fileCutName 등의 정보를 FileUploadResult에 설정
        FileUploadResult result = new FileUploadResult();
        
        // 읽어 올 요소가 있으면 true, 없으면 false를 반환한다. 
        while (itr.hasNext()) {

            Entry < String, MultipartFile > entry = itr.next();

            // entry에 값을 가져온다.
            mFile = entry.getValue();
            System.out.println("mFile= "+mFile);
           
            // 파일명: Antony.jpg
            String fileName = mFile.getOriginalFilename();
            System.out.println("fileName = "+fileName);
           
            // 확장자를 제외한 파일명: Antony
            String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));
            System.out.println("fileCutName = "+fileCutName);
            
            // 확장자: jpg
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            System.out.println("fileExt= "+fileExt);
            
            // 저장될 경로와 파일명: 
            String saveFilePath = filePath + File.separator + fileName;
            System.out.println("saveFilePath = "+saveFilePath);
            
            // filePath에 해당되는 파일의 File 객체를 생성한다.
            File fileFolder = new File(filePath);
            System.out.println("fileFolder = "+fileFolder);
            
            
            
            
            
            if (!fileFolder.exists()) {
            	System.out.println("!fileFolder.exist()라면 fileFolder는 "+fileFolder);
                // 부모 폴더까지 포함하여 경로에 폴더를 만든다.
                if (fileFolder.mkdirs()) {
                	System.out.println("!fileFolder.exist()일 떄 fileFolder.mkdirs()가 맞는 경우");
                    logger.info("[file.mkdirs] : Success");
                } else {
                	System.out.println("!fileFolder.exist()일 떄 fileFolder.mkdirs()가 아닌 경우");
                    logger.error("[file.mkdirs] : Fail");
                }
            }
            System.out.println("fileFolder.exists()라면 ");
            System.out.println("saveFilePath은 "+ saveFilePath);
            File saveFile = new File(saveFilePath);
            System.out.println("saveFile은 "+ saveFile);
            
            
            
            
            
            
            
            // saveFile이 File이면 true, 아니면 false
            // 파일명이 중복일 경우 파일명(1).확장자, 파일명(2).확장자 와 같은 형태로 생성한다.
            if (saveFile.isFile()) {// saveFile이 File이면 true, 아니면 false
                boolean _exist = true;
                System.out.println("saveFile.isFile(): 파일명이 중복일 경우>> 파일명(1).확장자 이런거");
                int index = 0;

                // 동일한 파일명이 존재하지 않을때까지 반복한다.
                while (_exist) { // 파일명이 중복일 경우 파일명(1).확장자, 파일명(2).확장자 와 같은 형태로 생성한다.
                	System.out.println("중복인 경우");
                    index++;

                    saveFileName = fileCutName + "(" + index + ")." + fileExt;
                    System.out.println("중복으로 추가된 이미지명"+saveFileName);
                    String dictFile = filePath + File.separator + saveFileName;

                    _exist = new File(dictFile).isFile();
                    System.out.println("이제는 중복인가?(중복이면 true)" + _exist);
                    
                    
                    if (!_exist) {//중복아니면
                    	System.out.println("중복이 아닌 경우");
                        savaFilePath = dictFile;
                    }
                }
                System.out.println("중복에서 빠져나왔다. saveFileName은 "+saveFileName );
                //생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo(File f) 메서드를 이용해서 업로드처리한다.
                mFile.transferTo(new File(savaFilePath));
                result.setFileName(saveFileName);
            
            
            } else { // 파일명이 중복이 아닌 경우
                //생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에 transferTo(File f) 메서드를 이용해서 업로드처리한다.
            	System.out.println("saveFile.isFile()이 아니라면");
            	System.out.println("114의 saveFile은"+saveFile);
                mFile.transferTo(saveFile);
                result.setFileCutName(fileCutName);
                result.setFileName(fileName);
            }
            

        }//while (itr.hasNext()) 읽어올 요소가 있으면 while문 끝

        System.out.println("result는 "+result);
        return result;
    }
}