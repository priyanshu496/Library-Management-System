import java.sql.*;
import java.util.Scanner;

public class BookDAO {
    //DAO  = Data Access Object

   public static void addBook() {
       Scanner sc = new Scanner(System.in);
       try {
           PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("INSERT INTO book(title,author,category,ISBN,quantity) VALUES(?,?,?,?,?)");
           while (true)
           {
//               System.out.print("Enter BookID: ");
//               int bookID = sc.nextInt();
               System.out.print("Enter book title: ");
               String title = sc.next();
               System.out.print("Enter book author: ");
               String author = sc.next();
               System.out.print("Enter book category: ");
               String category = sc.next();
               System.out.print("Enter ISBN: ");
               String ISBN = sc.next();
               System.out.print("Enter quantity: ");
               int quantity = sc.nextInt();
               System.out.print("Want to add more books(Y/N)");
               String choice = sc.next();
//               preparedStatement.setInt(1,bookID);
               preparedStatement.setString(1,title);
               preparedStatement.setString(2,author);
               preparedStatement.setString(3,category);
               preparedStatement.setString(4,ISBN);
               preparedStatement.setInt(5,quantity);
               ;

               preparedStatement.addBatch();
               if (choice.equalsIgnoreCase("N"))
               {
                   break;
               }

           }
           int [] arr = preparedStatement.executeBatch();
           for (int j : arr) {
               if (j == 0) {
                   System.out.println("Error!!!");
               }
           }


       } catch (SQLException e) {
           throw new RuntimeException(e);
       }




   }

   public static void updateBook() {
       Scanner sc = new Scanner(System.in);
       try {
           PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("UPDATE book SET title = ?, author = ?, category = ?, ISBN = ?, quantity = ? WHERE BookID = ?");
           while (true)
           {
               System.out.println("--------UPDATING--------");
               System.out.print("Enter BookID: ");
               int BookID = sc.nextInt();
               System.out.print("Enter new book title: ");
               String title = sc.next();
               System.out.print("Enter new author: ");
               String author = sc.next();
               System.out.print("Enter new category: ");
               String category = sc.next();
               System.out.print("Enter new ISBN: ");
               String ISBN = sc.next();
               System.out.print("Enter new quantity: ");
               int quantity = sc.nextInt();
               System.out.print("Want to update more books?(Y/N): ");
               String choice = sc.next();
               preparedStatement.setString(1,title);
               preparedStatement.setString(2,author);
               preparedStatement.setString(3,category);
               preparedStatement.setString(4,ISBN);
               preparedStatement.setInt(5,quantity);
               preparedStatement.setInt(6,BookID);
               preparedStatement.executeUpdate();
               if (choice.equalsIgnoreCase("N"))
               {
                   break;
               }
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }



   }

   public static void deleteBook(){
       Scanner sc = new Scanner(System.in);
       try {
           PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("DELETE FROM book WHERE BookID = ? AND title = ?");
           while (true)
           {
               System.out.println("------Deleting a Book------");
               System.out.print("Enter BookID: ");
               int BookID = sc.nextInt();
               System.out.print("Enter book title: ");
               String title = sc.next();
               preparedStatement.setInt(1,BookID);
               preparedStatement.setString(2,title);
               preparedStatement.executeUpdate();
               System.out.print("Want to Delete more books?(Y/N): ");
               String choice = sc.next();
               if (choice.equalsIgnoreCase("N"))
               {
                   System.out.println("Deleted");
                   break;
               }
           }

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

   public static void getAllBooks() {

       try {
           PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("SELECT * FROM book");
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               int BookID = resultSet.getInt("BookID");
               String title = resultSet.getString("title");
               String author = resultSet.getString("author");
               String category = resultSet.getString("category");
               String ISBN = resultSet.getString("ISBN");
               int quantity = resultSet.getInt("quantity");
               System.out.println("BookID: " + BookID);
               System.out.println("Title: "+ title);
               System.out.println("Author: "+ author);
               System.out.println("Category: "+ category);
               System.out.println("ISBN: "+ ISBN);
               System.out.println("Quantity: "+quantity);
               System.out.println("-----------------------------");

           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }



   }

   public static void getBookByID(){
       Scanner sc = new Scanner(System.in);
       try {
           PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("SELECT * FROM book WHERE BookID = ?");
           System.out.print("Enter BookID: ");
           int BookId = sc.nextInt();
           preparedStatement.setInt(1,BookId);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next())
           {

               String title = resultSet.getString("title");
               String author = resultSet.getString("author");
               String category =resultSet.getString("category");
               String ISBN = resultSet.getString("ISBN");
               int quantity = resultSet.getInt("quantity");
               System.out.println("Book title: "+ title);
               System.out.println("Author: "+ author);
               System.out.println("Category: "+category);
               System.out.println("ISBN: "+ISBN);
               System.out.println("Quantity: "+quantity);
               System.out.println("--------------------------");

           }
//           int []arr = preparedStatement.execute();
//           for (int j : arr) {
//               if (j == 0) {
//                   System.out.println("Not Exists!!!");
//               }
//           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }




}
