public class StudentGradeAnalyzer {

    private static double assignmentScore = 50;
    private static double midtermScore = -70;
    private static double finalScore = 80;

    public static void main(String[] args) {
        System.out.println("Assignment Score: " + assignmentScore);
        System.out.println("Midterm Score: " + midtermScore);
        System.out.println("Final Exam Score: " + finalScore);


        if(!isValidMarks(assignmentScore, midtermScore, finalScore)){
            System.out.println("Please enter valid marks between 0 and 100");
            return;
        }

        double average = averageMarks(assignmentScore, midtermScore, finalScore);
        char category = gradeCategory(average);
        System.out.println("Average Score: " + average);
        System.out.println("Category: " + category);
    }

    public static double averageMarks(double score1, double score2, double score3) {
        double sum = score1 + score2 + score3;
        return sum / 3;
    }

    public static char gradeCategory(double score) {
        if (score >= 90 && score <= 100) {
            return 'A';
        } else if (score >= 80 && score <= 89) {
            return 'B';
        } else if (score >= 70 && score <= 79) {
            return 'C';
        } else if (score >= 60 && score <= 69) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static boolean isValidMarks(double Marks1, double Marks2, double Marks3) {
        if(Marks1 < 0 || Marks1 > 100){
            System.out.println("The Assignment score " + Marks1 + " is invalid (0-100 only)");
            return false;
        }
        if(Marks2 < 0 || Marks2 > 100){
            System.out.println("The Midterm score " + Marks2 + " is invalid (0-100 only)");
            return false;
        }
        if(Marks3 < 0 || Marks3 > 100){
            System.out.println("The Final score " + Marks3 + " is invalid (0-100 only)");
            return false;
        }
        return true;
    }
}