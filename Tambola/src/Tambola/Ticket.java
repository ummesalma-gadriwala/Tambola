package Tambola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * @author Umme Salma Gadriwala
 * Purpose: This class is a single 3x9 ticket for the popular board game, also known as Housie.
 * According to Wikipedia, a typical Tambola ticket contains fifteen numbers, arranged in nine columns by three rows.
 * Each row contains five numbers and four blank spaces.
 * Each column contains either one, two or very rarely three, numbers such that:
 * 	*The first column contains numbers from 1 to 9,
 * 	*The second column numbers from 10 to 19,
 * 	*The third 20 to 29 and so on up until the last column, which contains numbers from 80 to 90.
 * A zero on the ticket represents a blank space.
 */
public class Ticket {
	int[][] ticket = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
					   { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
					   { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	Random rand = new Random();

	
	/**
	 * Purpose: Generates a single ticket using the Random number generator.
	 * A zero represents a blank space.
	 */
	public Ticket() {
		positions();
		ticket();
	}

	
	/**
	 * Purpose: This method determines the positions on the ticket that will contain a number.
	 * 1 represents a number, 0 represents a blank.
	 */
	private void positions() {
		for (int i = 0; i < 3; i++) {
			int count = 0;
			while (count < 5) {
				int position = rand.nextInt(9);
				while (ticket[i][position] == 1) {
					position = rand.nextInt(9);
				}
				ticket[i][position] = 1;
				count++;
			}
		}
	}

	/**
	 * Purpose: This method populates the ticket with valid numbers
	 * Every 1 in each row is replaced with a valid row number.
	 */
	private void ticket() {
		int min = 0;
		for (int i = 0; i < 9; i++) {
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			int count = ticket[0][i] + ticket[1][i] + ticket[2][i];
			while (count > 0) {
				int num = rand.nextInt(10) + min;
				while (numbers.contains(num)) {
					num = rand.nextInt(10) + min;
				}
				numbers.add(num);
				count--;
			}
			numbers.sort(null);
			int index = 0;
			if (ticket[0][i] == 1) {
				ticket[0][i] = numbers.get(index);
				index++;
			}
			if (ticket[1][i] == 1) {
				ticket[1][i] = numbers.get(index);
				index++;
			}
			if (ticket[2][i] == 1) {
				ticket[2][i] = numbers.get(index);
				index++;
			}
			min += 10;
			if (i == 8) min += 1;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(ticket);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.ticket[i][j] != other.ticket[i][j])
					return false;
			}
		}
		return true;
	}

	/**
	 * @return Convert the Ticket to a string
	 */
	public String get() {
		String p = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				p = p + ticket[i][j] + ",";
			}
			p = p.substring(0, p.length()-1) + "\n";
		}
		return p;
	}
}
