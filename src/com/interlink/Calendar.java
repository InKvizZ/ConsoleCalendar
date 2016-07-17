package com.interlink;

import java.time.*;
import java.text.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import javax.xml.stream.events.StartDocument;
public class Calendar {

	public static void main(String[] args) {
		LocalDate date = null;
		
		System.out.printf("Enter the date. Format : dd/mm/yyyy%n");
		
		Scanner scanner = new Scanner(System.in);
		String iputString = scanner.nextLine();
		
		//TODO add validation
		if (iputString.equals("")) { 
			date = LocalDate.now();
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			date = LocalDate.parse(iputString, formatter);	
		}
		
		scanner.close();
		System.out.println(date);

		System.out.format("+-------+------+-------+------+------+-----+-----+%n");
		System.out.format("|  Mon  |  Tue |  Wed  | Thu  | Fri  | \u001B[31mSat\u001B[0m | \u001B[31mSan\u001B[0m |%n");
		System.out.format("+-------+------+-------+------+------+-----+-----+%n");
		
		getMonthForConsole(date);
		
		System.out.format("+-------+------+-------+------+------+-----+-----+%n");
	}

	
	private static void getMonthForConsole(LocalDate date) {
		Month month = date.getMonth();
		String leftAlignFormat = " | %-3s ";
		
		LocalDate firstDay = date.withDayOfMonth(1);
		
		int offset =  firstDay.getDayOfWeek().getValue();
		
		int dayOfMonth = 1;
		int dayCountInMonth = month.maxLength();
		
		while (dayOfMonth <= dayCountInMonth) {
			for (int i = 1; i <= 7; i++) {
				if (i >= offset && dayOfMonth <= dayCountInMonth) {
					LocalDate currentDay = date.withDayOfMonth(dayOfMonth);
					
					if (currentDay.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
							currentDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
						System.out.format(leftAlignFormat, "\u001B[31m" + dayOfMonth + "\u001B[0m");
						//TODO implement color
					} else if (date.equals(currentDay)) {
						System.out.format(leftAlignFormat, "\u001B[32m" + dayOfMonth + " \u001B[0m");
						//TODO implement CURRENT_DAY
					} else {
						System.out.format(leftAlignFormat, dayOfMonth);						
					}
					
					dayOfMonth++;
				} else {
					System.out.format(leftAlignFormat, "");
				}
			}
			// reset offset
			offset = 0;
			System.out.println("|");
		}
	}
}
