import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PMain {
    public static void main(String []args){
//        Scanner scanner= new Scanner(System.in);
//        System.out.println("enter first number");
//        int num1=scanner.nextInt();
//        System.out.println("second");
//        int num2=scanner.nextInt();
//        try { System.out.println("result"+num1+"and"+num2+ "is"+num1/num2);
//         List <Integer>numbers = List.of(1,2,3,4);
//         System.out.println(numbers.get(6));}
//        catch ( ArithmeticException e){
//            System.out.println("operation :dividing number"+e.getMessage()+"is impossible ,denomitator");
//        }
//        catch (ArrayIndexOutOfBoundsException ex){}
//        finally{
//            System.out.println("thank you");
//        }
//        try { BufferedReader reader = new BufferedReader(new FileReader("sample.txt"));}
//        catch (FileNotFoundException e){
//            System.out.println(e.getMessage());
//        }
        List<String > names = new ArrayList<>();
        names.add("beza");
//        for(int i =0;i<names.size();i++){
//            System.out.println(names.get(i));
//        }
        // to itereate over each name inside names

        for (String name:names ){
            System.out.println(name);
        }

    }
}
