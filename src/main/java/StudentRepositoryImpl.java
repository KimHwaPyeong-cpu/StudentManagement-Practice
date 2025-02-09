import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

	// 메모리 저장
	private final List<Student> students = new ArrayList<>();
	private int nextId = 1;

    @Override
    public List<Student> findAll() {
        return students;
    }

    // ID로 학생 조회
    @Override
    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        // 못 찾으면 null 반환 (실무에서는 Optional을 쓰거나 예외를 던지기도 함)
        return null;
    }

    @Override
    public void save(Student student) {
    	student = new Student(nextId++, student.getName(), student.getMajor());
    	students.add(student);
    }

    // 학생 정보 업데이트
    @Override
    public void update(int id, Student updatedStudent) {
        // 리스트를 순회하면서 해당 ID를 가진 학생이 있으면 업데이트
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getId() == id) {
                // 새로운 값으로 갱신
                Student newStudent = new Student(id, updatedStudent.getName(), updatedStudent.getMajor());
                students.set(i, newStudent);
                return; // 업데이트 후 메서드 종료
            }
        }
    }

    // 학생 삭제
    @Override
    public void delete(int id) {
        // 간단하게 removeIf 사용
        students.removeIf(student -> student.getId() == id);
    }
}
