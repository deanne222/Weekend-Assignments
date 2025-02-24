package com.grading.Grading.MarkCard;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StudentGrade {

    @PostMapping("/studentEntry")
    public String StudentEntry(@RequestBody Map<String, String> req){
        String Name = req.get("name");
        String Rollno = req.get("rollno");
        String DOB = req.get("dob");
        String Gender = req.get("gender");
        String Admno = req.get("admno");
        String Dept = req.get("dept");
        String BG = req.get("bg");
        String Address = req.get("address");
        String Pincode = req.get("pincode");

        return "Student Detail Added Successfully";
    }

    @PostMapping("/findResult")
    public String FindResult(@RequestBody Map<String, String> grade){
        try {
            String Rollno = grade.get("rollno");
            int Maths = parseSafe(grade.get("maths"));
            int MathsT = parseSafe(grade.get("mathst"));
            int Physics = parseSafe(grade.get("physics"));
            int PhysicsT = parseSafe(grade.get("physicst"));
            int Chemistry = parseSafe(grade.get("chemistry"));
            int ChemistryT = parseSafe(grade.get("chemistryt"));
            int English = parseSafe(grade.get("english"));
            int EnglishT = parseSafe(grade.get("englisht"));
            int Social = parseSafe(grade.get("social"));
            int SocialT = parseSafe(grade.get("socialt"));
            int Computer = parseSafe(grade.get("computer"));
            int ComputerT = parseSafe(grade.get("computert"));

            if (MathsT == 0 || PhysicsT == 0 || ChemistryT == 0 || EnglishT == 0 || SocialT == 0 || ComputerT == 0) {
                return "Error: Total marks cannot be zero.";
            }

            double mathsPerc = (Maths / (double) MathsT) * 100;
            double physicsPerc = (Physics / (double) PhysicsT) * 100;
            double chemistryPerc = (Chemistry / (double) ChemistryT) * 100;
            double englishPerc = (English / (double) EnglishT) * 100;
            double socialPerc = (Social / (double) SocialT) * 100;
            double computerPerc = (Computer / (double) ComputerT) * 100;

            double avg = calculate(Maths, Physics, Chemistry, English, Social, Computer);

            if (avg < 50 || mathsPerc < 50 || physicsPerc < 50 || chemistryPerc < 50 || englishPerc < 50 || socialPerc < 50 || computerPerc < 50) {
                return "Student " + Rollno + " failed with grade F";
            }
            return "Student " + Rollno +" passed with grade " + getGrade(avg) ;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getGrade(double avg) {
        if (avg >= 90) return "S";
        if (avg >= 85) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 75) return "B+";
        if (avg >= 70) return "B";
        if (avg >= 65) return "C+";
        if (avg >= 60) return "C";
        if (avg >= 55) return "D+";
        if (avg >= 50) return "D";
        return "F";
    }
    private int parseSafe(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    private double calculate(int... marks) {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum / (double) marks.length;
    }
}
