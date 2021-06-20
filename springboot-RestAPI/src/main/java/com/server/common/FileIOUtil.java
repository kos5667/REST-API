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
     * ���� ��θ� ���� �޾Ƽ� ������ �����ϴ� �޼ҵ�
     *
     * @param String srcFilePath ������ ��� ���� ���
     * @param String destFilePath ����� ���� ���
     * @throws IOException
     */
    public void fileCopy(String srcFilePath, String destFilePath) throws IOException {

        File srcFile = new File(setFilePathReplaceAll(srcFilePath));
        File destFile = new File(setFilePathReplaceAll(destFilePath));

        fileCopy(srcFile, destFile);

    }

    /**
     * ������ ���� �޾Ƽ� ������ �����ϴ� �޼ҵ�
     *
     * @param File srcFile ������ ��� ����
     * @param File destFile ����� ����
     * @throws IOException
     */
    public void fileCopy(File srcFile, File destFile) throws IOException {
        FileUtils.copyFile(srcFile, destFile);
    }

    /**
     * destFilePath ��ο� �ش��ϴ� ���Ͽ� createContent������ ����ϴ� �޼ҵ� �Դϴ�. fileAppend �� �̾�� ����
     * ���θ� �����ϴ� ������ �̾�� �ϴ°�� true�� ���� ���� �ϰ��� �ϴ°�� false�� �Է� �ϸ� �˴ϴ�. fileEncoding ����
     * �Է� ���� �ʾƵ� �˴ϴ�.
     *
     * @param String destFilePath ���� ����� ���� ���
     * @param Stirng createContent ����� �� ����
     * @param        boolean fileAppend ���Ͽ� �� ��Ͻ� �̾���̱� ���� ����� ���� ����(ex>true
     *               �̾��,false �����)
     * @param String fileEncoding �۵�Ͻ� ���ڵ� ���� ����(ex> euc-kr, utf-8)
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
     * destFile ���Ͽ� createContent������ ����ϴ� �޼ҵ� �Դϴ�. fileAppend �� �̾�� ���� ���θ� �����ϴ� ������
     * �̾�� �ϴ°�� true�� ���� ���� �ϰ��� �ϴ°�� false�� �Է� �ϸ� �˴ϴ�. fileEncoding ���� �Է� ���� �ʾƵ�
     * �˴ϴ�.
     *
     * @param File   destFile ���� ����� ����
     * @param Stirng createContent ����� �� ����
     * @param        boolean fileAppend ���Ͽ� �� ��Ͻ� �̾���̱� ���� ����� ���� ����(ex>true
     *               �̾��,false �����)
     * @param String fileEncoding �۵�Ͻ� ���ڵ� ���� ����(ex> euc-kr, utf-8)
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
     * srcFilePath ��ο� �ش��ϴ� ������ ������ �о���� �޼ҵ� �Դϴ�. fileEncoding ���� �������� �ʾƵ� �˴ϴ�.
     *
     * @param String srcFilePath �о�� ���� ���
     * @param String fileEncoding ���� ���ڵ� ����(ex>euc-kr, utf-8)
     * @return String ������ ����
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
     * srcFile ������ ������ �о���� �޼ҵ� �Դϴ�. fileEncoding ���� �������� �ʾƵ� �˴ϴ�.
     *
     * @param File   srcFile �о�� ����
     * @param String fileEncoding ���� ���ڵ� ����(ex>euc-kr, utf-8)
     * @return String ������ ����
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
     * exists ���� �Ǵ� ���� ���� ���θ� Ȯ���ϴ� �޼ҵ�
     *
     * @param String filePath ���� �Ǵ� ���� ���
     * @return boolean ���� �Ǵ� ���� ���� ����
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
     * exists ���� �Ǵ� ���� ���� ���θ� Ȯ���ϴ� �޼ҵ�
     *
     * @param File file ���� ��ü
     * @return boolean ���� �Ǵ� ���� ���� ����
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
     * folderPath ��ο� �ش��ϴ� ������ �����ϴ� �޼ҵ�
     *
     * @param String folderPath ������ ���� ���
     * @param        boolean isRecursive ������ �����Ҷ� ���� ���丮�� ���� �������� ����(true:
     *               mkdirs/false: mkdir)
     * @return boolean �۾��� ���� ����
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

                sb.append("FileIOUtil createFolder : ���� �̸��� ������ ���� �մϴ�.")
                  .append("(")
                  .append(folderPath)
                  .append(")");

                logger.debug(sb.toString());
            }
        } else {
            isSuccess = false;

            StringBuilder sb = new StringBuilder();

            sb.append("FileIOUtil createFolder : ���� Ȥ�� ������ ���� �մϴ�.")
              .append("(")
              .append(folderPath)
              .append(")");

            logger.debug(sb.toString());
        }

        return isSuccess;

    }

    /**
     * newFolder ������ �����ϴ� �޼ҵ�
     *
     * @param File newFolder ������ ����
     * @param      boolean isRecursive ������ �����Ҷ� ���� ���丮�� ���� �������� ����(true:
     *             mkdirs/false: mkdir)
     * @return boolean �۾��� ���� ����
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
                logger.debug("FileIOUtil createFolder : ���� �̸��� ������ ���� �մϴ�.");
            }
        } else {
            isSuccess = false;
            logger.debug("FileIOUtil createFolder : ���� Ȥ�� ������ ���� �մϴ�.");
        }

        return isSuccess;

    }

    /**
     * filePath ��ο� �ش��ϴ� ������ �����ϴ� �޼ҵ�
     *
     * @param String filePath ������ ���� ���
     */
    public void createFile(String filePath) throws IOException, InvocationTargetException, SQLException, Exception {

        File newFile = new File(setFilePathReplaceAll(filePath));

        if (!newFile.exists()) {
            if (!newFile.isDirectory()) {
                newFile.createNewFile();
            } else {
                logger.debug("FileIOUtil createFile : ���� �̸��� ������ ���� �մϴ�.");
            }
        } else {
            logger.debug("FileIOUtil createFile : ���� Ȥ�� ������ ���� �մϴ�.");
        }

    }

    /**
     * newFile ������ �����ϴ� �޼ҵ�
     *
     * @param File newFile ������ ����
     */
    public void createFile(File newFile) throws IOException, InvocationTargetException, SQLException, Exception {

        if (!newFile.exists()) {
            if (!newFile.isDirectory()) {
                newFile.createNewFile();
            } else {
                logger.debug("FileIOUtil createFile : ���� �̸��� ������ ���� �մϴ�.");
            }
        } else {
            logger.debug("FileIOUtil createFile : ���� Ȥ�� ������ ���� �մϴ�.");
        }

    }

    /**
     * deleteFilePath ����� ������ �����ϴ� �޼ҵ�
     *
     * @param String deleteFilePath ������ ���� ���
     */
    public void deleteFile(String deleteFilePath) throws IOException, InvocationTargetException, SQLException, Exception {

        File deleteFile = new File(setFilePathReplaceAll(deleteFilePath));
        deleteFile.delete();

    }

    /**
     * deleteFilePath ������ �����ϴ� �޼ҵ�
     *
     * @param String deleteFile ������ ����
     */
    public void deleteFile(File deleteFile) {
        deleteFile.delete();
    }

    /**
     * deleteFilePath ����� ������ �����ϴ� �޼ҵ� �ٸ� ������ �Ǵ� ���μ����� ���� ������ Lock �Ǿ� ���� ��� JVM��
     * �����Ҷ� ������ ����
     *
     * @param String deleteFilePath ������ ���� ���
     */
    public void deleteOnExit(String deleteFilePath) throws IOException, InvocationTargetException, SQLException, Exception {
        File deleteFile = new File(setFilePathReplaceAll(deleteFilePath));
        deleteFile.deleteOnExit();
    }

    /**
     * deleteFolderPath ����� ������ �����ϴ� �޼ҵ� �ش� ���� ������ ���� ���� �� ������ ��� ���� �մϴ�.
     *
     * @param String deleteFolderPath ������ ���� ���
     * @throws IOException
     */
    public void deleteFolder(String deleteFolderPath) throws IOException, InvocationTargetException, SQLException, Exception {

        File deleteFolder = new File(setFilePathReplaceAll(deleteFolderPath));
        FileUtils.deleteDirectory(deleteFolder);

    }

    /**
     * deleteFolder ������ �����ϴ� �޼ҵ� �ش� ���� ������ ���� ���� �� ������ ��� �����մϴ�.
     *
     * @param File deleteFolder ������ ����
     * @throws IOException
     */
    public void deleteFolder(File deleteFolder) throws IOException {
        FileUtils.deleteDirectory(deleteFolder);
    }

    /**
     * ���� ���� fileName �� ���� Ȯ���� ������ �Ѱ��ִ� �޼ҵ�
     *
     * @param String fileName ���ϸ�
     * @return String ���� Ÿ��
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