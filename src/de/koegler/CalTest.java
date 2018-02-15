package de.koegler;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import static de.koegler.Cal.calculateCal;
import static de.koegler.Cal.getCalendar;
import static de.koegler.Cal.getDesiredMonth;
import static de.koegler.Cal.getDesiredYear;
import static de.koegler.Cal.getStartDayOfWeek;

public class CalTest {

    private String inputDayOfWeek = "friday";
    private String inputMonth = "3";
    private String inputYear = "2020";

    @Test
    @Ignore     // default settings can no longer be guaranteed, if calculateCal is called in unpredictable manner
    public void calculateCalWithNoneParam() {
        calculateCal();
        Assert.assertNotNull(getCalendar());
        Assert.assertEquals( "desiredMonth is different to default use case.", LocalDate.now().getMonth(), getDesiredMonth());
        Assert.assertEquals( "desiredYear is different to default use case.", LocalDate.now().getYear(), getDesiredYear().getValue());
        Assert.assertEquals( "desiredStartDayOfWeek is different to default use case.", DayOfWeek.MONDAY.getValue(), getStartDayOfWeek().getValue());
    }

    @Test
    public void calculateCalWithOneParam() {
        calculateCal(inputDayOfWeek);
        Assert.assertNotNull(getCalendar());
        Assert.assertTrue("Error during setting startDayOfWeek", getStartDayOfWeek().toString().toUpperCase().equals(inputDayOfWeek.toUpperCase()));
    }

    @Test
    public void calculateCalWithTwoParam() {
        calculateCal(inputMonth, inputYear);
        Assert.assertNotNull(getCalendar());
        Assert.assertEquals("Error during setting desiredYear", Year.of(Integer.parseInt(inputYear)), getDesiredYear());
        Assert.assertEquals("Error during setting desriedMonth", Month.of(Integer.parseInt(inputMonth)), getDesiredMonth());
    }

    @Test
    public void calculteCalWithThreeParam() {
        calculateCal(inputMonth, inputYear, inputDayOfWeek);
        Assert.assertNotNull(getCalendar());
        Assert.assertEquals("Error during setting desiredYear", Year.of(Integer.parseInt(inputYear)), getDesiredYear());
        Assert.assertEquals("Error during setting desiredMonth", Month.of(Integer.parseInt(inputMonth)), getDesiredMonth());
        Assert.assertTrue("Error during setting startDayOfWeek", getStartDayOfWeek().toString().toUpperCase().equals(inputDayOfWeek.toUpperCase()));

    }
}