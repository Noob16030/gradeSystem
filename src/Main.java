import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Vytvorim scannery, ktore pouzijem na cely program a privitam uzivatela
        System.out.println("Hello, insert the number of students.");
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        int studentsCount = 0; // Odkontrolujem vstup, nastavim na nulu pre while cyklus
        while(studentsCount == 0){
            try {
                studentsCount = scannerInt.nextInt();
            } catch (Exception e) {
                System.out.println("Must be number. Try again.");
                scannerInt.next();
            }
        }
        // Vytvorim list s triedou Student (to je v podstate record, ktory poznam z pascalu)
        ArrayList<Student> myStudents = new ArrayList<Student>();
        for (int i = 0; i < studentsCount; i++){
            System.out.println("Insert the name of " + (i + 1) + ". student.");
            String nameOfStudent = ""; 
            while (nameOfStudent.isEmpty()){ // Odkontrolujem vtup pre meno, nesmie byt prazdne
                try {
                    nameOfStudent = scannerStr.nextLine();
                } catch (Exception e) {
                    System.out.println("Wrong input.");
                    scannerStr.next();
                }
                if (nameOfStudent.isEmpty()){
                    System.out.println("Name cant be blank. Insert name of " + (i+1) + ". student");
                }
            }
            // Odkontrolujem pocet predmetov
            System.out.println("Insert the number of subjects.");
            int subjectsCount = 0;
            while(subjectsCount == 0){
                try {
                    subjectsCount = scannerInt.nextInt();
                } catch (Exception e) {
                    System.out.println("Must be number. Try again.");
                    scannerInt.next();
                }
            }
            // Odkontrolujem znamky, musia byt od 1 po 5
            int[] arrayOfGrades = new int[subjectsCount];
            for (int j = 0; j < arrayOfGrades.length; j++){
                System.out.println("Insert the grade of " + (j + 1) + ". subject.");
                arrayOfGrades[j] = 0;
                while(arrayOfGrades[j] == 0 || arrayOfGrades[j] > 5){
                    try {
                        arrayOfGrades[j] = scannerInt.nextInt();
                    } catch (Exception e) {
                        System.out.println("Must be number (1-5). Try again.");
                        scannerInt.next();
                    }
                    if (arrayOfGrades[j] == 0 || arrayOfGrades[j] > 5){
                        System.out.println("Must be number (1-5). Try again.");
                    }
                }
            }
            // Vkladam na konci cyklu objekt student do listu, docasne hodnoty som mal v premennych
            Student student = new Student();
            student.nameOfStudent = nameOfStudent;
            student.numberOfSubjects = subjectsCount;
            student.gradesForSubjects = arrayOfGrades;
            myStudents.add(student);
        }
        // Vypocitam priemerne znamky a vypisem najvyssiu znamku a jej drzitela
        double averageGrade = 0;
        String helpName = "";
        for (Student std: myStudents) {
            System.out.println("Student " + std.nameOfStudent + " have average grade: " + String.format("%.2f",returnAverageGrade(std.gradesForSubjects)));
            if (returnAverageGrade(std.gradesForSubjects) > averageGrade){
                averageGrade = returnAverageGrade(std.gradesForSubjects);
                helpName = std.nameOfStudent;
            }
        }
        System.out.println("The highest average grade have student " +  helpName + " with " + String.format("%.2f", averageGrade) + " grade.");

        // Cyklus pre zobrazenie znamok konkretneho ziaka, ak chce uzivatel skoncit, na vstupe zada n, pri troch prazdnych vstupoch program skonci
        int maxBlankInputs = 0;
        while (maxBlankInputs < 3) {
            System.out.println("Do you wanna see grades of subject for some student?(name for continue/n for exit)");
            helpName = scannerStr.nextLine().toLowerCase();
            if (helpName.toLowerCase().equals("n")){
                break;
            } else if (helpName.isEmpty()) {
                maxBlankInputs++;
            } else {
                int hlpInt = 0;
                for (Student std: myStudents) {
                    if (helpName.equals(std.nameOfStudent.toLowerCase())){
                        for (int i = 0; i < std.numberOfSubjects; i++){
                            System.out.println((i+1)+". subject grade: "+std.gradesForSubjects[i]);
                        }
                    } else {
                        hlpInt++;
                    }
                    // Ak nenajde meno, vrati sa na zaciatok cyklu
                    if (hlpInt == myStudents.size()){
                        System.out.println("Student with name " + helpName + " not found.");
                    }

                }
            }
        }
    }

    // Pomocna funkcia pre vypocet priemernej znamky
    public static Double returnAverageGrade (int[] arrayOfGrades){
        int all = 0;
        String rtrn;
        for (int grade: arrayOfGrades){
            all += grade;
        }
        rtrn = String.format("%.2f", (Double.valueOf(all) / Double.valueOf(arrayOfGrades.length)));
        return (Double.valueOf(all) / Double.valueOf(arrayOfGrades.length));
    }

}

/**
 * TASK - Grade Management System
 *
 * Create a program that:
 * 1. Asks the user how many students they want to enter grades for
 * 2. For each student, asks for:
 *    - Name
 *    - Number of subjects (grades)
 *    - Grades for each subject
 * 3. Stores this information in appropriate arrays
 * 4. Provides the following functionality:
 *    - Calculate and display average grade for each student
 *    - Find and display the student with the highest average
 *    - Display all grades for any student (by entering their name)
 *
 * Requirements:
 * - Use arrays to store the data
 * - Handle invalid inputs
 * - Format the output to be easily readable
 *
 */