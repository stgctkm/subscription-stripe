package ccsr.subscription.infrastructure.datasource.student;

import ccsr.subscription.application.repository.student.StudentRepository;
import ccsr.subscription.domain.student.Student;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentDataSource implements StudentRepository {

    Map<String, String> store = new HashMap<>();

    @Override
    public void register(Student student) {
        store.put(student.email().toString(), student.customerId().toString());
    }
}
