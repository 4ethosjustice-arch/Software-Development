public class TestMyInteger {
    public static void main(String[] args) {
        MyInteger n1 = new MyInteger(7);
        MyInteger n2 = new MyInteger(28);

        System.out.println("n1 value: " + n1.getValue());
        System.out.println("n1 isEven? " + n1.isEven());
        System.out.println("n1 isOdd? " + n1.isOdd());
        System.out.println("n1 isPrime? " + n1.isPrime());

        System.out.println();

        System.out.println("n2 value: " + n2.getValue());
        System.out.println("n2 isEven? " + n2.isEven());
        System.out.println("n2 isOdd? " + n2.isOdd());
        System.out.println("n2 isPrime? " + n2.isPrime());

        System.out.println();

        System.out.println("isEven(16)? " + MyInteger.isEven(16));
        System.out.println("isOdd(19)? " + MyInteger.isOdd(19));
        System.out.println("isPrime(29)? " + MyInteger.isPrime(29));

        System.out.println();

        System.out.println("isEven(n1)? " + MyInteger.isEven(n1));
        System.out.println("isOdd(n1)? " + MyInteger.isOdd(n1));
        System.out.println("isPrime(n1)? " + MyInteger.isPrime(n1));

        System.out.println();

        System.out.println("n1 equals 7? " + n1.equals(7));
        System.out.println("n1 equals n2? " + n1.equals(n2));

        System.out.println();

        char[] chars = {'1', '2', '3', '4'};
        System.out.println("parseInt(chars {'1','2','3','4'}): " + MyInteger.parseInt(chars));
        System.out.println("parseInt(\"5678\"): " + MyInteger.parseInt("5678"));
    }
}

class MyInteger {
    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEven() {
        return value % 2 == 0;
    }

    public boolean isOdd() {
        return value % 2 != 0;
    }

    public boolean isPrime() {
        if (value <= 1) return false;
        for (int i = 2; i <= Math.sqrt(value); i++) {
            if (value % i == 0) return false;
        }
        return true;
    }

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static boolean isOdd(int n) {
        return n % 2 != 0;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean isEven(MyInteger n) {
        return n.getValue() % 2 == 0;
    }

    public static boolean isOdd(MyInteger n) {
        return n.getValue() % 2 != 0;
    }

    public static boolean isPrime(MyInteger n) {
        return n.isPrime();
    }

    public boolean equals(int n) {
        return value == n;
    }

    public boolean equals(MyInteger n) {
        return value == n.getValue();
    }

    public static int parseInt(char[] chars) {
        int result = 0;
        for (char c : chars) {
            result = result * 10 + (c - '0');
        }
        return result;
    }

    public static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}