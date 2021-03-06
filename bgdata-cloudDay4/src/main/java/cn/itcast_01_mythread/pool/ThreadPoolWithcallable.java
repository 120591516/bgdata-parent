package cn.itcast_01_mythread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * callable 跟runnable的区别：
 * runnable的run方法不会有任何返回结果，所以主线程无法获得任务线程的返回值
 * 
 * callable的call方法可以返回结果，但是主线程在获取时是被阻塞，需要等待任务线程返回才能拿到结果
 * @author
 *
 */
public class ThreadPoolWithcallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(20); 
		List<Future<String>> list  = new ArrayList<Future<String>>();
		for(int i = 0; i < 10; i++){
			Future<String> submit = pool.submit(new Callable<String>(){
				public String call() {
					//System.out.println("a");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return "b--"+Thread.currentThread().getName();
				}			   
			   });
			list.add(submit);
			//从Future中get结果，这个方法是会被阻塞的，一直要等到线程任务返回结果
//			System.out.println(submit.get());
		} 
		for (Future<String> future : list) {
			System.out.println(future.get());
		}
			pool.shutdown();

	}

}
