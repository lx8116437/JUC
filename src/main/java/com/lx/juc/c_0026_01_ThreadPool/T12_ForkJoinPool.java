package com.lx.juc.c_0026_01_ThreadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T12_ForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_VALUE = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println("---" + Arrays.stream(nums).sum());
    }

    static class AddTask extends RecursiveAction {
        int start;
        int end;

        public AddTask(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_VALUE) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from:" + start + " to:" + end + " = " + sum);
            } else {
                int mid = start + (end - start) / 2;
                AddTask subTask1 = new AddTask(start, mid);
                AddTask subTask2 = new AddTask(mid, end);
                subTask1.fork();
                subTask2.fork();
            }

        }
    }

    static class AddTaskRet extends RecursiveTask<Long> {
        private static final long serialVersionUID = 1L;
        int start;
        int end;

        AddTaskRet(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_VALUE) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }
            int mid = start + (end - start) / 2;
            AddTaskRet t1 = new AddTaskRet(start, mid);
            AddTaskRet t2 = new AddTaskRet(mid, end);
            t1.fork();
            t2.fork();
            return t1.join() + t2.join();
        }
    }

    public static void main(String[] args) throws IOException {
        /*ForkJoinPool fjp = new ForkJoinPool();
        AddTask task = new AddTask(0, nums.length);
        fjp.execute(task);
        System.in.read();*/

        ForkJoinPool fjp = new ForkJoinPool();
        AddTaskRet taskRet = new AddTaskRet(0, nums.length);
        fjp.execute(taskRet);
        long result = taskRet.join();
        System.out.println(result);
    }


}
