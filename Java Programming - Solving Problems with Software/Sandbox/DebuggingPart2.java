
/**
 * Write a description of DebuggingPart2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DebuggingPart2 {
    public void findAbc(String input){
       int index = input.indexOf("abc");
       int count = 0;
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           System.out.println("index before update: " + index);
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc",index+3);
           System.out.println("index after update: " + index);
           count++;
       }
       System.out.println("Count for " + input + ": " + count);
       System.out.println("-----------");
   }

   public void test(){
       //findAbc("abcd");
       //findAbc("abcdabc");
       findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       
       findAbc("kdabcabcjei");      // 2
       findAbc("ttabcesoeiabco");   // 2
       findAbc("abcbabccabcd");     // 3
       findAbc("qwertyabcuioabcp"); // 2
       findAbc("abcabcabcabca");    // 4
       
   }
}
