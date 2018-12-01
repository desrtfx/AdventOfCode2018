package day01;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import util.FileIO;

public class Day01 {

	private static final String FILENAME = "./resources/Input_Day01.txt";

	private static final List<Integer> data;
	
	static {
		List<String> rawData = FileIO.getFileAsList(FILENAME);
		data = rawData.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}
	
	public static int part1(List<Integer> data) {
		return data.stream().reduce(0, Integer::sum);
	}
	
	public static int part2(List<Integer> data) {
		Set<Integer> unique = new HashSet<>();
		int freq = 0;
		while(true) {
			for(int item : data) {
				freq += item;
				if(!unique.add(freq)) {
					return freq;
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		System.out.printf("Result of Part 1 with input data: %d%n", part1(data));
		System.out.printf("Result of Part 2 with input data: %d%n", part2(data));
		
	}
}
