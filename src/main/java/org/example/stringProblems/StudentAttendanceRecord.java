package org.example.stringProblems;

import java.util.HashMap;
import java.util.Map;

public class StudentAttendanceRecord {
    public boolean checkRecord(String s) {
        int aCount=0;
        int consecutiveLCount=0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if('A' == ch){
                aCount++;
                if (aCount>=2) return false;
            }

            if ('L'==ch){
                consecutiveLCount++;
                if (consecutiveLCount>=3) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StudentAttendanceRecord studentAttendanceRecord = new StudentAttendanceRecord();
        System.out.println(studentAttendanceRecord.checkRecord("PPALLL"));
    }
}
