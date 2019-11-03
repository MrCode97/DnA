import java.util.ArrayList;
import java.util.List;

public class Algo63v3 {
	public static void main(String[] args) {
		String input = "kuzdrabokrgostakdoshes";	// Valid string
		// String input = "kuztakdoshes";			// Invalid string

		List<Word> nouns = new ArrayList<>();
		boolean isValid = findWords(input, 0, nouns);
		System.out.println(nouns + "\n" + isValid);
		readSolution(nouns, input);
	}

	private static void readSolution(List<Word> nouns, String input) {
		int lastStart = input.length();
		for (int i = nouns.size() - 1; i >= 0; i--) {
			if (nouns.get(i)._end == lastStart - 1) {
				lastStart = nouns.get(i)._start;
				input = input.substring(0, lastStart) + " " + input.substring(lastStart);
			}
		}
		input = input.trim();
		System.out.println(input);

	}

	private static boolean expert(CharSequence charSequence) {
		String[] knownWords = { "bokr", "bokrgos", "doshes", "drabok", "gostak", "kuz", "kuzdra", "takdos" };
		for (String item : knownWords) {
			if (charSequence.equals(item)) {
				return true;
			}
		}
		return false;
	}

	private static boolean findWords(String input, int offset, List<Word> nouns) {
		List<Word> foundWords = new ArrayList<>();
		for (int wordLength = 1; wordLength <= input.length(); wordLength++) {
			String currentWord = (String) input.subSequence(0, wordLength);
			if (expert(currentWord)) {
				int wordEndIndex = offset + wordLength - 1;
				foundWords.add(new Word(offset, wordEndIndex, currentWord));
				if (wordEndIndex == input.length() - 1 + offset) {
					// Edge case, to not forget latest found nouns
					nouns.addAll(foundWords); 
					return true;
				}
			}
		}
		nouns.addAll(foundWords);

		for (Word word : foundWords) {
			// I assume that a single letter may also be a noun
			if (word._end - offset < input.length()) { 
				int newWordStartIndex = word._end + 1 - offset;
				String newinput = input.subSequence(newWordStartIndex, input.length()).toString();
				if (findWords(newinput, word._end + 1, nouns)) {
					return true;
				}
			}

		}
		return false;
	}

}

class Word {
	Word _vorgaenger;
	int _start;
	int _end;
	String _value;

	public Word(int start, int end, String value) {
		_start = start;
		_end = end;
		_value = value;
	}

	public String toString() {
		return String.format("%s: %s:%s", this._value, this._start, this._end);
	}
}
