import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\kasya\\Desktop\\11\\22.txt";
//        File file = new File(path);

        try {
            String data = Files.readString(Paths.get(path));
            System.out.println(data);

            Files.writeString(
                    Paths.get(path),
                    "\ntest testtest!!!",
                    StandardOpenOption.APPEND);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}