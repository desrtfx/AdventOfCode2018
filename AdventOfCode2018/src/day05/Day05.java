package day05;

import java.util.HashSet;
import java.util.Set;

import util.FileIO;

public class Day05 {
	
	public static final int DIFF_UPPER_LOWER_CASE = 32;
	
	private static final String FILENAME = "./resources/Input_Day05.txt";

	private static String data;
	
	static {
		data = FileIO.getFileAsString(FILENAME);
	}
	
	public static int react(String data) {
		StringBuilder current = new StringBuilder(data); 
		String last;
		do {
			last = current.toString();
			int i = 0;
			while ((i < current.length()-1)) {
				if(Math.abs(current.charAt(i) - current.charAt(i + 1)) == DIFF_UPPER_LOWER_CASE) {
					current.deleteCharAt(i);
					current.deleteCharAt(i);
				} else {
					i++;
				}
			}
		} while ((current.length()!=0) && (!last.equals(current.toString())));
		return current.length();
	}
	
	public static int part1(String data) {
		return react(data);
	}
	
	
	public static int part2(String data) {
		Set<String> usedChars = new HashSet<>();
		for(int i = 0; i < data.length(); i++) {
			usedChars.add(data.substring(i,i+1).toLowerCase());
		}
		int minLength = Integer.MAX_VALUE;
		// evaluate
		for(String s : usedChars) {
			String test = new String(data); // copy original data
			test = test.replaceAll(s, "");
			test = test.replaceAll(s.toUpperCase(), "");
			int length = react(test);
			if (length < minLength) {
				minLength = length;
			}
		}
		return minLength;
	}
	
	public static void main(String[] args) {
		// Test data
		String testData = "dabAcCaCBAcCcaDA";
		System.out.printf("Result of Part 1 with test data: %d - expected 10%n", part1(testData));

		// part 1
		System.out.printf("Result of Part 1 with input data: %d%n", part1(data));
		
		// Test part 2
		System.out.printf("Result of Part 1 with test data: %d - expected 4%n", part2(testData));
		// Part 2
		System.out.printf("Result of Part 2 with input data: %d%n", part2(data));
		
	}
	
	
}
