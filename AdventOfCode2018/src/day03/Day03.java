package day03;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.FileIO;

public class Day03 {
	
	static class Rect {
		int ID;
		Rectangle r;
		
		public Rect(int ID, int x, int y, int width, int height) {
			this.ID = ID;
			r = new Rectangle(x, y, width, height);
		}
		
		public boolean intersects(Rect other) {
			return r.intersects(other.r);
		}
		
		public Rectangle intersection(Rect other) {
			return r.intersection(other.r);
		}
		
		
	}
	
	
	
	
	private static final String FILENAME = "./resources/Input_Day03.txt";

	private static final List<Rect> data;
	
	private static final String parsePattern = "#(?<ID>\\d*)\\s@\\s(?<StartX>\\d*),(?<StartY>\\d*):\\s(?<SizeX>\\d*)x(?<SizeY>\\d*)";

	private static int[][] fabric = new int[1001][1001];

	
	static {
		List<String> rawData = FileIO.getFileAsList(FILENAME);
		data = new ArrayList<>();
		Pattern pattern = Pattern.compile(parsePattern, Pattern.MULTILINE);
		for(String item : rawData) {
			Matcher matcher = pattern.matcher(item);
			while (matcher.find()) {
				int ID = Integer.parseInt(matcher.group("ID"));
				int startX = Integer.parseInt(matcher.group("StartX"));
				int startY = Integer.parseInt(matcher.group("StartY"));
				int width = Integer.parseInt(matcher.group("SizeX"));
				int height = Integer.parseInt(matcher.group("SizeY"));
				data.add(new Rect(ID, startX, startY, width, height));
			}
		}
	}

	public static int part1(List<Rect> data) {
		for(Rect r : data) {
			for(int y = r.r.y; y < r.r.y + r.r.height; y++) {
				for(int x = r.r.x; x < r.r.x + r.r.width; x++) {
					fabric[y][x] = (fabric[y][x] == 0)?r.ID:-1;
				}
			}
		}
		int count = 0;
		for(int y = 0; y < fabric.length; y++) {
			for(int x = 0; x < fabric[y].length; x++) {
				if(fabric[y][x] == -1) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static int part2(List<Rect> data) {
		for(Rect r : data) {
			boolean intact = true;
			for(int y = r.r.y; y < r.r.y + r.r.height; y++) {
				if (!intact) {
					break;
				}
				for(int x = r.r.x; x < r.r.x + r.r.width; x++) {
					if(fabric[y][x] != r.ID) {
						intact = false;
						break;
					}
				}
			}
			if (intact) {
				return r.ID;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		// part 1
		System.out.printf("Result of Part 1 with input data: %d%n", part1(data));
		// Part 2
		System.out.printf("Result of Part 2 with input data: %d%n", part2(data));
		
	}
}
