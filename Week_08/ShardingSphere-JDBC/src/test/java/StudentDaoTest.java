import me.bob.Main;
import me.bob.entity.Student;
import me.bob.repository.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest(classes = Main.class)
public class StudentDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void testSave() {
        for (int i = 0; i <= 100; i++) {
            Student student = new Student();
            Random random = new Random();
            int anInt = random.nextInt(20);
            student.setId(Long.valueOf(i));
            student.setName(anInt);
            studentDao.save(student);
        }
    }

}