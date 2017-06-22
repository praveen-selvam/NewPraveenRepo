
package database;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
public class Database {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
    Connection con= (Connection) DriverManager.getConnection( "jdbc:mysql://localhost:3306/atlocaldb","atlocaldbuser","atpassword");  
            
            Statement stmt=(Statement) con.createStatement();
            
            // CREATE tabl
            
            /* String sql = "create table employee " +
                     "(id integer not null, " +
                     " name varchar(50), " +
                     " address  varchar(50), " +
                     " age   integer, " +
                     " primary key ( id ))";
      
             stmt.executeUpdate(sql);      
     System.out.println("Table Created.");*/
      
    
            //INSERT
            
        /*    String sql = "INSERT INTO employee " + "VALUES (101, 'Srini', 'Vasan',58)";
                   stmt.executeUpdate(sql);
                    sql = "INSERT INTO employee " + "VALUES (102, 'Deepa', 'Rajan',68)";
                     stmt.executeUpdate(sql);
                     sql = "INSERT INTO employee " + "VALUES (103, 'Sarath', 'Kumar',48)";
                     stmt.executeUpdate(employee);
        
            */
            //DISPLAY
            
            String sql = "SELECT id, name, address, age FROM employee";
              ResultSet rs = stmt.executeQuery(sql);
              //STEP 5: Extract data from result set
              while(rs.next()){
                 //Retrieve by column name
                 int Id  = rs.getInt("id");
                 String Name = rs.getString("name");
                 String Address = rs.getString("address");
                 int Age = rs.getInt("age");
                

                 //Display values
                 System.out.print("ID: " + Id);
                 System.out.print(", AGE: " + Name);
                 System.out.print(", FIRST: " + Address);
                 System.out.println(", LAST: " + Age);
            
              } 
          System.out.println();
          System.out.println("Data's from table in given database...");  
            }catch(Exception e){ System.out.println(e);}  
            }  
}
