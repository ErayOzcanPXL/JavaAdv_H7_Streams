package main.java.be.pxl.ja.exercise1;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Comparator;
import java.util.Map;

// BELANGRIJK
// **********
// file:///usr/share/doc/java-openjdk/api/java.base/java/util/stream/Collectors.html
// **********

public class StudentMain {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        List<Student> students = StudentDao.createStudents();

        // 1
        System.out.println("Studenten die vandag jarig zijn");
        students.stream()
            .filter(student -> student.getDateOfBirth().getMonth() == today.getMonth()
                    && student.getDateOfBirth().getDayOfWeek() == today.getDayOfWeek())
            .forEach(System.out::println);
        System.out.println();

        // 2
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

        // 3
        System.out.println("Afgestudeerde studenten in 2017");
        students.stream()
            .filter(student -> student.getGraduationYear() == 2017)
            .forEach(System.out::println);
        System.out.println();

        // 4
        System.out.println("Student met hoogste score ooit");
        students.stream()
            .sorted((x, y) -> Integer.compare(y.getScore(), x.getScore())) // kon ook compareTo in Student.java toevoegen
            //.findFirst() // terminal operation
            .limit(1)
            .forEach(student -> {
                System.out.println(student.getName() + ": " + student.getScore());
            });
        //System.out.println("student opslaan in Optional<Student> en print die uit met .ifPresent().get()");
        System.out.println();

        // 5
        Optional<Student> oldestStudent = students.stream()
            .sorted(Comparator.comparing(Student::getDateOfBirth))
            .findFirst();
            //.min(Comparator.comparing(Student::getDateOfBirth)) // dit is de beste manier, bovenste twee moeten dan weg, enkel de .min moet blijven
        System.out.println("Oudste student: " + oldestStudent.get());
        System.out.println();

        // 6
        System.out.println("Alle namen van student gescheiden door een komma");
        List<String> studentNames = students.stream()
            .map(Student::getName)
            .collect(Collectors.toList());
        System.out.println(String.join(", ", studentNames));
        System.out.println();

        // betere oplossing dan 6
        String joinedStudents = students.stream()
            .map(Student::getName)
            .collect(Collectors.joining(", "));
        System.out.println(joinedStudents);
        System.out.println();

        // 7
        System.out.println("De jongste student die is afgestudeerd in 2018");
        students.stream()
            //.max(Comparator.comparing(Student::getDateOfBirth))
            .filter(student -> student.getGraduationYear() == 2018)
            .sorted((x, y) -> y.getDateOfBirth().compareTo(x.getDateOfBirth()))
            .limit(1)
            .forEach(System.out::println);
        System.out.println();

        // 8
        Map<Integer, Double> averageScore = students.stream()
            .collect(Collectors.groupingBy(
                        Student::getGraduationYear,
                        Collectors.averagingInt(Student::getScore)));
        System.out.println(averageScore);
        System.out.println();

        // 9
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
