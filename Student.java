public class Student extends Person {
    private int id;
    private String course;

    public Student(int id, String name, int age, String course) {
        super(name, age);
        this.id = id;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Polymorphism: Overriding the abstract method
    @Override
    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Course: " + course);
        System.out.println("-------------------");
    }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + getName() + ", Age=" + getAge() + ", Course=" + course + "]";
    }
}
