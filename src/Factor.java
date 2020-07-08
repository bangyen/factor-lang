import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigInteger;
import java.nio.file.*;
import java.io.*;

public class Factor {

	public static final BigInteger zero = BigInteger.ZERO;
	public static final BigInteger one = BigInteger.ONE;
	public static final BigInteger two = BigInteger.TWO;
	
	public static ArrayList<Integer[]> factorize(BigInteger num) {
		
		ArrayList<Integer[]> factors = new ArrayList<Integer[]>();
		BigInteger temp = num;
		BigInteger k = two;
		
		while (temp.compareTo(one) != 0) {
			if (temp.mod(k).compareTo(zero) == 0) {
				int mod = k.intValue() % 11;
				if (7 <= mod && mod <= 8) {
					while (temp.mod(k).compareTo(zero) == 0) {
						temp = temp.divide(k);
						factors.add(new Integer[] {k.intValue(), 1});
					}
				} else {
					int count = 0;
					while (temp.mod(k).compareTo(zero) == 0) {
						temp = temp.divide(k);
						count++;
					}
					factors.add(new Integer[] {k.intValue(), count});
				}
			}
			k = k.add(one);
		}
		return factors;
	}
	
	public static int findBracket(ArrayList<Integer[]> symbols, int index, int mode) {
		
		int diff = mode == 7 ? 1 : -1;
		int bracket = diff;
		int num = index;
		
		while (bracket != 0) {
			num = num + diff;
			switch (symbols.get(num)[0] % 11) {
				case 7:
					bracket++;
					break;
				case 8:
					bracket--;
					break;
				default: break;
			}
		}
		return num;
	}
	
	public static void main(String[] args) {
		
		BigInteger num = BigInteger.ZERO;
		
		try {
			if (!args[0].strip().endsWith(".fact")) {
				System.out.println("Not a valid program.");
				return;
			}
			String data = "";
			String example = ".\\examples\\" + args[0];
			if (new File(example).exists()) {
				data = new String(Files.readAllBytes(Paths.get(example)));
			} else {
				data = new String(Files.readAllBytes(Paths.get(args[0])));
			}
	        	num = new BigInteger(data.replaceAll("[^0-9]", ""));
		} catch (NumberFormatException
				|ArrayIndexOutOfBoundsException
				|IOException nfe) {
			System.out.println("Not a valid program.");
		}
		
		if (num.compareTo(one) > 0) {
			
			ArrayList<Integer> cells = new ArrayList<Integer>();
			cells.add(0);
			int pointer = 0;
			boolean line = false;
			
			ArrayList<Integer[]> factors = factorize(num);
			Scanner input = new Scanner(System.in);
			
			for (int j = 0; j < factors.size(); j++) {
				
				Integer[] pair = factors.get(j);
				
				switch (pair[0] % 11) {
					case 1:
						pointer += pair[1];
						if (pointer + 1 > cells.size()) {
							int i = 0;
							while (i < pair[1]) {
								cells.add(0);
								i++;
							}
						}
						break;
					case 2:
						pointer = Math.max(pointer - pair[1], 0);
						break;
					case 3:
						cells.set(pointer, (cells.get(pointer) + pair[1]) % 256);
						break;
					case 4:
						cells.set(pointer, (cells.get(pointer) - pair[1] + 256) % 256);
						break;
					case 5:
						for (int i = 0; i < pair[1]; i++) {
							System.out.print((char) (int) cells.get(pointer));
						}
						break;
					case 6:
						for (int i = 0; i < pair[1]; i++) {
							System.out.print(line ? "\nInput: " : "Input: ");
							int charInput = (int) (input.nextLine() + (char) 0).charAt(0);
							cells.set(pointer, charInput);
							line = true;
						}
						break;
					case 7:
						if (cells.get(pointer) == 0) {
							j = findBracket(factors, j, 7) - 1;
						}
						break;
					case 8:
						if (cells.get(pointer) != 0) {
							j = findBracket(factors, j, 8) - 1;
						}
						break;
					default: break;
				}
			}
			
			input.close();
		}
	}

}
