package ccsr.subscription.application.service.student;

import ccsr.subscription.application.repository.student.StudentRepository;
import ccsr.subscription.domain.customer.Customer;
import ccsr.subscription.domain.student.Student;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    StudentRepository studentRepository;

    public void register(Student student) {
        studentRepository.register(student);
    }

    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
