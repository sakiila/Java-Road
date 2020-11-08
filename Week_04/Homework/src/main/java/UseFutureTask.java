import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class UseFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 异步执行 下面方法
        FutureTask<Integer> task = new FutureTask<>(() -> {
            return sum();//这是得到的返回值
        });
        executorService.submit(task);

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + task.get());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        executorService.shutdown();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
