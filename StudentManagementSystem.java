import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.InputMismatchException;

public class StudentManagementSystem {

    // Polymorphism: List of Person can hold Student objects (or any other subclass
    // of Person)
    static List<Person> studentList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n====== Student Management System ======");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        System.out.println("Exiting... Thank you!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                sc.nextLine(); // Clear buffer to prevent infinite loop
            } catch (InvalidStudentDataException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // 1️⃣ Add Student
    static void addStudent() throws InvalidStudentDataException {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            // Check duplicate ID
            for (Person p : studentList) {
                if (p instanceof Student) {
                    if (((Student) p).getId() == id) {
                        throw new InvalidStudentDataException("Student ID " + id + " already exists.");
                    }
                }
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            // Exception Handling Logic
            if (age < 0 || age > 120) {
                throw new InvalidStudentDataException("Age must be between 0 and 120.");
            }

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            // Encapsulation & Inheritance: Creating Student object
            studentList.add(new Student(id, name, age, course));
            System.out.println("Student added successfully!");

        } catch (InputMismatchException e) {
            sc.nextLine(); // Clear buffer
            throw new InvalidStudentDataException("ID and Age must be numeric.");
        }
    }

    // 2️⃣ View Students
    static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        // Polymorphism: Treating Student objects as Person
        for (Person p : studentList) {
            p.displayInfo(); // Dynamic binding calls Student's displayInfo()
        }
    }

    // 3️⃣ Update Student
    static void updateStudent() {
        try {
            System.out.print("Enter Student ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            for (Person p : studentList) {
                // Check if Person is a Student
                if (p instanceof Student) {
                    Student s = (Student) p;
                    if (s.getId() == id) {
                        System.out.print("Enter new Name: ");
                        String newName = sc.nextLine();
                        if (!newName.isEmpty())
                            s.setName(newName); // Encapsulation: using setter

                        System.out.print("Enter new Age: ");
                        String ageInput = sc.nextLine();
                        if (!ageInput.isEmpty()) {
                            try {
                                int newAge = Integer.parseInt(ageInput);
                                if (newAge < 0) {
                                    System.out.println("Invalid age. Update skipped.");
                                } else {
                                    s.setAge(newAge);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid age format. Update skipped.");
                            }
                        }

                        System.out.print("Enter new Course: ");
                        String newCourse = sc.nextLine();
                        if (!newCourse.isEmpty())
                            s.setCourse(newCourse);

                        System.out.println("Student updated successfully!");
                        return;
                    }
                }
            }
            System.out.println("Student not found.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input format.");
            sc.nextLine();
        }
    }

    // 4️⃣ Delete Student
    static void deleteStudent() {
        try {
            System.out.print("Enter Student ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            Person toRemove = null;
            for (Person p : studentList) {
                if (p instanceof Student) {
                    if (((Student) p).getId() == id) {
                        toRemove = p;
                        break;
                    }
                }
            }

            if (toRemove != null) {
                studentList.remove(toRemove);
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: ID must be a number.");
            sc.nextLine();
        }
    }
}
