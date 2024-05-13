/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studentapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author arnav
 */
public class DBAccess {
 
    Connection conn = null;
    private int course;
    private int instructor_id;
    // Constructor - DB Connection
    DBAccess()
    {
       
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UNIVERSITY_DB","postgres","postgres");
            System.out.println("Opened database successfully");
            System.out.println("111");
        }
        catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName()+":"+e.getMessage());
           // System.exit(0);
        }
        
    }
    
    
    public List<Student> ReadStudentData()
    {
        List<Student> studentList = new ArrayList<Student>();
        
        try {
            try (PreparedStatement stmt = conn.prepareStatement("select * from student")) {
                ResultSet rSet = stmt.executeQuery();
                
                
                
                while(rSet.next())
                {
                    Student student = new Student();
                    student.setId(rSet.getInt("id"));
                    student.setName(rSet.getString("name"));
                    student.setAge(rSet.getInt("age"));
                    student.setGender(rSet.getString("gender"));
                    student.setMobileNumber(rSet.getLong("mobile_no"));
                    studentList.add(student);
                    
                    
//                System.out.println("ID = "+ student.getId());
//                System.out.println("name = "+ student.getName());
//                System.out.println("age = "+ student.getAge());
//                System.out.println("gender = "+ student.getGender());
                }
                rSet.close();
            }
            System.out.println();           
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            
        } 
        return studentList;
    }
    
    
    public int InsertInStudent(Student student )
    {
        try {
            //first is to create statement
            String str = "Insert into student(id,name,age,mobile_no,gender) values(?,?,?,?,?);";
            try (PreparedStatement stmt = conn.prepareStatement(str)) {
                stmt.setInt(1,student.getId());
                stmt.setString(2, student.getName());
                stmt.setInt(3, student.getAge());
                stmt.setLong(4,student.getMobileNumber());
                stmt.setString(5,student.getGender());
                
                stmt.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
    
   public int deleteInStudent(int id){
           
        try {
            
//            Class.forName("org.postgresql.Driver");
//            c=DriveManager.getConnection("jdbc:postgresql://localhost:5432/UNIVERSITY_DB","postgres","postgres");
//            c.setAutoCommit(false);
//            System.out.println("Opened data successfully");
//            stmt = c.createStatement();
            
            String str = "Delete from student where id = ? ";
            try (PreparedStatement stmt = conn.prepareStatement(str)) {
                stmt.setInt(1,id);
                
                stmt.executeUpdate();
            } 

        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
   }
   
   
    public int updateinStudent(Student student){
       try{
           //Statement stmt = c.createStatement();
            String str = "Update student set name = ?, age = ?, gender = ?, mobile_no = ? where id = ?;";
           try (PreparedStatement stmt = conn.prepareStatement(str)) {
               stmt.setInt(5,student.getId());
               stmt.setString(1, student.getName());
               stmt.setInt(2, student.getAge());
               stmt.setString(3, student.getGender());
               stmt.setLong(4, student.getMobileNumber());
               
               
               stmt.executeUpdate();
           }
            
           
       }catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
   }
    
    
    
    
    
    //for course tab
    public List<Course> ReadCourseData()
    {
        List<Course> courseList = new ArrayList<Course>();
        
        try {
            try (PreparedStatement stmt = conn.prepareStatement("select * from course")) {
                ResultSet rSet = stmt.executeQuery();
                
                
                
                while(rSet.next())
                {
                    Course course = new Course();
                    course.setId(rSet.getInt("course_id"));
                    course.setCourse(rSet.getString("course"));
                    course.setCredits(rSet.getInt("credits"));
                    
                    courseList.add(course);
                }
                rSet.close();
            }
            System.out.println();           
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            
        } 
        return courseList;
    }
    
    
    
     public int InsertInCourse(Course course )
    {
        try {
            //first is to create statement
            String str = "Insert into course(course,credits) values(?,?);";
            try (PreparedStatement stmt = conn.prepareStatement(str)) {
                stmt.setString(1, (String) course.getCourse());
                stmt.setInt(2, course.getCredits());
                
                stmt.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
     
     
      public int deleteInCourse(int id){
           
        try {
            
//            Class.forName("org.postgresql.Driver");
//            c=DriveManager.getConnection("jdbc:postgresql://localhost:5432/UNIVERSITY_DB","postgres","postgres");
//            c.setAutoCommit(false);
//            System.out.println("Opened data successfully");
//            stmt = c.createStatement();
            
            String str = "Delete from course where course_id= ? ";
            try (PreparedStatement stmt = conn.prepareStatement(str)) {
                stmt.setInt(1, id);
                
                stmt.executeUpdate();
            } 

        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
   }
   
   
    public int updateinCourse(Course course){
       try{
           //Statement stmt = c.createStatement();
            String str = "Update student set name = ? where id = ?;";
           try (PreparedStatement stmt = conn.prepareStatement(str)) {
               stmt.setString(1, course.getCourse());
               stmt.setInt(2, course.getCredits());
               
               
               stmt.executeUpdate();
           }
            
           
       }catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
   }
    
    
    
    
    
    
    
    
    
    
     public List<Instructor> ReadInstructorData()
    {
        List<Instructor> instructorList = new ArrayList<Instructor>();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from instructor");
            ResultSet rSet = stmt.executeQuery();
            
            
            
            while(rSet.next())
            {
                Instructor instructor = new Instructor();
                //instructor.setId(rSet.getInt("id"));
                instructor.setName(rSet.getString("name"));
                instructor.setAge(rSet.getInt("age"));
                instructor.setGender(rSet.getString("gender"));
                instructor.setMobileNo(rSet.getLong("mobile_no"));
                instructorList.add(instructor);
                
                
//                System.out.println("ID = "+ student.getId());
//                System.out.println("name = "+ student.getName());
//                System.out.println("age = "+ student.getAge());
//                System.out.println("gender = "+ student.getGender());                
            }  
            rSet.close();
            stmt.close();
            System.out.println();           
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            
        } 
        return instructorList;
    }
   public int InsertInInstructor(Instructor instructor )
    {
        try {
            //first is to create statement
            String str = "Insert into instructor(name,age,mobile_no,gender) values(?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(str);
            //stmt.setInt(1,instructor.getId());
            stmt.setString(1, instructor.getName());
            stmt.setInt(2, instructor.getAge());
            stmt.setLong(3,instructor.getMobileNo());
            stmt.setString(4,instructor.getGender());
                    
            stmt.executeUpdate();
            stmt.close();
            
            //bind data to statement
            //execute statement
            //
            
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
    }
    
   public int deleteInInstructor(int instructor_id){
           
        try {
            
//            Class.forName("org.postgresql.Driver");
//            c=DriveManager.getConnection("jdbc:postgresql://localhost:5432/UNIVERSITY_DB","postgres","postgres");
//            c.setAutoCommit(false);
//            System.out.println("Opened data successfully");
//            stmt = c.createStatement();
            
            String str = "Delete from instructor where instructor_id = ? ";
            PreparedStatement stmt = conn.prepareStatement(str);
            stmt.setInt(1,instructor_id);
           
            stmt.executeUpdate();
            stmt.close(); 

        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
   }
   
   
    public int updateinInstructor(Instructor instructor){
       try{
           //Statement stmt = c.createStatement();
            String str = "Update student set name = ? where name = ?;";
           try (PreparedStatement stmt = conn.prepareStatement(str)) {
               stmt.setString(1, instructor.getName());
               stmt.setInt(2,instructor.getAge());
               
               
               stmt.executeUpdate();
           }
            
           
       }catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 1;
   }
    
    
     
     
     
     
     
     
     
     
     
     
     
    public static void main(String args[]){   
            
          DBAccess dbAccess = new DBAccess();
          System.out.println("111");
          Student student = new Student();
          student.setId(1);
          student.setAge(34);
          student.setGender("M");
          student.setMobileNumber(98824660);
           System.out.println("222");
          int success = dbAccess.InsertInStudent(student);
           System.out.println("333");
          if(success == 1)
          {
              System.out.println("Insert command executed succesfully");
              dbAccess.ReadStudentData();
                System.out.println("444");
          }  
          
       dbAccess.deleteInStudent(34);
    }
    
    
   
    // Methods - all three tables (read, add, update, delete)

 
    
    
    
    
}
