package day14;

public class Day14 {
	
	public static final int GOAL_LENGTH = 10;
	
	public static final int TARGET_LENGTH = 640441;
	

	public static void part1() {
		StringBuilder sb = new StringBuilder();
		sb.append("3");
		sb.append("7");
		int pos1 = 0;
		int pos2 = 1;
		while(sb.length() < TARGET_LENGTH + GOAL_LENGTH) {
			int a = Integer.parseInt(sb.substring(pos1,pos1+1));
			int b = Integer.parseInt(sb.substring(pos2, pos2+1));
			int sum = a + b;
			if (sum > 9) {
				sb.append(String.valueOf(sum / 10));
				sb.append(String.valueOf(sum % 10));
			} else {
				sb.append(String.valueOf(sum));
			}
			pos1 = (pos1 + 1 + a) % sb.length();
			pos2 = (pos2 + 1 + b) % sb.length();
		}
		
		System.out.println(sb.substring(TARGET_LENGTH));
	}
	
	public static void part2() {
		StringBuilder sb = new StringBuilder();
		sb.append("3");
		sb.append("7");
		int pos1 = 0;
		int pos2 = 1;
		int foundIndex = -1;
		int desiredSize = 1_000_000;
		while (foundIndex == -1) {
			while(sb.length() < desiredSize) {
				int a = Integer.parseInt(sb.substring(pos1,pos1+1));
				int b = Integer.parseInt(sb.substring(pos2, pos2+1));
				int sum = a + b;
				if (sum > 9) {
					sb.append(String.valueOf(sum / 10));
					sb.append(String.valueOf(sum % 10));
				} else {
					sb.append(String.valueOf(sum));
				}
				pos1 = (pos1 + 1 + a) % sb.length();
				pos2 = (pos2 + 1 + b) % sb.length();
			}
			foundIndex = sb.indexOf(String.valueOf(TARGET_LENGTH));
			desiredSize += 1_000_000;
		}
		
		System.out.println(foundIndex);
		
	}

	
	
	public static void main(String[] args) {
		part1();
		part2();
	}
}
