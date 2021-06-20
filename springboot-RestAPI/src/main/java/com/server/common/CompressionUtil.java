package com.server.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Stack;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompressionUtil {

    private static final Logger logger = LoggerFactory.getLogger(CompressionUtil.class);

    /**
     * ���������� �����ϴ� ���丮�� ���� ����
     *
     * @param zippedFile
     * @throws IOException
     */
    public void unzip(File zippedFile) throws IOException {
        unzip(zippedFile, Charset.defaultCharset().name());
    }

    public void unzip(File zippedFile, String encoding ) throws IOException {
        unzip(zippedFile, zippedFile.getParentFile(), encoding);
    }

    public void unzip(File zippedFile, File destDir) throws IOException {
        unzip(new FileInputStream(zippedFile), destDir, Charset.defaultCharset().name());
    }

    public void unzip(File zippedFile, File destDir, String encoding)
            throws IOException {
        unzip(new FileInputStream(zippedFile), destDir, encoding);
    }

    public void unzip(InputStream is, File destDir) throws IOException{
        unzip(is, destDir, Charset.defaultCharset().name());
    }

    public void unzip(InputStream is, File destDir, String encoding) throws IOException {

        ZipArchiveInputStream zis ;
        ZipArchiveEntry entry ;
        String name ;
        File target ;
        int nWritten = 0;
        BufferedOutputStream bos ;
        final byte [] buf = new byte[1024 * 8];

        ensureDestDir(destDir);

        zis = new ZipArchiveInputStream(is, encoding, false);
        while ( (entry = zis.getNextZipEntry()) != null ){
            name = entry.getName();
            target = new File (destDir, name);
            if ( entry.isDirectory() ){
                ensureDestDir(target);
            } else {
                target.createNewFile();
                bos = new BufferedOutputStream(new FileOutputStream(target));
                while ((nWritten = zis.read(buf)) >= 0 ){
                    bos.write(buf, 0, nWritten);
                }
                bos.close();
                logger.debug("CompressionUtil unzip file : " + name);
            }
        }
        zis.close();
    }

    private void ensureDestDir(File dir) throws IOException {

        if ( ! dir.exists() ) {
            dir.mkdirs(); /*  does it always work? */
            logger.debug("CompressionUtil ensureDestDir dir : " + dir);
        }

    }

    /**
     * compresses the given file(or dir) and creates new file under the same directory.
     * @param src file or directory
     * @throws IOException
     */
    public void zip(File src) throws IOException{
        zip(src, Charset.defaultCharset().name(), true);
    }
    /**
     * zips the given file(or dir) and create
     * @param src file or directory to compress
     * @param includeSrc if true and src is directory, then src is not included in the compression. if false, src is included.
     * @throws IOException
     */
    public void zip(File src, boolean includeSrc) throws IOException{
        zip(src, Charset.defaultCharset().name(), includeSrc);
    }
    /**
     * compresses the given src file (or directory) with the given encoding
     * @param src
     * @param charSetName
     * @param includeSrc
     * @throws IOException
     */
    public void zip(File src, String charSetName, boolean includeSrc) throws IOException {
        zip(src, src.getParentFile(), charSetName, includeSrc);
    }
    /**
     * compresses the given src file(or directory) and writes to the given output stream.
     * @param src
     * @param os
     * @throws IOException
     */
    public void zip(File src, OutputStream os) throws IOException {
        zip(src, os, Charset.defaultCharset().name(), true);
    }
    /**
     * compresses the given src file(or directory) and create the compressed file under the given destDir.
     * @param src
     * @param destDir
     * @param charSetName
     * @param includeSrc
     * @throws IOException
     */
    public void zip(File src, File destDir, String charSetName, boolean includeSrc) throws IOException {

        String fileName = src.getName();
        if ( !src.isDirectory() ){
            final int pos = fileName.lastIndexOf(".");
            if ( pos >  0){
                fileName = fileName.substring(0, pos);
            }
        }
        fileName += ".zip";
        ensureDestDir(destDir);

        final File zippedFile = new File ( destDir, fileName);
        if ( !zippedFile.exists() ) {
            zippedFile.createNewFile();
        }
        zip(src, new FileOutputStream(zippedFile), charSetName, includeSrc);
    }

    public void zip(File [] filesToZip, OutputStream os, String encoding ) {

    }

    public void zip(File src, OutputStream os, String charsetName, boolean includeSrc) throws IOException {

        final ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
        zos.setEncoding(charsetName);
        zos.setUseZip64(Zip64Mode.Always);
        FileInputStream fis ;

        int length ;
        ZipArchiveEntry ze ;
        final byte [] buf = new byte[8 * 1024];
        String name ;

        final Stack<File> stack = new Stack<>();
        File root ;
        if ( src.isDirectory() ) {
            if( includeSrc ){
                stack.push(src);
                root = src.getParentFile();
            }
            else {
                final File [] fs = src.listFiles();
                final int fsLength = fs.length;
                for (int i = 0; i < fsLength; i++) {
                    stack.push(fs[i]);
                }
                root = src;
            }
        } else {
            stack.push(src);
            root = src.getParentFile();
        }

        while ( !stack.isEmpty() ){

            final File f = stack.pop();
            name = toPath(root, f);

            if ( f.isDirectory()){
                logger.debug("CompressionUtil zip dir : " + name);
                final File [] fs = f.listFiles();
                final int fsLength = fs.length;
                for (int i = 0; i < fsLength; i++) {
                    if ( fs[i].isDirectory() ) {
                        stack.push(fs[i]);
                    } else {
                        stack.add(0, fs[i]);
                    }
                }
            } else {
                logger.debug("CompressionUtil zip file : " + name);
                ze = new ZipArchiveEntry(name);
                zos.putArchiveEntry(ze);
                fis = new FileInputStream(f);
                while ( (length = fis.read(buf, 0, buf.length)) >= 0 ){
                    zos.write(buf, 0, length);
                }
                fis.close();
                zos.closeArchiveEntry();
            }

        }
        zos.close();
    }

    // ����Ʈ�� ������ �޾� zip�� ����
    public void zip(List<File> src, OutputStream os) throws IOException {

        final ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
        zos.setEncoding(Charset.defaultCharset().name());
        zos.setUseZip64(Zip64Mode.Always);
        FileInputStream fis = null;

        int length;
        ZipArchiveEntry ze;
        final byte[] buf = new byte[8 * 1024];

        final int srcSize = src.size();

        if (srcSize > 0) {

            for (int i = 0; i < srcSize; i++) {
                logger.debug("CompressionUtil zip name : " + src.get(i).getName());
                ze = new ZipArchiveEntry(src.get(i).getName());
                zos.putArchiveEntry(ze);
                fis = new FileInputStream(src.get(i));
                while ((length = fis.read(buf, 0, buf.length)) >= 0) {
                    zos.write(buf, 0, length);
                }
            }

            fis.close();
            zos.closeArchiveEntry();
        }

        zos.close();
    }

    private String toPath(File root, File dir){
        String path = dir.getAbsolutePath();
        path = path.substring(root.getAbsolutePath().length()).replace(File.separatorChar, '/');
        if ( path.startsWith("/")) {
            path = path.substring(1);
        }
        if ( dir.isDirectory() && !path.endsWith("/")) {
            path += "/" ;
        }
        return path ;
    }

}