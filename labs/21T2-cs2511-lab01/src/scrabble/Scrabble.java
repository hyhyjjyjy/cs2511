package scrabble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scrabble {

    private List<String> dictionary = new ArrayList<String>(Arrays.asList("ab", "abe", "able", "ad", "ae", "ae",
            "ah", "al", "ale", "at", "ate", "ba", "bad", "be", "be", "bead", "bed", "bra", "brad", "bread", "bred",
            "cabble", "cable", "ea", "ea", "eat", "eater", "ed", "ha", "hah", "hat", "hate", "hater", "hath", "he",
            "heat", "heater", "heath", "heather", "heathery", "het", "in", "io", "ion", "li", "lin", "lion", "on",
            "program", "ra", "rad", "re", "rea", "read", "red", "sa", "sat", "scabble", "scrabble", "se", "sea", "seat",
            "seathe", "set", "seth", "sh", "sha", "shat", "she", "shea", "sheat", "sheath", "sheathe", "sheather",
            "sheathery", "sheth", "st", "te"));
    private String word;

    public Scrabble(String word) {
        this.word = word;
    }

    public int score() {
        List<String> appeared = new ArrayList<String>();
        return this.scoreHelpFunc(appeared);
    }

    public int scoreHelpFunc(List<String> appeared) {
        if (this.word.length() <= 1)
            return 0;
        if (this.word.length() == 2)
            if (this.dictionary.contains(this.word) && !appeared.contains(this.word)) {
                appeared.add(this.word);
                return 1;
            } 
            else 
                return 0;
        if (!this.dictionary.contains(this.word))
            return 0;
        
        int sum = 1;
        appeared.add(this.word);

        for (int i = 0; i < this.word.length(); i++) {
            String subWord = this.word.substring(0, i) + this.word.substring(i + 1, this.word.length());
            if (this.dictionary.contains(subWord) && !appeared.contains(subWord)){
                Scrabble newString = new Scrabble(subWord);
                sum += newString.scoreHelpFunc(appeared);
            }
        }
        return sum;

    }
}