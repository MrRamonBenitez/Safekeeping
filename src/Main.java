import java.io.*;
import java.util.zip.*;

public class Main {
    public static void main(String[] args) {

        GameProgress progress1 = new GameProgress(100, 5, 1, 57.43);
        saveGame("D:/games/savegames/save1.dat", progress1);
        GameProgress progress2 = new GameProgress(77, 3, 3, 100.67);
        saveGame("D:/games/savegames/save2.dat", progress2);
        GameProgress progress3 = new GameProgress(83, 7, 5, 432.77);
        saveGame("D:/games/savegames/save3.dat", progress3);

        File dir = new File("D:/games/savegames");
        File[] filesList = new File[3];
        if (dir.isDirectory()) {
            filesList = dir.listFiles();
        }

        System.out.println(filesList.toString());

        zipFiles("D:/games/savegames/save.zip", filesList);

    }

    private static void saveGame(String path, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, File[] list) {
        for (File item : list) {
            try (ZipOutputStream zout = new ZipOutputStream(new
                    FileOutputStream(path));
                 FileInputStream fis = new FileInputStream(item.getPath())) {
                ZipEntry entry = new ZipEntry(item.getPath());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
