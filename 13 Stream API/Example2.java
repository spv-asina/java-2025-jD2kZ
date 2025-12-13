import java.util.*;
import java.util.stream.Collectors;

class RealComparison {
    static class Student {
        String name;
        double grade;
        String group;

        Student(String name, double grade, String group) {
            this.name = name;
            this.grade = grade;
            this.group = group;
        }
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Анна", 4.5, "A"),
                new Student("Борис", 3.8, "B"),
                new Student("Сергей", 4.2, "A"),
                new Student("Дарья", 5.0, "B"),
                new Student("Евгений", 3.5, "A"),
                new Student("Ольга", 4.8, "B"),
                new Student("Иван", 4.0, "A")
        );

        // ============ СЛОЖНАЯ ЗАДАЧА ============
        System.out.println("ЗАДАЧА: Получить топ-2 студента по оценке из каждой группы, \n" +
                "        преобразовать имена в верхний регистр, \n" +
                "        отсортировать по имени");

        System.out.println("\n=== Решение С Stream API ===");
        Map<String, List<String>> result = students.stream()
                .sorted((s1, s2) -> Double.compare(s2.grade, s1.grade)) // сортировка по убыванию оценки
                .collect(Collectors.groupingBy(
                        s -> s.group,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .limit(2)                           // топ-2 из группы
                                        .map(s -> s.name.toUpperCase())     // в верхний регистр
                                        .sorted()                           // сортировка по имени
                                        .collect(Collectors.toList())
                        )
                ));

        result.forEach((group, names) ->
                System.out.println("Группа " + group + ": " + names));

        System.out.println("\n=== Решение БЕЗ Stream API ===");
        // Сначала группируем вручную
        Map<String, List<Student>> grouped = new HashMap<>();
        for (Student s : students) {
            grouped.computeIfAbsent(s.group, k -> new ArrayList<>()).add(s);
        }

        // Затем для каждой группы...
        Map<String, List<String>> result2 = new HashMap<>();
        for (Map.Entry<String, List<Student>> entry : grouped.entrySet()) {
            // Сортируем по оценке (убывание)
            List<Student> groupStudents = entry.getValue();
            groupStudents.sort((s1, s2) -> Double.compare(s2.grade, s1.grade));

            // Берем топ-2
            List<String> topNames = new ArrayList<>();
            for (int i = 0; i < Math.min(2, groupStudents.size()); i++) {
                topNames.add(groupStudents.get(i).name.toUpperCase());
            }

            // Сортируем имена
            Collections.sort(topNames);
            result2.put(entry.getKey(), topNames);
        }

        result2.forEach((group, names) ->
                System.out.println("Группа " + group + ": " + names));
    }
}