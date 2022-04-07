package com.planradar.weather;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import com.planradar.weather.utils.KelvinToCelsius;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    static KelvinToCelsius toCelsius;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @BeforeClass
    public static void Beforerun(){
        toCelsius=new KelvinToCelsius();
    }
    @Test
    public void convertToCelsiusCase0() {
        String result =toCelsius.kelvinToCelsius(301);
        assertEquals("wrong Conversion", 28+" °C",result);
    }
    @Test
    public void convertToCelsiusCase1() {
        String result =toCelsius.kelvinToCelsius(300);
        assertEquals( 27+" °C",result);
    }
}