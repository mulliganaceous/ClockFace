package demo;
import java.util.Scanner;

import main.TimeDate;

public class TimeDateDemo {
	public static void run_demo(Scanner input, TimeDate timedate) {
		int year, mo, d;
		System.out.print("Enter year: ");
		year = input.nextInt();
		System.out.print("Enter month: ");
		mo = input.nextInt();
		System.out.print("Enter day: ");
		d = input.nextInt();
		if (year < 0 || mo <= 0 || d <= 0)
			timedate.resetDate();
		timedate.setDate(year, mo, d);
		
		int h, m, s;
		System.out.print("Enter hour: ");
		h = input.nextInt();
		System.out.println("Enter minute: ");
		m = input.nextInt();
		System.out.println("Enter second: ");
		s = input.nextInt();
		timedate.setTime(h, m, s);
	}
	
	public static void main(String[] args) {
		TimeDate timedate = new TimeDate();
		boolean loop = true;
		Scanner input = new Scanner(System.in);
		while(loop) {
			run_demo(input, timedate);
			System.out.println(timedate);
			System.out.println("Do you want another date? ");
			int k = input.nextInt();
			if (k == 0) loop = false;
		}
		input.close();
	}
}
