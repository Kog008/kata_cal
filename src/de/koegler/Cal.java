package de.koegler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class Cal {

    // Default global settings
    private static LocalDate now = LocalDate.now();
    private static Month desiredMonth = now.getMonth();
    private static Year desiredYear = Year.of(now.getYear());
    // Default start day of week in germany is monday.
    private static DayOfWeek startDayOfWeek = DayOfWeek.MONDAY;

    // Week has seven days - obviously I need 7 columns in my 2D array.
    private static int column_count = 7;
    // Title is not going to be part of my array, but the DayOfWeek bar (or table header) will be.
    // In common case I am going to need five rows for the displaying the days itself and one row for the tbl header.
    // In worst case last entry of array is going to be empty and I am not going to see this line in console output.
    private static int row_count = 6;
    // calendar object for printing purpose
    private static Object[][] calendar = new Object[row_count][column_count];

    /**
     * Method call with none parameters just calculates and prints current month as calendar.
     * Default case with default global settings.
     */
    public static void calculateCal() {

        printTitle();

        writeDesiredDaysToCalendar();

        setCalendar();

        formatCalendar();

        printCalendar();
    }

    /**
     * Method prints current month of current year starting the calendar at given week day.
     * @param desiredStartDayOfWeek       Starting day of week in calendar.
     */
    public static void calculateCal(String desiredStartDayOfWeek) {

        printTitle();

        setStartDayOfWeek(desiredStartDayOfWeek);

        writeDesiredDaysToCalendar();

        setCalendar();

        formatCalendar();

        printCalendar();
    }

    /**
     * Methods prints given month of given desiredYear with default start day of week.
     *
     * @param givenMonth     Given month
     * @param givenYear      Given desiredYear
     */
    public static void calculateCal(String givenMonth, String givenYear) {

        desiredMonth = Month.of(Integer.parseInt(givenMonth));
        desiredYear = Year.of(Integer.parseInt(givenYear));

        printTitle();

        // no need to call setStartDayOfWeek(...) in this case

        writeDesiredDaysToCalendar();

        setCalendar();

        formatCalendar();

        printCalendar();
    }

    /**
     * Methods prints given month of given desiredYear with given day as start day of the first week.
     *
     * @param givenMonth             Given month.
     * @param givenYear              Given desiredYear.
     * @param startingDay              Start day of the week in calendar.
     */
    public static void calculateCal(String givenMonth, String givenYear, String startingDay) {

        desiredMonth = Month.of(Integer.parseInt(givenMonth));
        desiredYear = Year.of(Integer.parseInt(givenYear));

        printTitle();

        setStartDayOfWeek(startingDay);

        writeDesiredDaysToCalendar();

        setCalendar();

        formatCalendar();

        printCalendar();
    }

    private static void setStartDayOfWeek(String desiredStartDayOfWeek) {

        for ( DayOfWeek dow: DayOfWeek.values() ) {
            if ( desiredStartDayOfWeek.toUpperCase().equals(dow.toString().toUpperCase()) ) {
                startDayOfWeek = dow;
                break;
            }
        }
    }

    private static void printCalendar() {
        ROWS:
        for(int i=0; i<row_count; i++) {

            COLUMNS:
            for (int j = 0; j < column_count; j++) {
                System.out.print(calendar[i][j] + "     ");
            }
            System.out.print("\n");
        }
    }

    private static void printTitle() {
        System.out.println("                           " +desiredMonth+ "       " +desiredYear);
    }

    private static void writeDesiredDaysToCalendar() {
        for (int i = 0; i < column_count; i++) {
            calendar[0][i] = startDayOfWeek.plus(i);
        }
    }

    private static void setCalendar() {
        // starts at first of month. Is gong to be iterated over.
        LocalDate cursorDate = LocalDate.of(desiredYear.getValue(), desiredMonth, 1);
        DayOfWeek beginInsertions = cursorDate.getDayOfWeek();


        // find column to start with
        FIND_INSERT_CELL:
        for (int i = 0; i < column_count; i++) {

            // find takes place in row 0
            if ( calendar[0][i].toString().equals(beginInsertions.toString()) ) {

                // inserts take place from row 1, but ongoing from same column i
                ROWS:
                for (int y = 1; y < row_count; y++) {

                    COLUMNS:
                    for (int x = i; x < column_count; x++) {

                        // only as long as desired month lasts
                        if( cursorDate.getMonth() == desiredMonth ) {
                            calendar[y][x] = cursorDate.getDayOfMonth();
                            cursorDate = cursorDate.plusDays(1);
                        } else {
                            break COLUMNS;
                        }
                    }

                    // only in first row insertions start not at the rows beginning
                    if (i != 0)
                        i = 0;
                }
                break FIND_INSERT_CELL;
            }
        }
    }

    private static void formatCalendar() {
        // Skip table header → row = 1.
        for (int row = 1; row < row_count; row++) {
            for (int column = 0; column < column_count; column++) {
                // Override null values to empty strings.
                // Need for white space was defined experimentally.
                if (calendar[row][column] == null)
                    calendar[row][column] = "       ";
                else
                    // Otherwise slightly format existing entry.
                    // Add fixed amount of white space and trim same amount of leading spaces as long as the given entry is
                    calendar[row][column] = ("        " + calendar[row][column]).substring(calendar[row][column].toString().length()+1);
            }
        }
    }

    //region Getter - basically for testing purpose
    public static LocalDate getNow() {
        return now;
    }

    public static Month getDesiredMonth() {
        return desiredMonth;
    }

    public static Year getDesiredYear() {
        return desiredYear;
    }

    public static DayOfWeek getStartDayOfWeek() {
        return startDayOfWeek;
    }

    public static int getColumn_count() {
        return column_count;
    }

    public static int getRow_count() {
        return row_count;
    }

    public static Object[][] getCalendar() {
        return calendar;
    }
    //endregion
}
