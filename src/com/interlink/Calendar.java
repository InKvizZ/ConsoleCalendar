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
		
		System.out.printf("dd/mm/yyyy%n");
		
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
		
		System.out.format("+-----+-----+-----+-----+-----+-----+-----+%n");
		System.out.format("| Mon | Tue | Wed | Thu | Fri | Sat | San |%n");
		System.out.format("+-----+-----+-----+-----+-----+-----+-----+%n");
		
		getMonthForConsole(date);
		
		System.out.format("+-----+-----+-----+-----+-----+-----+-----+%n");
	}

	
	private static void getMonthForConsole(LocalDate date) {
		Month month = date.getMonth();
		String leftAlignFormat = "| %-3s ";
		
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
						System.out.format(leftAlignFormat, dayOfMonth + "*");
						//TODO implement color
					} else if (false) {
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
