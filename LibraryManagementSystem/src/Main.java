
import java.util.Scanner;


public class Main  {
    public static void menu() {
        String op1 = "1. Books";
        String op2 = "2. Members";
        String op3 = "3. Issue Book";
        String op4 = "4. Return Book";
        String op5 = "5. Calculate fine";
        String op0 = "6. Exit";


        Scanner sc = new Scanner(System.in);
        System.out.println("------MAIN MENU------");
        System.out.println(op1);
        System.out.println(op2);
        System.out.println(op3);
        System.out.println(op4);
        System.out.println(op5);
        System.out.println(op0);

        String select = sc.next();

        switch (select) {
            case "1" -> {
                System.out.println("------Book Menu------");
                String op6 = "1. Add Book";
                String op7 = "2. Delete Book";
                String op8 = "3. Update Book";
                String op9 = "4. Get All Books";
                String op10 = "5. Get Book By ID";
                System.out.println(op6);
                System.out.println(op7);
                System.out.println(op8);
                System.out.println(op9);
                System.out.println(op10);
                String select1 = sc.next();
                switch (select1) {
                    case "1" -> BookDAO.addBook();
                    case "2" -> BookDAO.deleteBook();
                    case "3" -> BookDAO.updateBook();
                    case "4" -> BookDAO.getAllBooks();
                    case "5" -> BookDAO.getBookByID();
                    default -> System.out.println("Wrong input");
                }
            }
            case "2" -> {
                System.out.println("------Member Menu------");
                System.out.println("1. Add Member");
                System.out.println("2. Update Member");
                System.out.println("3. Delete Member");
                System.out.println("4. Get All Members");
                System.out.println("5. Get Member By Id");
                String select2 = sc.next();
                switch (select2) {
                    case "1" -> MemberDAO.addMember();
                    case "2" -> MemberDAO.updateMember();
                    case "3" -> MemberDAO.deleteMember();
                    case "4" -> MemberDAO.getAllMembers();
                    case "5" -> MemberDAO.getMemberByID();
                    default -> System.out.println("Wrong input");
                }
            }
            case "3" -> TransactionDAO.issueBook();
            case "4" -> TransactionDAO.returnBook();
            case "5" -> TransactionDAO.calculateFine();
            case "6" -> {
                break;
            }
        }
    }
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

      while (true)
      {

          menu();
          System.out.print("Go Back to main menu(Y/N): ");
          String choice = sc.nextLine();
          if (choice.equalsIgnoreCase("y"))
          {
              menu();
          }
          else
              break;
      }




    }
}
