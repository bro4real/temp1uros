import java.util.ArrayList;
import java.util.List;
import com.brothers4real.lesson20.Student;

public class TestOnlineEducation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Student> students = new ArrayList<>();
		
		students.add(new Student("Cristina"));
		students.add(new Student("Oleg"));
		students.add(new Student("Uros"));
		
		for(Student s : students){
			s.requestHomework();
			System.out.println(s.name + ": I hope I can finish homework "
						+ "for lesson no." + s.lessonNumber + " on time.");
		}
	}
}
