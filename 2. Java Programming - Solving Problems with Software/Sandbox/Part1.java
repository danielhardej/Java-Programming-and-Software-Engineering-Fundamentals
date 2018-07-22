
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1) {
            break;
        }
        if (index > input.length() - 3) {
            break;
        }
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+4);
    }
}
   public void test() {
    //no code yet
    //findAbc("abcd");
    //findAbc("abcdabc");
    //findAbc("eusabce");
    //findAbc("woiehabchi");
    //findAbc("abcbbbabcdddabc");
    //findAbc("aaaaabc");
    findAbc("yabcyabc");
    System.out.println("-------");
}
}
