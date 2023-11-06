import java.util.Scanner;

public class CreditCardValidator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a credit card number as a long integer: ");
        long number = input.nextLong();

        System.out.println(number + (isValid(number) ? " is valid" : " is invalid"));
    }

    /** Return true if the card number is valid */
    public static boolean isValid(long number) {
        return (getSize(number) >= 13 && getSize(number) <= 16) &&
                (prefixMatched(number, 4) || prefixMatched(number, 5) ||
                        prefixMatched(number, 37) || prefixMatched(number, 6))
                &&
                ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
    }

    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        long temp = number;
        boolean doubleDigit = false;
        while (temp > 0) {
            int digit = (int) (temp % 10);
            if (doubleDigit) {
                sum += getDigit(digit * 2);
            }
            doubleDigit = !doubleDigit;
            temp /= 10;
        }
        return sum;
    }

    /**
     * Return this number if it is a single digit, otherwise,
     * return the sum of the two digits
     */
    public static int getDigit(int number) {
        if (number < 9) {
            return number;
        } else {
            return number / 10 + number % 10;
        }
    }

    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        long temp = number;
        boolean doubleDigit = false;
        while (temp > 0) {
            int digit = (int) (temp % 10);
            if (!doubleDigit) {
                sum += digit;
            }
            doubleDigit = !doubleDigit;
            temp /= 10;
        }
        return sum;
    }

    /** Return true if the number d is a prefix for number */
    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    /** Return the number of digits in d */
    public static int getSize(long d) {
        int length = 0;
        while (d > 0) {
            length++;
            d /= 10;
        }
        return length;
    }

    /**
     * Return the first k number of digits from number. If the
     * number of digits in number is less than k, return number.
     */
    public static long getPrefix(long number, int k) {
        if (getSize(number) < k) {
            return number;
        } else {
            long size = getSize(number) - k;
            return (long) (number / Math.pow(10, size));
        }
    }
}
