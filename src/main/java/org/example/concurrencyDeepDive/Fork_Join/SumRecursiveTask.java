package org.example.concurrencyDeepDive.Fork_Join;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

public class SumRecursiveTask extends RecursiveTask<Integer> {

    private static final Logger logger
            = Logger.getLogger(SumRecursiveTask.class.getName());

    private static final int THRESHOLD = 10;
    private final List<Integer> workList;

    public SumRecursiveTask(List<Integer> workList) {
        this.workList = workList;
    }

    @Override
    protected Integer compute() {

        if (workList.size()<=THRESHOLD){
            return partialSum(workList);
        }

        return ForkJoinTask.invokeAll(createSubtasks())
                .stream().mapToInt(ForkJoinTask::join).sum();

    }

    private List<SumRecursiveTask> createSubtasks() {
        List<SumRecursiveTask> subTask = new ArrayList<>();
        int size = workList.size();
        logger.info("workList size "+size);
        List<Integer> workListLeft = workList.subList(0, (size / 2));
        List<Integer> workListRight = workList.subList((size / 2), size);
        subTask.add(new SumRecursiveTask(workListLeft));
        subTask.add(new SumRecursiveTask(workListRight));
        return subTask;
    }

    private Integer partialSum(List<Integer> workList) {

        int sum = workList.stream().mapToInt(e -> e).sum();

        logger.info(() -> "Partial sum: " + workList + " = "
                + sum + "\tThread: " + Thread.currentThread().getName());
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i <200; i++) {
            list.add(1 + rnd.nextInt(10));
        }

        SumRecursiveTask sumRecursiveTask = new SumRecursiveTask(list);
        Integer sumAll = forkJoinPool.invoke(sumRecursiveTask);
        logger.info(() -> "Final sum: " + sumAll);
    }
}
