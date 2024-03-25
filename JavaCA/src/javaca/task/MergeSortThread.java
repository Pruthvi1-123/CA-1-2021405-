package javaca.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author name: Pruthvi Mulik
 * Student number:2021405
 * GitHub link: https://github.com/CCT-Dublin/ca1-Pruthvi1-123.git
 */

public class MergeSortThread implements Callable<List<Integer>> {
    private List<Integer> list;

    public MergeSortThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public List<Integer> call() {
        return mergeSort(list);
    }

    private List<Integer> mergeSort(List<Integer> list) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<Integer> left = list.subList(0, mid);
        List<Integer> right = list.subList(mid, list.size());

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }
}