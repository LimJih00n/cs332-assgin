package recfun

object MainTest extends App {
  import Main._
  
  println("Testing Pascal's Triangle:")
  println(s"pascal(0,2) = ${pascal(0,2)} (expected: 1)")
  println(s"pascal(1,2) = ${pascal(1,2)} (expected: 2)")
  println(s"pascal(1,3) = ${pascal(1,3)} (expected: 3)")
  println(s"pascal(2,4) = ${pascal(2,4)} (expected: 6)")
  println()
  
  println("Testing Parentheses Balancing:")
  val test1 = "(if (zero? x) max (/ 1 x))".toList
  println(s"'(if (zero? x) max (/ 1 x))' balanced: ${balance(test1)} (expected: true)")
  
  val test2 = "I told him (that it's not (yet) done). (But he wasn't listening)".toList
  println(s"Complex nested parentheses balanced: ${balance(test2)} (expected: true)")
  
  val test3 = ":-)".toList
  println(s"':-)'balanced: ${balance(test3)} (expected: false)")
  
  val test4 = "())(".toList
  println(s"'())(' balanced: ${balance(test4)} (expected: false)")
  println()
  
  println("Testing Counting Change:")
  println(s"countChange(4, List(1,2)) = ${countChange(4, List(1,2))} (expected: 3)")
  println(s"countChange(10, List(5,2,3)) = ${countChange(10, List(5,2,3))} (expected: 4)")
  println(s"countChange(0, List(1,2)) = ${countChange(0, List(1,2))} (expected: 1)")
  println(s"countChange(7, List()) = ${countChange(7, List())} (expected: 0)")
}