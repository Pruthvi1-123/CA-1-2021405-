
package javaca.util;

import javaca.task.MatrixMultiplicationThread;
import javaca.task.StandardDeviationThread;
import javaca.task.MergeSortThread;
import java.util.List;
import java.util.Scanner;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 *
 * @author name: Pruthvi Mulik
 * Student number:2021405
 * GitHub link: https://github.com/CCT-Dublin/ca1-Pruthvi1-123.git
 */

public class CAUtil {
    List<List<Integer>> inputData;
    private String [] menuOptions={"Load data","Perform standard deviation","Perform matrix multiplication","Perform merge sort","Exit"};
    
    public int printMenu()
    {
        Scanner scanner=new Scanner(System.in);
        int i=0;
        System.out.println("------Concurrency Menu-----");
        for (String option:menuOptions)
            System.out.println(++i+"."+option);
        
        System.out.print("Please select an option:");
        int op=scanner.nextInt();
        return op>0&&op<=menuOptions.length?op:printMenu();
        
    }
    
    public void loadData()
    {
        String filePath="data.csv";
        try{
         List<String> data=Files.readAllLines(Path.of(filePath));
         inputData=new ArrayList<>();
           data.stream().forEach(line->
           {
           inputData.add(Arrays.stream(line.split(",")).map(d->Integer.valueOf(d)).collect(Collectors.toList()));
           });
            System.out.println("Data Loaded!");
        }catch(IOException exception)
        {
            System.err.println("Error : Data file cannot be loaded.\nPlease check if the path is correct and file is present on the path.");
        }
    }
    
    public void performStandaredDeviation() throws Exception
    {
        if(inputData==null)
        {
            System.out.println("Data not loaded, Please load data first.");
            return;
        }
        long start=System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(inputData.size());

        List<Future<Double>> futures = new ArrayList<>();

        for (List<Integer> sublist : inputData) {
            Callable<Double> task = new StandardDeviationThread(sublist);
            Future<Double> future = executor.submit(task);
            futures.add(future);
        }
        double sum = 0;
        for (Future<Double> future : futures) {
            try {
                sum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error : "+e.getMessage());
            }
        }
        double averageStandardDeviation = sum / inputData.size();
        System.out.println("Standard Deviation: " + averageStandardDeviation);
        executor.shutdown();
        System.out.println("Total Time taken : "+(System.currentTimeMillis()-start)+"ms");
          
    }
    public void performMatrixMultiplication() throws Exception
    {
        if(inputData==null)
        {
            System.out.println("Data not loaded, Please load data first.");
            return;
        }
        long start=System.currentTimeMillis();
        int [][] matrixA=inputData.subList(0, inputData.get(0).size()).stream()
                .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
        int [][] matrixB=inputData.subList(inputData.get(0).size(), inputData.size()).stream()
                .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
         int[][] result = new int[matrixA.length][matrixB[0].length];

        int numThreads = inputData.size();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                Runnable task = new MatrixMultiplicationThread(matrixA, matrixB, result, i, j);
                executor.execute(task);
            }
        }
        System.out.println("Result Matrix:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        executor.shutdown();
        System.out.println("Total Time taken : "+(System.currentTimeMillis()-start)+"ms");
          
    }
    
    
      public void performMergeSort() throws Exception
    {
        if(inputData==null)
        {
            System.out.println("Data not loaded, Please load data first.");
            return;
        }
        long start=System.currentTimeMillis();
    

        ExecutorService executor = Executors.newFixedThreadPool(inputData.size());
        List<Future<List<Integer>>> futures = new ArrayList<>();

        for (List<Integer> sublist : inputData) {
            MergeSortThread task = new MergeSortThread(sublist);
            Future<List<Integer>> future = executor.submit(task);
            futures.add(future);
        }

       List<List<Integer>> sortedData = new ArrayList<>();
        for (Future<List<Integer>> future : futures) {
            try {
                sortedData.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                 System.err.println("Error : "+e.getMessage());
            }
        }
        sortedData.forEach(System.out::println);
        executor.shutdown();
        System.out.println("Total Time taken : "+(System.currentTimeMillis()-start)+"ms");
          
    }
    
}
