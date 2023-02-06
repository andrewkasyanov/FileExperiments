import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\kasya\\Desktop\\11\\";
        String in = path + "folderNew.zip";
        String out = path + "\\result\\";

        try {
//            //Creating archive
//            FileOutputStream outputStream = new FileOutputStream(out);
//            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
//            writeFileToZip(new File(in),zipOut,"");
//            zipOut.flush();
//            zipOut.close();
//            outputStream.close();
            //Extracting archive
            FileInputStream inputStream = new FileInputStream(in);
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            while (true) {
                ZipEntry entry = zipInputStream.getNextEntry();
                if (entry == null) {
                    break;
                }

                File file = new File(out + entry.getName());
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    byte[] bytes = zipInputStream.readAllBytes();
                    Files.write(
                            Paths.get(file.getAbsolutePath()),
                            bytes,
                            StandardOpenOption.CREATE
                    );

//TODO: сложный вариант
//                    FileOutputStream outputStream = new FileOutputStream(file);
//                    byte[] buffer = new byte[1024];
//                    int len;
//                    while((len = zipInputStream.read(buffer)) > 0) {
//                        outputStream.write(buffer, 0, len);
//                    }
//                    outputStream.close();
//                    outputStream.flush();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void writeFileToZip(File file, ZipOutputStream zipOut,
                                      String path) throws Exception {
        if (file.isDirectory()) {
            String folder = path + file.getName() + "/";
            ZipEntry entry = new ZipEntry(folder);
            zipOut.putNextEntry(entry);
            zipOut.closeEntry();
            File[] files = file.listFiles();
            for (File subFile : files) {
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