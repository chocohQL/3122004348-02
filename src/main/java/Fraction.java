/**
 * 分数
 *
 * @author chocoh
 */
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Fraction add(Fraction other) {
        return new Fraction(this.numerator * other.denominator + other.numerator * this.denominator,
                this.denominator * other.denominator);
    }

    public Fraction subtract(Fraction other) {
        return new Fraction(this.numerator * other.denominator - other.numerator * this.denominator,
                this.denominator * other.denominator);
    }

    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator,
                this.denominator * other.denominator);
    }

    public Fraction divide(Fraction other) {
        return new Fraction(this.numerator * other.denominator,
                this.denominator * other.numerator);
    }

    private void simplify() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int compareTo(Fraction other) {
        return Integer.compare(this.numerator * other.denominator, other.numerator * this.denominator);
    }

    @Override
    public String toString() {
        if (numerator == 0) {
            return "0";
        }
        if (Math.abs(numerator) < denominator) {
            return numerator + "/" + denominator;
        }
        int intPart = numerator / denominator;
        int numerator1 = Math.abs(numerator) % denominator;
        return numerator1 == 0 ? String.valueOf(intPart) : intPart + "'" + numerator1 + "/" + denominator;
    }
}
