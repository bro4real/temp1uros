import java.util.concurrent.ExecutionException;
import com.brothers4real.newsthread.ReadByDirectThreading;

public class TestDirectThreading {

	public static void main(String[] args) 
			throws InterruptedException, ExecutionException{
	
		int numberOfThreads = 3;
		
		new ReadByDirectThreading(numberOfThreads);
		
	}

}
