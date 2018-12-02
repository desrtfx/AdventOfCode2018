package day02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.FileIO;

public class Day02 {

	private static final String FILENAME = "./resources/Input_Day02.txt";

	private static final List<String> data;
	
	static {
		data = FileIO.getFileAsList(FILENAME);
	}
	
	public static int part1(List<String> data) {
		int doubleChars = 0;
		int tripleChars = 0;
		for(String item : data) {
			Map<Character,Integer> histo = new HashMap<>();
			for(char c : item.toCharArray()) {
				histo.put(c, histo.getOrDefault(c, 0) + 1);
			}
			for(Integer value : histo.values()) {
				if(value == 2) {
					doubleChars++;
					break;
				}
			}
			for(Integer value : histo.values()) {
				if(value == 3) {
					tripleChars++;
					break;
				}
			}
		}
		
		
		return doubleChars * tripleChars;
	}
	
	public static String part2(List<String> data) {
		for(int i = 0; i<data.size() - 1; i++) {
			for(int j = i + 1; j < data.size(); j++) {
				String first = data.get(i);
				String second = data.get(j);
				StringBuilder same = new StringBuilder();
				for(int x = 0; x < first.length(); x++) {
					if(first.charAt(x) == second.charAt(x)) {
						same.append(first.charAt(x));
					}
				}
				if (same.length() == first.length()-1) {
					return same.toString();
				}
			}
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		// part 1
		List<String> testData = Arrays.asList("abcdef","bababc","abbcde","abcccd","aabcdd","abcdee","ababab");
		System.out.printf("Result of Part 1 with test data: %d | expected result: 12%n", part1(testData));
		System.out.printf("Result of Part 1 with input data: %d%n", part1(data));
		// Part 2
		List<String> testPart2 = Arrays.asList("abcde","fghij","klmno","pqrst","fguij","axcye","wvxyz");
		System.out.printf("Result of Part 2 with test data: %s | expected result: fgij%n", part2(testPart2));
		System.out.printf("Result of Part 2 with input data: %s%n", part2(data));
		
	}
	
}
