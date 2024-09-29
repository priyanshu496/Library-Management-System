import java.sql.*;
import java.sql.Date.*;
import java.util.Scanner;
import java.time.LocalDate;


public class TransactionDAO {//DAO  = Data Access Object

//    private static int doesHaveFine(Connection connection, int memberID) {
//        try {
//            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("SELECT fine FROM Transaction WHERE memberID = ?");
//            preparedStatement.setInt(1, memberID);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            return resultSet.getInt("fine");
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }


    public static void issueBook() {

        try {


            Scanner sc = new Scanner(System.in);
            Connection connection = DBConnection.getConnection();
            //connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Transaction(memberID, BookID, issueDate) VALUES (?,?,?)");
            System.out.println("------ISSUE BOOK------");
            System.out.print("Enter Member Id: ");
            int memberID = sc.nextInt();
            System.out.print("Enter Book id: ");
            int bookId = sc.nextInt();
            LocalDate localDate = LocalDate.now();
            Date issueDate = Date.valueOf(localDate);
//                System.out.print("Want to issue more books?(Y/N): ");
//                String choice = sc.next();
            preparedStatement.setInt(1, memberID);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setDate(3, issueDate);
            preparedStatement.executeUpdate();
//            if (doesHaveFine(connection,memberID)==0)
//            {
//                //connection.commit();
//
//            }

            System.out.print("Want to issue more books?(Y/N): ");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("y")) {
                issueBook();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//            DBConnection.closeConnection(DBConnection.getConnection());
//
//
//        }



    }

        public static void returnBook () {
            try (Scanner sc = new Scanner(System.in)) {
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Transaction SET returnDate = ? WHERE transactionID = ? AND BookID = ?");
                System.out.print("Enter transaction id: ");
                int transactionId = sc.nextInt();
                System.out.print("Enter Member ID: ");
                int BookID = sc.nextInt();
                LocalDate localDate = LocalDate.now();
                Date returnDate = Date.valueOf(localDate);
                preparedStatement.setDate(1, returnDate);
                preparedStatement.setInt(2, transactionId);
                preparedStatement.setInt(3, BookID);
                preparedStatement.executeUpdate();
                System.out.println("Book returned");
                preparedStatement.close();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static void calculateFine () {
            Connection connection = DBConnection.getConnection();
            try {
                Scanner sc = new Scanner(System.in);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(fine) as totalFine FROM Transaction WHERE memberID = ?");
                System.out.print("Enter memberID: ");
                int memberID = sc.nextInt();
                preparedStatement.setInt(1,memberID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int totalFine = resultSet.getInt("totalFine");
                    System.out.println("Your fine is: " + totalFine);
                    if (totalFine>0)
                    {
                        System.out.println("You can not issue new book");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
