package com.server.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileIOUtil {

    private static Logger logger = LoggerFactory.getLogger(FileIOUtil.class);

    /**
     * 파일 경로를 전달 받아서 파일을 복사하는 메소드
     *
     * @param String srcFilePath 복사할 대상 파일 경로
     * @param String destFilePath 복사될 파일 경로
     * @throws IOException
     */
    public void fileCopy(String srcFilePath, String destFilePath) throws IOException {

        File srcFile = new File(setFilePathReplaceAll(srcFilePath));
        File destFile = new File(setFilePathReplaceAll(destFilePath));

        fileCopy(srcFile, destFile);

    }

    /**
     * 파일을 전달 받아서 파일을 복사하는 메소드
     *
     * @param File srcFile 복사할 대상 파일
     * @param File destFile 복사될 파일
     * @throws IOException
     */
    public void fileCopy(File srcFile, File destFile) throws IOException {
        FileUtils.copyFile(srcFile, destFile);
    }

    /**
     * destFilePath 경로에 해당하는 파일에 createContent내용을 등록하는 메소드 입니다. fileAppend 은 이어쓰기 할지
     * 여부를 지정하는 것으로 이어쓰기 하는경우 true를 덮어 쓰기 하고자 하는경우 false를 입력 하면 됩니다. fileEncoding 값은
     * 입력 하지 않아도 됩니다.
     *
     * @param String destFilePath 글을 등록할 파일 경로
     * @param Stirng createContent 등록할 글 내용
     * @param        boolean fileAppend 파일에 글 등록시 이어붙이기 할지 덮어쓰기 할지 결정(ex>true
     *               이어쓰기,false 덮어쓰기)
     * @param String fileEncoding 글등록시 인코딩 값을 정의(ex> euc-kr, utf-8)
     */
    public void writingToaFile(String destFilePath, String createContent, boolean fileAppend, String fileEncoding)
            throws IOException, InvocationTargetException, SQLException, Exception {

        File destFile = new File(setFilePathReplaceAll(destFilePath));

        if (fileAppend) {
            if (fileEncoding != null && !"".equals(fileEncoding)) {
                FileUtils.writeStringToFile(destFile, createContent, fileEncoding, fileAppend);
            } else {
                FileUtils.writeStringToFile(destFile, createContent, fileAppend);
            }
        } else {
            if (fileEncoding != null && !"".equals(fileEncoding)) {
                FileUtils.writeStringToFile(destFile, createContent, fileEncoding);
            } else {
                FileUtils.writeStringToFile(destFile, createContent);
            }
        }

    }

    /**
     * destFile 파일에 createContent내용을 등록하는 메소드 입니다. fileAppend 은 이어쓰기 할지 여부를 지정하는 것으로
     * 이어쓰기 하는경우 true를 덮어 쓰기 하고자 하는경우 false를 입력 하면 됩니다. fileEncoding 값은 입력 하지 않아도
     * 됩니다.
     *
     * @param File   destFile 글을 등록할 파일
     * @param Stirng createContent 등록할 글 내용
     * @param        boolean fileAppend 파일에 글 등록시 이어붙이기 할지 덮어쓰기 할지 결정(ex>true
     *               이어쓰기,false 덮어쓰기)
     * @param String fileEncoding 글등록시 인코딩 값을 정의(ex> euc-kr, utf-8)
     */
    public void writingToaFile(File destFile, String createContent, boolean fileAppend, String fileEncoding)
            throws IOException, InvocationTargetException, SQLException, Exception {

        if (fileAppend) {
            if (fileEncoding != null && !"".equals(fileEncoding)) {
                FileUtils.writeStringToFile(destFile, createContent, fileEncoding, fileAppend);
            } else {
                FileUtils.writeStringToFile(destFile, createContent, fileAppend);
            }
        } else {
            if (fileEncoding != null && !"".equals(fileEncoding)) {
                FileUtils.writeStringToFile(destFile, createContent, fileEncoding);
            } else {
                FileUtils.writeStringToFile(destFile, createContent);
            }
        }

    }

    /**
     * srcFilePath 경로에 해당하는 파일의 내용을 읽어오는 메소드 입니다. fileEncoding 값은 정의하지 않아도 됩니다.
     *
     * @param String srcFilePath 읽어올 파일 경로
     * @param String fileEncoding 파일 인코딩 종류(ex>euc-kr, utf-8)
     * @return String 파일의 내용
     */
    public String readTheFile(String srcFilePath, String fileEncoding) throws IOException, InvocationTargetException, SQLException, Exception {

        String fileContent = "";

        File srcFile = new File(setFilePathReplaceAll(srcFilePath));

        if (fileEncoding != null && !"".equals(fileEncoding)) {
            fileContent = FileUtils.readFileToString(srcFile, fileEncoding);
        } else {
            fileContent = FileUtils.readFileToString(srcFile);
        }

        return fileContent;

    }

    /**
     * srcFile 파일의 내용을 읽어오는 메소드 입니다. fileEncoding 값은 정의하지 않아도 됩니다.
     *
     * @param File   srcFile 읽어올 파일
     * @param String fileEncoding 파일 인코딩 종류(ex>euc-kr, utf-8)
     * @return String 파일의 내용
     */
    public String readTheFile(File srcFile, String fileEncoding) throws IOException, InvocationTargetException, SQLException, Exception {

        String fileContent = "";

        if (fileEncoding != null && !"".equals(fileEncoding)) {
            fileContent = FileUtils.readFileToString(srcFile, fileEncoding);
        } else {
            fileContent = FileUtils.readFileToString(srcFile);
        }

        return fileContent;

    }

    /**
     * exists 파일 또는 폴더 존재 여부를 확인하는 메소드
     *
     * @param String filePath 파일 또는 폴더 경로
     * @return boolean 파일 또는 폴더 존재 여부
     */
    public boolean exists(String filePath) {

        boolean isExists = true;
        File newFolder = new File(setFilePathReplaceAll(filePath));

        if (newFolder.exists()) {
            isExists = true;
        } else {
            isExists = false;
        }

        return isExists;

    }

    /**
     * exists 파일 또는 폴더 존재 여부를 확인하는 메소드
     *
     * @param File file 파일 객체
     * @return boolean 파일 또는 폴더 존재 여부
     */
    public boolean exists(File file) {

        boolean isExists = true;

        if (file.exists()) {
            isExists = true;
        } else {
            isExists = false;
        }

        return isExists;

    }

    /**
     * folderPath 경로에 해당하는 폴더를 생성하는 메소드
     *
     * @param String folderPath 생성할 폴더 경로
     * @param        boolean isRecursive 폴더를 생성할때 상위 디렉토리도 같이 생성할지 여부(true:
     *               mkdirs/false: mkdir)
     * @return boolean 작업의 성공 여부
     */
    public boolean createFolder(String folderPath, boolean isRecursive) {

        boolean isSuccess = true;
        File newFolder = new File(setFilePathReplaceAll(folderPath));

        if (!newFolder.exists()) {
            if (!newFolder.isFile()) {
                if (isRecursive) {
                    newFolder.mkdirs();
                } else {
                    newFolder.mkdir();
                }
            } else {
                isSuccess = false;

                StringBuilder sb = new StringBuilder();

                sb.append("FileIOUtil createFolder : 같은 이름의 파일이 존재 합니다.")
                  .append("(")
                  .append(folderPath)
                  .append(")");

                logger.debug(sb.toString());
            }
        } else {
            isSuccess = false;

            StringBuilder sb = new StringBuilder();

            sb.append("FileIOUtil createFolder : 파일 혹은 폴더가 존재 합니다.")
              .append("(")
              .append(folderPath)
              .append(")");

            logger.debug(sb.toString());
        }

        return isSuccess;

    }

    /**
     * newFolder 폴더를 생성하는 메소드
     *
     * @param File newFolder 생성할 폴더
     * @param      boolean isRecursive 폴더를 생성할때 상위 디렉토리도 같이 생성할지 여부(true:
     *             mkdirs/false: mkdir)
     * @return boolean 작업의 성공 여부
     */
    public boolean createFolder(File newFolder, boolean isRecursive) {

        boolean isSuccess = true;

        if (!newFolder.exists()) {
            if (!newFolder.isFile()) {
                if (isRecursive) {
                    newFolder.mkdirs();
                } else {
                    newFolder.mkdir();
                }
            } else {
                isSuccess = false;
                logger.debug("FileIOUtil createFolder : 같은 이름의 파일이 존재 합니다.");
            }
        } else {
            isSuccess = false;
            logger.debug("FileIOUtil createFolder : 파일 혹은 폴더가 존재 합니다.");
        }

        return isSuccess;

    }

    /**
     * filePath 경로에 해당하는 파일을 생성하는 메소드
     *
     * @param String filePath 생성할 파일 경로
     */
    public void createFile(String filePath) throws IOException, InvocationTargetException, SQLException, Exception {

        File newFile = new File(setFilePathReplaceAll(filePath));

        if (!newFile.exists()) {
            if (!newFile.isDirectory()) {
                newFile.createNewFile();
            } else {
                logger.debug("FileIOUtil createFile : 같은 이름의 폴더가 존재 합니다.");
            }
        } else {
            logger.debug("FileIOUtil createFile : 파일 혹은 폴더가 존재 합니다.");
        }

    }

    /**
     * newFile 파일을 생성하는 메소드
     *
     * @param File newFile 생성할 파일
     */
    public void createFile(File newFile) throws IOException, InvocationTargetException, SQLException, Exception {

        if (!newFile.exists()) {
            if (!newFile.isDirectory()) {
                newFile.createNewFile();
            } else {
                logger.debug("FileIOUtil createFile : 같은 이름의 폴더가 존재 합니다.");
            }
        } else {
            logger.debug("FileIOUtil createFile : 파일 혹은 폴더가 존재 합니다.");
        }

    }

    /**
     * deleteFilePath 경로의 파일을 삭제하는 메소드
     *
     * @param String deleteFilePath 삭제할 파일 경로
     */
    public void deleteFile(String deleteFilePath) throws IOException, InvocationTargetException, SQLException, Exception {

        File deleteFile = new File(setFilePathReplaceAll(deleteFilePath));
        deleteFile.delete();

    }

    /**
     * deleteFilePath 파일을 삭제하는 메소드
     *
     * @param String deleteFile 삭제할 파일
     */
    public void deleteFile(File deleteFile) {
        deleteFile.delete();
    }

    /**
     * deleteFilePath 경로의 파일을 삭제하는 메소드 다른 스레드 또는 프로세스에 의해 파일이 Lock 되어 있을 경우 JVM이
     * 종료할때 파일을 삭제
     *
     * @param String deleteFilePath 삭제할 파일 경로
     */
    public void deleteOnExit(String deleteFilePath) throws IOException, InvocationTargetException, SQLException, Exception {
        File deleteFile = new File(setFilePathReplaceAll(deleteFilePath));
        deleteFile.deleteOnExit();
    }

    /**
     * deleteFolderPath 경로의 폴더를 삭제하는 메소드 해당 폴더 삭제시 하위 폴더 및 파일을 모두 삭제 합니다.
     *
     * @param String deleteFolderPath 삭제할 폴더 경로
     * @throws IOException
     */
    public void deleteFolder(String deleteFolderPath) throws IOException, InvocationTargetException, SQLException, Exception {

        File deleteFolder = new File(setFilePathReplaceAll(deleteFolderPath));
        FileUtils.deleteDirectory(deleteFolder);

    }

    /**
     * deleteFolder 폴더를 삭제하는 메소드 해당 폴더 삭제시 하위 폴더 및 파일을 모두 삭제합니다.
     *
     * @param File deleteFolder 삭제할 폴더
     * @throws IOException
     */
    public void deleteFolder(File deleteFolder) throws IOException {
        FileUtils.deleteDirectory(deleteFolder);
    }

    /**
     * 전달 받은 fileName 의 파일 확장자 정보를 넘겨주는 메소드
     *
     * @param String fileName 파일명
     * @return String 파일 타입
     */
    public String fileTypeName(String fileName) {

        if (fileName != null && !"".equals(fileName)) {
            return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        } else {
            return "";
        }

    }

    public String setFilePathReplaceAll(String value) {

        String returnValue = value;

        if (returnValue == null || returnValue.trim()
                                              .equals("")) {
            return "";
        }

        returnValue = returnValue.replaceAll("[.][.]/", "");
        returnValue = returnValue.replaceAll("[.]/", "");
        returnValue = returnValue.replaceAll("[.]\\\\\\\\", "");
        returnValue = returnValue.replaceAll("[.][.]\\\\\\\\", "");
        returnValue = returnValue.replaceAll("\\\\\\\\", "");
        returnValue = returnValue.replaceAll("\\\\[.]\\\\[.]", ""); // ..
        returnValue = returnValue.replaceAll("&", "");

        return returnValue;

    }

}