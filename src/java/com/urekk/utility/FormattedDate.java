/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.urekk.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author urekk
 */
public class FormattedDate {
    private static final DateTimeFormatter SQL_DATETIME = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    private static final DateTimeFormatter VN_DATETIME = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss");
    
    public static String getCurrentDateTime() {
        String dateTime = SQL_DATETIME.format(LocalDateTime.now());
        return dateTime;
    }
    
    public static String getVNDateTime(String dateTime) {
        LocalDateTime local = LocalDateTime.parse(dateTime, SQL_DATETIME);
        return VN_DATETIME.format(local);
    }
    
}
