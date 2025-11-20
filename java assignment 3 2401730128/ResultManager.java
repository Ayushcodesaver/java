import java.util.InputMismatchException;
import java.util.Scanner;

// Custom exception to show invalid marks
class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

class Student {

    private int rollNumber;
    private String studentName;
    private int[] marks = new int[3];

    public Student(int rollNumber, String studentName, int[] marks) throws InvalidMarksException {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        validateMarks(marks);
        this.marks = marks;
    }

    private void validateMarks(int[] marks) throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException("Invalid marks " + marks[i]);
            }
        }
    }

    public double calculateAverage() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total / 3.0;
    }

    public void displayResult() {
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Student Name: " + studentName);
        System.out.print("Marks: ");
        for (int m : marks) System.out.print(m + " ");
        System.out.println("\nAverage: " + calculateAverage());
    }

    public int getRollNumber() {
        return rollNumber;
    }
}

public class ResultManager {

    private Student[] students = new Student[100];
    private int studentCount = 0;
    private Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = scanner.nextInt();
            }

            students[studentCount++] = new Student(roll, name, marks);
            System.out.println("Student added!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public void showStudentDetails() {
        System.out.print("Enter Roll Number: ");
        int roll = scanner.nextInt();

        for (int i = 0; i < studentCount; i++) {
            if (students[i].getRollNumber() == roll) {
                students[i].displayResult();
                return;
            }
        }

        System.out.println("No student found.");
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Show Student Details");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int ch = scanner.nextInt();
            switch (ch) {
                case 1: addStudent(); break;
                case 2: showStudentDetails(); break;
                case 3: return;
                default: System.out.println("Invalid!");
            }
        }
    }

    public static void main(String[] args) {
        new ResultManager().mainMenu();
    }
}
