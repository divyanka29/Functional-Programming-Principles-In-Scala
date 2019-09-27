class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be non zero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b:Int): Int = if(b == 0) a else gcd(b, a % b)
  def numer = x
  def denom = y

  def < (other: Rational) = numer * other.denom < other.numer * denom

  def max(other: Rational) = {
    if(this < other) other
    else this
  }

  def add(other: Rational) = {
    new Rational(numer * other.denom + other.numer + denom, denom * other.denom)
  }

  override def toString: String = numer / gcd(numer, denom) + "/" + denom / gcd(numer, denom)

  def neg: Rational = {
    new Rational(-numer, denom)
  }

  def subtract(other: Rational) = {
    add(other.neg)
  }
}

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val t = y.subtract(new Rational(1, 2))
x < y
y.max(x)