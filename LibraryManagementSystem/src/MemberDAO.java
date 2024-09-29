import java.lang.ref.PhantomReference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class MemberDAO{
    //DAO  = Data Access Object
    public static void addMember(){
        Scanner sc = new Scanner(System.in);
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("INSERT INTO members(name, address, phoneNumber, emailID) VALUES (?,?,?,?)");
            while (true)
            {
                System.out.println("--------Adding Member------");
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter address: ");
                String address = sc.next();
                System.out.print("Enter phone number: ");
                String phoneNumber = sc.next();
                System.out.print("Enter email ID: ");
                String emailID = sc.next();
                System.out.print("Want to add more member? (Y/N): ");
                String choice = sc.next();
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,address);
                preparedStatement.setString(3,phoneNumber);
                preparedStatement.setString(4,emailID);
                preparedStatement.addBatch();
                preparedStatement.executeBatch();
                if (choice.equalsIgnoreCase("N")){
                    break;
                }
            }
//            int [] arr = preparedStatement.executeBatch();
//
//            for (int j : arr) {
//            if (j == 0) {
//                System.out.println("Error!!!");
//            }
//        }

            preparedStatement.close();
            DBConnection.closeConnection(DBConnection.getConnection());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateMember(){
        try {
            Scanner sc = new Scanner(System.in);
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("UPDATE members SET name = ? , address= ?, phoneNumber=?, emailID = ? WHERE memberID = ?");
            while (true)
            {
                System.out.println("------Enter new data------");
                System.out.print("Enter Member ID for update: ");
                int memberId = sc.nextInt();
                System.out.print("Enter name: ");
                String name =  sc.next();
                System.out.print("Enter address: ");
                String address = sc.next();
                System.out.print("Enter phone number: ");
                String phoneNumber = sc.next();
                System.out.print("Enter email Id: ");
                String emailId = sc.next();
                System.out.print("Want to update more member?(Y/N): ");
                String choice = sc.next();
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,address);
                preparedStatement.setString(3,phoneNumber);
                preparedStatement.setString(4,emailId);
                preparedStatement.setInt(5,memberId);
                preparedStatement.executeUpdate();
                if (choice.equalsIgnoreCase("N"))
                {
                    break;
                }


            }
            preparedStatement.close();
            DBConnection.closeConnection(DBConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteMember() {
        Scanner sc = new Scanner(System.in);
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("DELETE FROM members WHERE memberID = ? AND name = ?");
            while (true)
            {
                System.out.println("------Deleting------");
                System.out.print("Enter Member Id for deletion: ");
                int memberId = sc.nextInt();
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Want to delete more?(Y/N): ");
                String choice = sc.next();
                preparedStatement.setInt(1,memberId);
                preparedStatement.setString(2,name);
                preparedStatement.executeUpdate();
                if (choice.equalsIgnoreCase("N"))
                {
                    break;
                }

            }
            preparedStatement.close();
            DBConnection.closeConnection(DBConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getAllMembers(){

        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("SELECT * FROM members");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int memberId = resultSet.getInt("memberID");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String emailId = resultSet.getString("emailID");
                System.out.println("Member id: "+ memberId);
                System.out.println("Name: "+ name);
                System.out.println("Address: "+address);
                System.out.println("Phone number: "+phoneNumber);
                System.out.println("Email Id: "+emailId);
                System.out.println("------------------------------");
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void getMemberByID(){
        Scanner sc = new Scanner(System.in);
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("SELECT * FROM members WHERE memberID = ?");
            System.out.print("Enter Member Id: ");
            int memberId = sc.nextInt();
            preparedStatement.setInt(1,memberId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {

                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String emailId = resultSet.getString("emailID");
                System.out.println("Member name: " + name);
                System.out.println("Address: "+ address);
                System.out.println("Phone number: "+phoneNumber);
                System.out.println("Email id: "+emailId);
                System.out.println("--------------------------------");

            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }






}
