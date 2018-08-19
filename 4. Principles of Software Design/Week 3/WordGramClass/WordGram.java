/**
 * Java Programming: Principles of Software Design
 * 
 * Word N-Grams Programming Exercise: WordGram Class
 * 
 * Assignment I: Complete WordGram
 * 
 * @author Daniel J Hardej
 * @version v1.0
 * 
 * 
 **/

public class WordGram {
    
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int k=0; k<myWords.length; k++) {
            ret += myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) {
            return false;
        }
        for (int k=0; k<myWords.length; k++) {
            if (! myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        String[] shiftedWords = new String[out.length()];
        // TODO: Complete this method
        for (int k=0; k<myWords.length-1; k++) {
            shiftedWords[k] = out.wordAt(k+1);
        }
        shiftedWords[shiftedWords.length-1] = word;
        return new WordGram(shiftedWords, 0, shiftedWords.length);
    }
    
    public int hashCode () {
        return this.toString().hashCode();
    }
}