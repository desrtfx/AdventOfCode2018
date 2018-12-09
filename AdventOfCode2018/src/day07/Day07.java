package day07;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.FileIO;

public class Day07 {
	private static final String FILENAME = "./resources/Input_Day06.txt";

	private static Map<Integer,Point> data = new HashMap<>();

	static {
		List<String[]> rawData = FileIO.getFileLinesSplit(FILENAME, ", ");
		}
		
	
	
	
	public static int part1(Map<Integer,Point> data) {
		return 0;
	}
	
	public static int part2(Map<Integer,Point> data) {
		return 0;
	}
	
	public static void main(String[] args) {
		// part 1
		System.out.printf("Result of Part 1 with input data: %d%n", part1(data));
		
		// part 2
		System.out.printf("Result of Part 2 with input data: %d%n", part2(data));
		
	}
	

}
