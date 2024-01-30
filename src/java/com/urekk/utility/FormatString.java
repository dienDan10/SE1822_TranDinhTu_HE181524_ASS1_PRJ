/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.urekk.utility;

import java.util.Scanner;

/**
 *
 * @author urekk
 */
public class FormatString {
    
    // format a input string into sql string
    // replace all ['] to ''
    public static String toSQLString(String str) {
        str = str.replaceAll("'", "''");
        return str;
    }
    
    // format a sql string with line break to html string with line break
    public static String toHTMLString(String str) {
        Scanner sc = new Scanner(str);
        StringBuilder res = new StringBuilder();
        while (sc.hasNextLine()) {
            res.append(sc.nextLine());
            res.append("</br>");
        }
        
        return res.toString();
    }
}
