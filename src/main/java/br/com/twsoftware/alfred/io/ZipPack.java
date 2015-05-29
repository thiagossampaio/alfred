
package br.com.twsoftware.alfred.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import br.com.twsoftware.alfred.object.Objeto;

public class ZipPack{

     private List<File> files = new ArrayList<File>();

     private String fileName;

     private String directory;

     private ZipPack(File... files){

          this.files = Arrays.asList(files);
     }

     private ZipPack(String directory){

          this.directory = directory;
     }

     private ZipPack(String... paths){

          this.files = new ArrayList<File>();

          for (String path : paths) {

               File f = new File(path);
               if (f.exists()) {
                    this.files.add(f);
               } else {
                    System.err.println("Arquivo não encontrado para compactar: " + f.getAbsolutePath());
               }

          }
     }

     public static ZipPack files(String... paths) {

          return new ZipPack(paths);
     }

     public static ZipPack files(File... files) {

          return new ZipPack(files);
     }

     public ZipPack to(String fileName) {

          this.fileName = fileName;
          return this;
     }

     public static ZipPack fromDirectory(String directory) {

          return new ZipPack(directory);
     }

     public byte[] pack() throws Exception {

          if (Objeto.isBlank(files)) {
               throw new RuntimeException("Nenhum arquivo foi passado para ser compactado.");
          }

          String outputFile = "neus_music_pac";
          if (Objeto.notBlank(fileName)) {
               outputFile = fileName;
          }

          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          ZipOutputStream zos = new ZipOutputStream(baos);
          byte bytes[] = new byte[2048];

          for (File file : files) {

               FileInputStream fis = new FileInputStream(file);
               BufferedInputStream bis = new BufferedInputStream(fis);

               zos.putNextEntry(new ZipEntry(file.getName()));

               int bytesRead;
               while ((bytesRead = bis.read(bytes)) != -1) {
                    zos.write(bytes, 0, bytesRead);
               }

               zos.closeEntry();
               bis.close();
               fis.close();

          }

          zos.flush();
          baos.flush();
          zos.close();
          baos.close();

          return baos.toByteArray();

     }

     public void packAndSave() throws Exception {

          try {

               byte[] result = pack();
               FileOutputStream out = null;
               File f = new File(fileName);

               out = new FileOutputStream(f);
               out.write(result);
               out.flush();
               out.close();

          } catch (Exception ex) {
               throw new RuntimeException("Erro ao gravar o arquivo! ", ex);
          }

     }

     /**
      * Packs the given directory.
      * 
      * @param directoryPath
      * - the directory that is going to be packed
      * @throws IOException
      */
     public void packDirectory() throws IOException {

          if (Objeto.isBlank(directory)) {
               throw new RuntimeException("Informe o diretório que deseja compactar.");
          }

          // The output zip file name
          String outputFile = directory + ".zip";

          // Open streams to write the ZIP contents to
          FileOutputStream fos = new FileOutputStream(outputFile);
          ZipOutputStream zos = new ZipOutputStream(fos);

          // iterate directory structure recursively and add zip entries
          packCurrentDirectoryContents(directory, zos);

          // Close the streams
          zos.closeEntry();
          zos.close();
          fos.close();
     }

     /**
      * Recursively pack directory contents.
      * 
      * @param directoryPath
      * - current directory path that is visited recursively
      * @param zos
      * - ZIP output stream reference to add elements to
      * @throws IOException
      */
     private void packCurrentDirectoryContents(String directoryPath, ZipOutputStream zos) throws IOException {

          // Iterate through the directory elements
          for (String dirElement : new File(directoryPath).list()) {

               // Construct each element full path
               String dirElementPath = directoryPath + "/" + dirElement;

               // For directories - go down the directory tree recursively
               if (new File(dirElementPath).isDirectory()) {

                    packCurrentDirectoryContents(dirElementPath, zos);

               } else {
                    // For files add the a ZIP entry
                    // THIS IS IMPORTANT: a ZIP entry needs to be a relative path to the file
                    // so we cut off the path to the directory that is being packed.
                    ZipEntry ze = new ZipEntry(dirElementPath.replaceAll(directory + "/", ""));
                    zos.putNextEntry(ze);

                    // Open input stream to packed file
                    FileInputStream fis = new FileInputStream(dirElementPath);

                    // An array to which will hold byte being read from the packed file
                    byte[] bytesRead = new byte[512];

                    // Read bytes from packed file and store them in the ZIP output stream
                    int bytesNum;
                    while ((bytesNum = fis.read(bytesRead)) > 0) {
                         zos.write(bytesRead, 0, bytesNum);
                    }

                    // Close the stream
                    fis.close();
               }
          }

     }

}