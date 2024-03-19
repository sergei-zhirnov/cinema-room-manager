package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        char[][] seatsArr = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                seatsArr[i][j] = 'S';
            }
        }


        int choice = -1;
        while (choice != 0) {
            printMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 0:
                   break;
                case 1:
                    printSeats(seatsArr);
                    break;
                case 2:
                    sellTicket(seatsArr);
                    break;
                case 3:
                    printStatistics(seatsArr);
                    break;
                default:
                    System.out.println("Wrong input! Try again!");
                    break;
            }
        }

    }

    public static void printMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.println();
    }

    public static void printSeats(char[][] arr) {
        System.out.println("Cinema:");
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= arr[0].length; j++) {
                if (i == 0 && j != 0) {
                    System.out.print(j + " ");
                } else if (j == 0 && i != 0) {
                    System.out.print(i + " ");
                } else if (j == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(arr[i-1][j-1] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void sellTicket(char[][] seatsArr) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Enter a row number:");
            int rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNum = scanner.nextInt();
            int ticketPrice;

            if (rowNum > seatsArr.length || seatNum > seatsArr[0].length) {
                System.out.println("Wrong input!\n");
            } else if (seatsArr[rowNum - 1][seatNum - 1] == 'B') {
                System.out.println("That ticket has already been purchased!\n");
            } else if (seatsArr[rowNum - 1][seatNum - 1] == 'S') {
                ticketPrice = calculateTicketPrice(seatsArr, rowNum, seatNum);
                System.out.println("Ticket price: $" + ticketPrice);
                seatsArr[rowNum - 1][seatNum - 1] = 'B';
                break;
            }
        }


    }

    public static int calculateIncome(char[][] seatsArr, String incomeType) {
        int income = 0;
        int rows = seatsArr.length;
        int seats = seatsArr[0].length;
        if (rows * seats <= 60 && Objects.equals(incomeType, "TOTAL")) {
            income = rows * seats * 10;
        } else if (rows * seats > 60 && Objects.equals(incomeType, "TOTAL")) {
            int frontHalf = rows / 2;
            income = frontHalf * seats * 10 + (rows - frontHalf) * seats * 8;
        } else if (rows * seats <= 60 && Objects.equals(incomeType, "CURRENT")) {
            income = calculateSoldTickets(seatsArr, "TOTAL") * 10;
        } else if (rows * seats > 60 && Objects.equals(incomeType, "CURRENT")) {
            income = calculateSoldTickets(seatsArr, "FRONT_HALF") * 10 +
                    calculateSoldTickets(seatsArr, "BACK_HALF") * 8;
        }
        return income;
    }



    public static int calculateTicketPrice(char[][] seatsArr, int rowNum, int seatNum) {
        int ticketPrice = 0;
        int rows = seatsArr.length;
        int seats = seatsArr[0].length;
        if (rowNum > rows || seatNum > seats) {
            System.out.println("ERROR! NO SUCH ROW / SEAT");
            return ticketPrice;
        } else if (rows * seats <= 60 || rowNum <= rows / 2) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        return ticketPrice;
    }

    public static int calculateSoldTickets(char[][] seatsArr, String calcType) {
        int rows = seatsArr.length;
        int seats = seatsArr[0].length;
        int startRow = Objects.equals(calcType, "BACK_HALF") ? rows - 1 - rows / 2 : 0;
        int finRow = Objects.equals(calcType, "FRONT_HALF") ? (rows / 2) - 1 : rows - 1;

        int soldTickets = 0;
        for (int i = startRow; i <= finRow; i++) {
            for (int j = 0; j < seats; j++) {
                if (seatsArr[i][j] == 'B') {
                    soldTickets += 1;
                }
            }
        }

        return soldTickets;
    }

    public static void printStatistics(char[][] seatsArr) {
        int rows = seatsArr.length;
        int seats = seatsArr[0].length;

        // calculate purchased tickets
        int totalSoldTickets = calculateSoldTickets(seatsArr, "TOTAL");
        System.out.println("Number of purchased tickets: " + totalSoldTickets);

        //calculate percentage
        double soldTicketsRatio = (double) (100 * totalSoldTickets) / (rows * seats);
        System.out.printf("Percentage: %.2f%%\n", soldTicketsRatio);

        //calculate current income
        System.out.printf("Current income: $%d\n", calculateIncome(seatsArr,"CURRENT"));

        //calculate total income
        System.out.printf("Total income: $%d\n", calculateIncome(seatsArr, "TOTAL"));
    }

}