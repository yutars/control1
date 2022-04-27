package numbertostring;

import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int digits = in.nextInt();
        numberToString miw = new numberToString(digits);
        System.out.println("Строковое представление числа: " + miw);
    }
}
