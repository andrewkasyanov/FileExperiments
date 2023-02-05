import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\kasya\\Desktop\\11\\";
        String in = path + "22.txt";
        String out = path + "archive.zip";

        try {
            FileOutputStream outputStream = new FileOutputStream(out);
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            ZipEntry entry = new ZipEntry(new File(in).getName());
            zipOut.putNextEntry(entry);
            byte[] data = Files.readAllBytes(Paths.get(in));
            zipOut.write(data);
            zipOut.flush();
            zipOut.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}