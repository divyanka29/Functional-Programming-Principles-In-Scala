package recfun

import scala.collection.mutable
import scala.collection.mutable.Stack

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   */
  var parenthesesStack: mutable.Stack[Char] = Stack[Char]()

  def balance(chars: List[Char]): Boolean = {
    if (chars.isEmpty) parenthesesStack.isEmpty
    else {
      if(chars.head == '(') parenthesesStack.push(chars.head)
      else if (chars.head == ')') {
        if(parenthesesStack.isEmpty) return false
        else {
          if(parenthesesStack.top == '(') parenthesesStack.pop()
          else return false
        }
      }
      balance(chars.tail)
    }
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def count(coins: List[Int], m: Int, n: Int) : Int = {
      if(n == 0) return 1
      if(n < 0) return 0
      if(m <= 0 && n >= 1) return 0
      return count(coins, m-1, n) + count(coins, m, n - coins.apply(m-1))
    }
    count(coins, coins.length, money)
  }

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a+1, f(a) + acc)
    }
    loop(a, 0)
  }

}
