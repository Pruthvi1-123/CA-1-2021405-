package javaca.task;

import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author name: Pruthvi Mulik
 * Student number:2021405
 * GitHub link: https://github.com/CCT-Dublin/ca1-Pruthvi1-123.git
 */
public class StandardDeviationThread implements Callable<Double> {
    private List<Integer> data;

    public StandardDeviationThread(List<Integer> data) {
        this.data = data;
    }

    @Override
    public Double call() {
        System.out.println("Thread :"+data);
        double mean = 0;
        for (int num : data) {
            mean += num;
        }
        mean /= data.size();


        double sumSquaredDiffs = 0;
        for (int num : data) {
            double diff = num - mean;
            sumSquaredDiffs += diff * diff;
        }

        double variance = sumSquaredDiffs / data.size();
        return Math.sqrt(variance);
    }
}