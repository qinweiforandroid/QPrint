package com.qw.qprint;

import com.qw.print.command.PrintUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.println("           DINING ROOM");
        System.out.println(PrintUtils.format("ORDER NO:", "201911050001"));
        System.out.println(PrintUtils.format("ORDER TIME:", "October 28th，2018"));
        System.out.println(PrintUtils.format("TYPE:", "DINE IN"));
        System.out.println(PrintUtils.format("TABLE:", "7"));
        System.out.println(PrintUtils.getDoubleLine());
        System.out.println(PrintUtils.format("NAME", "NUM", "PRICE"));
        System.out.println(PrintUtils.getSingleLine());
        System.out.println(PrintUtils.format("Sashimi Platter ", "3", "230"));
        System.out.println(PrintUtils.format("  配料A", "", ""));
        System.out.println(PrintUtils.format("  配料B", "", ""));
        System.out.println(PrintUtils.format("Pot Sticker ", "2", "230"));
        System.out.println(PrintUtils.format("Fried Chicken Legs (Spicy Hot)", "4", "230"));
        System.out.println(PrintUtils.format("Chicken Salad", "2", "23"));
        System.out.println(PrintUtils.getDoubleLine());
        System.out.println(PrintUtils.format("", "Subtotal", "100"));
        System.out.println(PrintUtils.format("", "tax", "5"));
        System.out.println(PrintUtils.format("", "Total", "105"));
        System.out.println(PrintUtils.getDoubleLine());
        System.out.println(PrintUtils.format("PAY TYPE:", "ONLINE"));
        System.out.println("Notes:more cheese、、、");
        System.out.println(PrintUtils.getDoubleLine());
        System.out.println("           QR INFO");
        System.out.println("      Welcome next time");

    }
}