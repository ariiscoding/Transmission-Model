package he.ari;

import he.ari.concurrency.MultithreadedExecutor;
import he.ari.templates.Templates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ThreadManager threadManager = new ThreadManager(Templates.defaultModel(), 50);
//        threadManager.run();
//        threadManager.summarize();
        ExecutorService service = Executors.newFixedThreadPool(2);

//        for (int i = 0; i < 100; i++) {
//            service.execute(() -> {
//                System.out.println(1);
//            });
//        }

//        List<Future> futures = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            Future future = service.submit(new Runnable() {
//                public void run() {
//                    System.out.println("Asynchronous task");
//                }
//            });
//            futures.add(future);
//        }
//
//
//        System.out.println("Main thread");
//        service.shutdown();
//
//        try {
//            Thread.sleep(50);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        for (Future future : futures) {
//            System.out.println(future);
//        }

//        List<Main.Task> toRun = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            toRun.add(new Task());
//        }
//
//        List<Future<String>> future = service.invokeAll(toRun, 4, TimeUnit.SECONDS);
//
//        System.out.println("Assignment finished.");
//
//
//
//        for (Future<String> f : future) {
//            if (!f.isCancelled()) System.out.println("Success: " + f.get());
//            else System.out.println("Failed");
//        }
//
//        service.shutdown();

        MultithreadedExecutor multithreadedExecutor = new MultithreadedExecutor(Templates.defaultModel(),300);
        System.out.println(multithreadedExecutor.getThreadPoolSize());
        multithreadedExecutor.setThreadPoolSize(500);
        System.out.println(multithreadedExecutor.getThreadPoolSize());
        multithreadedExecutor.setThreadPoolSize(0);
        System.out.println(multithreadedExecutor.getThreadPoolSize());
        multithreadedExecutor.setThreadPoolSize(3000);
        System.out.println(multithreadedExecutor.getThreadPoolSize());
        multithreadedExecutor.setTimeOutSeconds(10);
        int failed = multithreadedExecutor.execute();
        System.out.println(failed);
        multithreadedExecutor.summarize();
    }

//    static class Task implements Callable<String> {
////        public Object call() {
////            System.out.println("Thread name: " + Thread.currentThread().getName());
////            return null;
////        }
//
//        @Override
//        public String call() {
//            System.out.println("Side thread started running.");
//            return new String("Hello");
//        }
//    }
}
