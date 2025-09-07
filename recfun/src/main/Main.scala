package recfun

object Main extends App {
  
  /**
   * Exercise 1: Pascal's Triangle
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }
  
  /**
   * Exercise 2: Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceHelper(chars: List[Char], openCount: Int): Boolean = {
      if (chars.isEmpty) openCount == 0
      else if (openCount < 0) false
      else {
        val head = chars.head
        val newCount = head match {
          case '(' => openCount + 1
          case ')' => openCount - 1
          case _ => openCount
        }
        balanceHelper(chars.tail, newCount)
      }
    }
    balanceHelper(chars, 0)
  }
  
  /**
   * Exercise 3: Counting Change
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0 || coins.isEmpty) 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}