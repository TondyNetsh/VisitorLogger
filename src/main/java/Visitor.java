import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Visitor {
    private String firstName, lastName, comments, visitorName;
    private int age;
    private String dateOfVisit;
    private String timeOfVisit;
    private static Logger myLog = LogManager.getLogger(Visitor.class.getName());

    public Visitor(String firstName,String lastName, int age, String dateOfVisit, String timeOfVisit, String comments,String visitorName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfVisit = dateOfVisit;
        this.timeOfVisit = timeOfVisit;
        this.comments = comments;
        this.visitorName = visitorName;
    }

    public static void save(String firstName,String lastName, int age, String dateOfVisit, String timeOfVisit, String comments, String visitorName) throws IOException{
        try{
            Scanner sc = new Scanner(System.in);
            File file = new File("files/visitor_"+firstName.toLowerCase()+"_"+lastName.toLowerCase()+".txt");

            if(file.exists()== false){
                System.out.println("File created successfully.");
                myLog.info("File created successfully.");
            }
            else {
                System.out.println("File creation failed.");
                myLog.error("File creation failed");
                System.out.println(0);
            }
            BufferedWriter add = new BufferedWriter(new FileWriter (file, true));
            add.write("Full Name: "+firstName+" ");
            add.write(lastName+"\n");
            add.write("Age: "+age+"\n");
            add.write("Date of visit: "+dateOfVisit+"\n");
            add.write("Time of visit: " + timeOfVisit+"\n");
            add.write("Comments: "+comments+"\n");
            add.write("Assistant Name: "+visitorName);

            add.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            myLog.error("IOException: " + e.getMessage());
        }
    }

    public static void load(String firstName,String lastName) throws IOException{
        try{
            FileReader fr = new FileReader("files/visitor_"+firstName.toLowerCase()+"_"+lastName.toLowerCase()+".txt");
            BufferedReader br = new BufferedReader(fr);
            int i;

            while (((i=br.read()) != -1)){
                System.out.print((char)i);
            }
            br.close();
            fr.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            myLog.error("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fname, lname,comments,vistorName;
        String dateOfVisit, timeOfVisit;
        int age;
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH.mm.ss");
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime timeNow = LocalDateTime.now();

        try{
            fname = JOptionPane.showInputDialog(null,"What is your first name?");
            lname = JOptionPane.showInputDialog(null,"What is your last name?");
            age = Integer.parseInt(JOptionPane.showInputDialog(null,"What is your age?"));

            dateOfVisit = dateNow.format(date);
            timeOfVisit = timeNow.format(time);

            comments = JOptionPane.showInputDialog(null,"What are your comments about us?");
            vistorName = JOptionPane.showInputDialog(null,"What is the name of person who assisted you?");

            Visitor.save(fname,lname,age,dateOfVisit,timeOfVisit,comments,vistorName);
            Visitor.load(fname,lname);
        }
        catch (IOException e){
            myLog.error("ERROR: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}