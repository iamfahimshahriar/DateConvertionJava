package main;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
	public class LanguageConverter {
	private static final String[] banglaDigits = {"১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯", "০", "জানুয়ারী", "ফেব্রুয়ারী", "মার্চ", "এপ্রিল", "মে", "জুন", "জুলাই", "আগষ্ট", "সেপ্টেম্বার", "অক্টোবার", "নভেম্বার", "ডিসেম্বার", ":", ","};
	private static final String[] englishDigits = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", ":", ","};
	static String csvFile="/home/fahim/personal/secl/project/repo/Language Conversion/test.csv";
	public static void main(String[] args) {
	      try {
	         Class.forName("org.postgresql.Driver");
	         Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cssm-dev","cssm","cssm");
		      System.out.println("Opened database successfully");
	         PreparedStatement pst = con.prepareStatement("SELECT businessdate FROM public.accountsdailydepositwithdrawalhistory");
             ResultSet rs = pst.executeQuery();
	         while (rs.next()) {
	             System.out.print(rs.getString(1)+"----");
	             String actualDate = rs.getString(1);
	     		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	     		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
	     		LocalDate ld = LocalDate.parse(actualDate, dtf);
	     		String SringEN = dtf2.format(ld);
	             String SringBN = SringEN;
	     		for(int i =0;i<banglaDigits.length;i++){
	     			SringBN=SringBN.replace(englishDigits[i],banglaDigits[i]);
	     		}
	     		System.out.print(SringEN+"----");
	     		System.out.println(SringBN);
	         }
	      } 
	      catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }

	   }
	}
