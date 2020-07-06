import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.math.BigInteger;
import java.io.*;

public class Factor {

	public static void main(String[] args) {
		
		BigInteger num = BigInteger.ZERO;
		BigInteger zero = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		BigInteger two = BigInteger.TWO;
		
		try {
	        num = new BigInteger(args[0]);
	    } catch (NumberFormatException|ArrayIndexOutOfBoundsException nfe) {
	        System.out.println("Not a valid program.");
	    }
		
		if (num.compareTo(one) > 0) {
			
			ArrayList<Integer[]> factors = new ArrayList<Integer[]>(); 
			ArrayList<Integer> cells = new ArrayList<Integer>();
			cells.add(0);
			String s = "";
			int pointer = 0;
			BigInteger k = two;
			
			while (num.compareTo(one) != 0) {
				if (num.mod(k).compareTo(zero) == 0) {
					int count = 0;
					while (num.mod(k).compareTo(zero) == 0) {
						num = num.divide(k);
						count++;
					}
					factors.add(new Integer[] {k.intValue(), count});
				}
				k = k.add(one);
			}
			
			Scanner input = new Scanner(System.in);
			
			for (int j = 0; j < factors.size(); j++) {
				
				boolean eof = false; if (eof) break;
				Integer[] pair = factors.get(j);
				int matching = 0;
				
				switch (pair[0] % 11) {
					case 1:
						pointer += pair[1];
						if (pointer + 1 > cells.size()) {
							cells.add(0);
						}
						break;
					case 2:
						pointer = Math.max(pointer - pair[1], 0);
						break;
					case 3:
						cells.set(pointer, (cells.get(pointer) + pair[1]) % 256);
						break;
					case 4:
						cells.set(pointer, (cells.get(pointer) - pair[1]) % 256);
						break;
					case 5:
						if (cells.get(pointer) == 0) {
							eof = true;
							break;
						}
						for (int i = 0; i < pair[1]; i++) {
							s += (char) (int) cells.get(pointer);
						}
						break;
					case 6:
						for (int i = 0; i < pair[1]; i++) {
							System.out.print("Input: ");
							int charInput = (int) (input.nextLine() + " ").charAt(0);
							cells.set(pointer, charInput);
						}
						break;
					case 7:
						if (cells.get(pointer) == 0) {
							matching++;
							while (matching != 0) {
								j++;
								int val = factors.get(j)[0] % 11;
								if (val == 7) {
									matching++;
								} else if (val == 8) {
									matching--;
								}
							}
						}
						break;
					case 8:
						if (cells.get(pointer) != 0) {
							matching--;
							while (matching != 0) {
								j--;
								int val = factors.get(j)[0] % 11;
								if (val == 7) {
									matching++;
								} else if (val == 8) {
									matching--;
								}
							}
						}
						break;
				}
			}
			
			input.close();
			System.out.println(s);
		}
	}

}
