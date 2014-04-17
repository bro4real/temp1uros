import java.util.concurrent.ExecutionException;
import com.brothers4real.newsthread.ReadByExecution;

public class TestExecutorThreading {

	public static void main(String[] args) 
			throws InterruptedException, ExecutionException {

		int numberOfThreads = 3;
		int threadPoolSize = 1;
		
		new ReadByExecution(numberOfThreads, threadPoolSize);
	}

}
