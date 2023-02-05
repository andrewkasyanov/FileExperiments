import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\kasya\\Desktop\\11\\";
        String in = path + "test";
        String out = path + "archive.zip";

        try {
            FileOutputStream outputStream = new FileOutputStream(out);
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            writeFileToZip(new File(in),zipOut,"");

            zipOut.flush();
            zipOut.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    public static void writeFileToZip(File file, ZipOutputStream zipOut,
                                      String path) throws Exception{
        if(file.isDirectory()) {
            String folder = path + file.getName() + "/";
            ZipEntry entry = new ZipEntry(folder);
            zipOut.putNextEntry(entry);
            zipOut.closeEntry();
            File[] files = file.listFiles();
            for(File subFile : files) {
                writeFileToZip(subFile, zipOut, path);
            }
            return;
        }
        ZipEntry entry = new ZipEntry(path + file.getName());
        zipOut.putNextEntry(entry);
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        zipOut.write(bytes);
    }
}