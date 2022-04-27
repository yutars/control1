package numbertostring;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;


public class numberToString {
    private BigDecimal digits;
    private boolean isPositive;

    public numberToString(long inputedDigits) {
        this.isPositive = (inputedDigits >= 0);
        this.digits = new BigDecimal( Math.abs(inputedDigits));
    }
    public numberToString(double inputedDigits) {
        this.isPositive = (inputedDigits >= 0);
        this.digits = new BigDecimal( Math.abs(inputedDigits));
    }
    

    public String toString() {
        String[][] firstDozen = {
            {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
            {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
        };
        String[] hundreds = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        String[] secondDozen = {"", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать", "двадцать"};
        String[] dozens = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        String[][] thousands = {
            {"тысяча", "тысячи", "тысяч", "1"},
            {"миллион", "миллиона", "миллионов", "0"},
            {"миллиард", "миллиарда", "миллиардов", "0"},
            {"триллион", "триллиона", "триллионов", "0"},
            //сюда можно добавить разряды
        };
        long longDigits = digits.longValue();
        long num = longDigits;
        ArrayList segments = new ArrayList();
        while(num > 999) {
            long seg = num / 1000;
            segments.add( num - (seg * 1000) );
            num = seg;
        }
        segments.add( num );
        Collections.reverse(segments);
        String o = "";
        if (num == 0) 
        {
            o = "ноль";
        }
        else
        {
            int lev = segments.size();
            for (int i = 0; i < segments.size(); i++)
            {
                int sexi = (int)Integer.valueOf( thousands[lev][3] );
                int ri = (int)Integer.valueOf( segments.get(i).toString() );
                if (ri == 0 && lev > 1)
                    lev--;
                else
                {
                    String rs = String.valueOf(ri); 
                    if (rs.length() == 1) rs = "00" + rs;
                    if (rs.length() == 2) rs = "0" + rs;
                    int r1 = (int)Integer.valueOf( rs.substring(0, 1) );
                    int r2 = (int)Integer.valueOf( rs.substring(1, 2) );
                    int r3 = (int)Integer.valueOf( rs.substring(2, 3) );
                    int r22= (int)Integer.valueOf( rs.substring(1, 3) );
                    if (ri>99) o += hundreds[r1] + " ";
                    if (r22 > 20)
                    {
                        o += dozens[r2] + " ";
                        o += firstDozen[ sexi ][r3]+" ";
                    }
                    else {
                        if (r22 > 9) 
                            o += secondDozen[r22 - 9]+" ";
                        else 
                            o += firstDozen[ sexi ][r3]+" ";
                    }
                    if(lev>1)
                        o += morph(ri, thousands[lev - 2][ 0], thousands[lev - 2][1], thousands[lev - 2][2]) + " ";
                    lev--;
                }
            }
        }
        if (!isPositive)
            return "минус "+o;
        else
            return o;
    }
    private static String morph(long n, String f1, String f2, String f5) {
        n = Math.abs(n) % 100;
        String rez = f5;
        long n1 = n % 10;
        if (n > 10 && n < 20) rez = f5;
        if (n1 > 1 && n1 < 5) rez = f2;
        if (n1 == 1) rez = f1;
        return rez;
    }
}