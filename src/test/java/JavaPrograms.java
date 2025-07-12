import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JavaPrograms {
    public static final Path File_Path = Paths.get("MyDemofile.txt");

    public static void writeAndCreateFile(String content) throws IOException {
        Files.write(File_Path,content.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("File written Successfully");

    }
    public static void editExistingFile(String content) throws IOException {
        Files.write(File_Path,("\n" + content).getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        System.out.println("File written Successfully");

    }
    public static void readFile() throws IOException {
        List<String> lines = Files.readAllLines(File_Path);
        System.out.println("File content: ");
        lines.forEach(System.out::println);
    }
    public static void main(String[] args) throws IOException {

        writeAndCreateFile("Hello brother, harion, om namah shivaya");
        editExistingFile("Line 2: Om namah shivaya");
        readFile();
        JavaPrograms o = new JavaPrograms();
      //  o.stringReverse();
        //o.findFrequencyfromArray1();
        /*
         * Scanner sc = new Scanner(System.in); System.out.print("Enter any number:");
         * int d = sc.nextInt();
         *
         * o.palindrom(d); sc.close();
         **/
    }

    void palindrom(int d) {
        int number = d, rev = 0;
        int m = number;
        number = rev;

        // extract the last digit %10
        // append the number rev * 10 + digit
        // remove the number from the lisit number=number/10;

        while (number != 0) {
            int digit = number % 10;
            rev = rev * 10 + digit;
            number = number / 10;
        }
        if (rev == m) {
            System.out.println(" number is palindrom :" + m);
        } else {
            System.out.println(" number is not palindrom :" + m);
        }

    }

    // Find the frequency of program number and string

    void findFrequencyfromArray() {
        int a[] = {10, 50, 20, 10, 50};
        String s[] = {"Hello", "Hello", "Apple"};
        String cc = "BABY";

        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < s.length; i++) {

            if (map.containsKey(s[i])) {
                map.put(s[i], map.get(s[i]) + 1);
            } else {
                map.put(s[i], 1);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " count is : " + entry.getValue());
        }

    }

    // Find the frequency of program char

    void findFrequencyfromArray1() {

        String cc = "BABY";

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < cc.length(); i++) {

            char c = cc.charAt(i);

            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " count is : " + entry.getValue());
        }

    }

    // Reverse the String
    void stringReverse() {
        System.out.println("Enter any string : ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i) + " ");
        }

    }

}
