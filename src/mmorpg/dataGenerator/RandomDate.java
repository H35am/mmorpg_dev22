/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmorpg.dataGenerator;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author 0775768 <Hesam.Zarza>
 */
public class RandomDate {
    public Date nextDate(){
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1900,2010);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return new Date(gc.get(gc.YEAR), gc.get(gc.MONTH), gc.get(gc.DAY_OF_MONTH));
    }
    
    private int randBetween(int start, int end){
        return start + (int)Math.round(Math.random()*(end - start));
    }
    
}
