package main.java.be.pxl.ja.exercise1;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.Comparator;

public class StudentMain {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        List<Student> students = StudentDao.createStudents();

        System.out.println("Studenten die vandag jarig zijn");
        students.stream()
            .filter(student -> student.getDateOfBirth().getMonth() == today.getMonth()
                    && student.getDateOfBirth().getDayOfWeek() == today.getDayOfWeek())
            .forEach(System.out::println);
        System.out.println();

        System.out.println("Gegevens van student Carol");
        students.stream()
            .filter(student -> "carol".equals(student.getName().toLowerCase()))
            .forEach(student -> {
                System.out.println("name: " + student.getName());
                System.out.println("graduation year: " + student.getGraduationYear());
                System.out.println("score: " + student.getScore());
                System.out.println("date of birth: " + student.getDateOfBirth());
            });
        System.out.println();

        System.out.println("Afgestudeerde studenten in 2017");
        students.stream()
            .filter(student -> student.getGraduationYear() == 2017)
            .forEach(System.out::println);
        System.out.println();

        System.out.println("Student met hoogste score ooit");
        students.stream()
            .sorted((x, y) -> Integer.compare(y.getScore(), x.getScore()))
            .limit(1)
            .forEach(student -> {
                System.out.println(student.getName() + ": " + student.getScore());
            });
        System.out.println();

        System.out.println("Alle namen van student gescheiden door een komma");
        List<String> studentNames = students.stream()
            .map(Student::getName)
            .collect(Collectors.toList());
        System.out.println(String.join(", ", studentNames));
        System.out.println();

        System.out.println("De jongste student die is afgestudeerd in 2018");
        students.stream()
            .filter(student -> student.getGraduationYear() == 2018)
            .sorted((x, y) -> y.getDateOfBirth().compareTo(x.getDateOfBirth()))
            .limit(1)
            .forEach(System.out::println);
        System.out.println();

        System.out.println("Gesorteerd op afstudeerjaar en vervolgens op score");
        Comparator<Student> studentComparator = Comparator.comparing(Student::getGraduationYear)
            .thenComparing(Student::getScore);

        students.stream()
            .sorted(studentComparator.reversed())
            .forEach(student -> {
                System.out.println(student.getGraduationYear() + " - " + student.getName() + ": " + student.getScore());
            });
        System.out.println();
    }
}
