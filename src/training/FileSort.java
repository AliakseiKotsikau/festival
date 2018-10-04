package training;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class FileSort {

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();                  // чтение пути до папки в которой будет происходить сортировка

        ArrayList<File> fileCopy = createDirectories(path);
        moveFiles(fileCopy,path);

    }

    public static void moveFiles(ArrayList<File> list, String path) throws IOException {
        for (File file : list) {
            String typePath = path + "\\" + file.getName().replaceAll(".+\\.","") + "\\" + file.getName();
            System.out.println(typePath);
            file.renameTo(new File(typePath));     // перемещение файлов в папки с соответствующим название
        }
    }

    public static ArrayList<File> createDirectories(String path) {
        File files = new File(path);
        ArrayList<File> fileCopy = new ArrayList<>(Arrays.asList(files.listFiles()));
        HashSet<String> ends = new HashSet<>();
        for (File file : files.listFiles()) {
            ends.add(file.getName().replaceAll(".+\\.",""));   // добавление в Set типов файлов
        }

        for (String s : ends) {                          // создание папок, называющихся типами файлов
            String tempPath = path + "\\" + s;
            File newFolder = new File(tempPath);
            if(!newFolder.exists()) newFolder.mkdir();
        }
        return fileCopy;
    }



}
