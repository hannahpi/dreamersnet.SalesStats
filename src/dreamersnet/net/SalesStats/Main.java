package dreamersnet.net.SalesStats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	final static String DATA_FILE = "WeeklySales.dat";
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File(DATA_FILE);
		Scanner fileScan = new Scanner(file);
		System.out.println("File " + DATA_FILE + "not found");				
		String curLine;
		String[] weekSalesStr;
		int numWeeks = 0;
		Double curWeekTotalSales;
		Double overallAverageWeeklySales =  0.00;
		int highestWeek = 0;
		int lowestWeek = 0;
		ArrayList<Double> weekSales = new ArrayList<>();
		ArrayList<Double> avgWeekSales = new ArrayList<>();
		
		while (fileScan.hasNextLine() ) {
			numWeeks++;
			curLine = fileScan.nextLine();
			curWeekTotalSales = 0.00;
			weekSalesStr = curLine.split(",");
			for (String day:weekSalesStr) {
				Double daySale = Double.parseDouble(day);
				//daySales.add(daySale);
				curWeekTotalSales += daySale;
				
			}
			weekSales.add(curWeekTotalSales);
			avgWeekSales.add(curWeekTotalSales/7.0);									
		}
		
		//Determine highest sales, lowest sales, overall average
		if (numWeeks < 2) {
			System.out.println("There was only 1 week of sales!!!  No highest or lowest stats are available");
			overallAverageWeeklySales = weekSales.get(0); 
		} else {
			if (weekSales.get(0) <= weekSales.get(1)) {
				lowestWeek = 0;
				highestWeek = 1;
			} else {
				lowestWeek = 1;
				highestWeek = 0;
			}
			Double totalAvgWeekSales = 0.00;
			for (int i=0; i<numWeeks; i++) {
				if (weekSales.get(i) < weekSales.get(lowestWeek)) {
					lowestWeek = i;
				} else if (weekSales.get(i) > weekSales.get(highestWeek)) {
					highestWeek = i;
				}
				totalAvgWeekSales += avgWeekSales.get(i);
			}
			overallAverageWeeklySales = totalAvgWeekSales / (numWeeks * 1.0);
			
		}
		
		//Output results.
		System.out.println("Average Weekly Sales");
		System.out.println("Week             Average Sales           Total Sales ");
		System.out.println("----         -------------------      ----------------");
		for (int i=0; i<numWeeks; i++) {
			System.out.println(" " + (i+1) + "              " + avgWeekSales.get(i) + "          " + weekSales.get(i));
		}
		System.out.println();
		if (numWeeks > 2) {
			System.out.println("The week with the highest sales was Week " + (highestWeek+1) + " with " + weekSales.get(highestWeek));
			System.out.println("The week with the lowest sales was Week " + (lowestWeek+1) + " with " + weekSales.get(lowestWeek));
		}
		System.out.println("The average overall was " + overallAverageWeeklySales);
		fileScan.close();
		
	}
}
