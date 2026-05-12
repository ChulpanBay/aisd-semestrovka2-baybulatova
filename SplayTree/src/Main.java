import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static SplayTree<Integer> splayTree;
    public static List<String> list = new ArrayList<>();
    public static List<Long> listTimeOfInsert = new ArrayList<>();
    public static List<Long> listTimeOfDelete = new ArrayList<>();
    public static List<Long> listTimeOfSearch = new ArrayList<>();

    // Lists for iteration counts
    public static List<Integer> listIterationsOfInsert = new ArrayList<>();
    public static List<Integer> listIterationsOfSearch = new ArrayList<>();
    public static List<Integer> listIterationsOfDelete = new ArrayList<>();

    public static Integer AverageTimeInsert;
    public static Integer AverageTimeSearch;
    public static Integer AverageTimeDelete;
    public static Integer AverageIterationsInsert;
    public static Integer AverageIterationsSearch;
    public static Integer AverageIterationsDelete;

    public static void main(String[] args) throws IOException {
        readFromFile();
        insertAllElements();
        searchHundredElements();
        deleteThousandElements();

        searchAverageTimeInsert();
        searchAverageIterationsInsert();
        searchAverageTimeSearch();
        searchAverageIterationsSearch();
        searchAverageTimeDelete();
        searchAverageIterationsDelete();

        System.out.println("Среднее время добавления элемента: " + AverageTimeInsert + " наносекунд");
        System.out.println("Среднее время поиска элемента: " + AverageTimeSearch + " наносекунд");
        System.out.println("Среднее время удаления элемента: " + AverageTimeDelete + " наносекунд");

        System.out.println("Среднее количество итераций при добавлении элемента: " + AverageIterationsInsert);
        System.out.println("Среднее количество итераций при поиске элемента: " + AverageIterationsSearch);
        System.out.println("Среднее количество итераций при удалении элемента: " + AverageIterationsDelete);
    }

    public static int[] createArray(){
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++){
            array[i] = (int) (Math.random() * 10000);
        }
        return array;
    }

    public static void writeToFile() throws IOException {
        int[] dataArray = createArray();
        FileWriter writer = new FileWriter("example.txt",false);
        for (int i = 0; i < dataArray.length; i++){
            writer.write(String.valueOf(dataArray[i]));
            writer.write("\n");
        }
        writer.close();
    }

    public static void readFromFile() throws IOException {
        Path path = Path.of("example.txt");
        list = Files.readAllLines(path);
    }

    public static void insertAllElements(){
        splayTree = new SplayTree<>();
        for (String s : list) {
            long startTime = System.nanoTime();
            splayTree.insert(Integer.parseInt(s));
            long endTime = System.nanoTime();
            listTimeOfInsert.add(endTime - startTime);
            listIterationsOfInsert.add(splayTree.getInsertIterations());
        }
    }

    public static void deleteThousandElements(){
        Random random = new Random();
        for (int i = 0; i < 1000; i++){
            int randomIndex = random.nextInt(list.size());
            long startTime = System.nanoTime();
            splayTree.delete(Integer.parseInt(list.get(randomIndex)));
            long endTime = System.nanoTime();
            listTimeOfDelete.add(endTime - startTime);
            listIterationsOfDelete.add(splayTree.getDeleteIterations());
        }
    }

    public static void searchHundredElements(){
        Random random = new Random();
        for (int i = 0; i < 100; i++){
            int randomValue = random.nextInt(10000);
            long startTime = System.nanoTime();
            splayTree.search(Integer.parseInt(list.get(randomValue)));
            long endTime = System.nanoTime();
            listTimeOfSearch.add(endTime - startTime);
            listIterationsOfSearch.add(splayTree.getSearchIterations());
        }
    }

    public static void searchAverageTimeInsert(){
        long localSum = 0;
        for (Long aLong : listTimeOfInsert) {
            localSum += aLong;
        }
        AverageTimeInsert = (int)(localSum / listTimeOfInsert.size());
    }

    public static void searchAverageIterationsInsert(){
        long localSum = 0;
        for (Integer iterations : listIterationsOfInsert) {
            localSum += iterations;
        }
        AverageIterationsInsert = (int)(localSum / listIterationsOfInsert.size());
    }

    public static void searchAverageTimeSearch(){
        long localSum = 0;
        for (Long aLong : listTimeOfSearch) {
            localSum += aLong;
        }
        AverageTimeSearch = (int)(localSum / listTimeOfSearch.size());
    }

    public static void searchAverageIterationsSearch(){
        long localSum = 0;
        for (Integer iterations : listIterationsOfSearch) {
            localSum += iterations;
        }
        AverageIterationsSearch = (int)(localSum / listIterationsOfSearch.size());
    }

    public static void searchAverageTimeDelete(){
        long localSum = 0;
        for (Long aLong : listTimeOfDelete) {
            localSum += aLong;
        }
        AverageTimeDelete = (int)(localSum / listTimeOfDelete.size());
    }

    public static void searchAverageIterationsDelete(){
        long localSum = 0;
        for (Integer iterations : listIterationsOfDelete) {
            localSum += iterations;
        }
        AverageIterationsDelete = (int)(localSum / listIterationsOfDelete.size());
    }
}
