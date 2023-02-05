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
        String[] in = {"22.txt", "11.txt", "output.txt"};
        String out = path + "archive.zip";

        try {
            FileOutputStream outputStream = new FileOutputStream(out);
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            for (String fileName : in) {
                File file = new File(path + fileName);
                ZipEntry entry = new ZipEntry(file.getName());
                zipOut.putNextEntry(entry);
                Path filePath = Paths.get(file.getAbsolutePath());
                byte[] data = Files.readAllBytes(filePath);
                zipOut.write(data);
            }
            zipOut.flush();
            zipOut.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}