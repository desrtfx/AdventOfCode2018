package day04;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import util.FileIO;

public class Day04 {

	
	private static final String FILENAME = "./resources/Input_Day04.txt";
	private static List<String> data;
	private static Map<Integer, int[]> dutyCycle = new HashMap<>();
	
	static {
		data = FileIO.getFileAsList(FILENAME);
		Collections.sort(data);
	}
	
	public static int part1(List<String> data) {
		int guardID = 0; // Guard on duty
		int minuteStart = 0;
		int minuteEnd = 0;
		// Parse data
		for(String item : data) {
			if(item.contains("Guard")) { // New Guard on duty
				int start = item.indexOf("#")+1;
				int end = item.substring(start).indexOf(" ");
				guardID = Integer.parseInt(item.substring(start, start+end));
			} else {
				if(item.contains("falls")) { // falls asleep
					int end = item.indexOf("]");
					minuteStart = Integer.parseInt(item.substring(end-2,end));
				} else {
					if(item.contains("wakes")) { // wakes up
						int end = item.indexOf("]");
						minuteEnd = Integer.parseInt(item.substring(end-2,end));
						// fill data
						int[] timeFrame = dutyCycle.getOrDefault(guardID, new int[60]);
						for(int i = minuteStart; i < minuteEnd; i++) {
							timeFrame[i]++;
						}
						dutyCycle.put(guardID, timeFrame);
					}
				}
			}
		}
		
		// analyze data
//		Map<Integer, Integer> guardSleep = new HashMap<>();
		int maxSleepTime = Integer.MIN_VALUE;
		int maxGuardID = 0;
		for(Entry<Integer, int[]> item : dutyCycle.entrySet()) {
			guardID = item.getKey();
			int[] times = item.getValue();
			int sleepTime = 0;
			for(int x : times) {
				sleepTime += x;
			}
			if (sleepTime > maxSleepTime) {
				maxSleepTime = sleepTime;
				maxGuardID = guardID;
			}
//			guardSleep.put(guardID, sleepTime);
		}
		int[] times = dutyCycle.get(maxGuardID);
		int maxTime = Integer.MIN_VALUE;
		int maxIndex = Integer.MIN_VALUE;
		for(int i = 0; i< times.length; i++) {
			if(times[i] > maxTime) {
				maxTime = times[i];
				maxIndex = i;
			}
		}
		return maxIndex * maxGuardID;
	}

	public static int part2() {
		int maxSleepChance = Integer.MIN_VALUE;
		int maxSleepMinute = 0;
		int maxGuardID = 0;
		for(Entry<Integer, int[]> item : dutyCycle.entrySet()) {
			int guardId = item.getKey();
			int[] times = item.getValue();
			for(int i = 0; i < times.length; i++) {
				if (times[i] > maxSleepChance) {
					maxSleepChance = times[i];
					maxGuardID = guardId;
					maxSleepMinute = i;
				}
			}
		}
		return maxSleepMinute * maxGuardID;
	}
	
	public static void main(String[] args) {
		// Test data
		List<String> testData = Arrays.asList("[1518-11-01 00:00] Guard #10 begins shift",
				"[1518-11-01 00:05] falls asleep",
				"[1518-11-01 00:25] wakes up",
				"[1518-11-01 00:30] falls asleep",
				"[1518-11-01 00:55] wakes up",
				"[1518-11-01 23:58] Guard #99 begins shift",
				"[1518-11-02 00:40] falls asleep",
				"[1518-11-02 00:50] wakes up",
				"[1518-11-03 00:05] Guard #10 begins shift",
				"[1518-11-03 00:24] falls asleep",
				"[1518-11-03 00:29] wakes up",
				"[1518-11-04 00:02] Guard #99 begins shift",
				"[1518-11-04 00:36] falls asleep",
				"[1518-11-04 00:46] wakes up",
				"[1518-11-05 00:03] Guard #99 begins shift",
				"[1518-11-05 00:45] falls asleep",
				"[1518-11-05 00:55] wakes up");
		System.out.printf("Result of Part 1 with test data: %d - expected 240%n", part1(testData));
		System.out.printf("Result of Part 2 with test data: %d - expected 4455%n", part2());
		// part 1
		System.out.printf("Result of Part 1 with input data: %d%n", part1(data));
		// Part 2
		System.out.printf("Result of Part 2 with input data: %d%n", part2());
		
	}
	
	
	
	

}
