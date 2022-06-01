package CtCILibrary;

public class BitVector {
	private static int DATASIZE = 32;
	private int length;
	private int[] vector;
	
	public BitVector(int length) {
		this.length = length;
		if (length % DATASIZE == 0) {
			vector = new int[length / DATASIZE];
		} else {
			vector = new int[length / DATASIZE + 1];
		}
	}
	
	public int length() {
		return length;
	}
	
	public boolean get(int i) {
		int b = vector[i / DATASIZE];
		int bitindex = i % DATASIZE;
		//00100010
		if (((b >> bitindex) & 1) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void print() {
		for (int k : vector) {
			for (int i = 0; i < DATASIZE; i++) {
				if ((k >> i & 1) == 1) {
					System.out.print(1);
				} else {
					System.out.print(0);
				}
			}
			System.out.println();
		}
	}
	
	public void set(int i, boolean flag) {
		if (i >= 0 && i < length) {
			int mask = ~(1 << i);
			int b = vector[i / DATASIZE] & mask;
			if (flag) {
				vector[i / DATASIZE] = b | (1 << i);
			} else {
				vector[i / DATASIZE] = b;
			}
		}
	}
}
