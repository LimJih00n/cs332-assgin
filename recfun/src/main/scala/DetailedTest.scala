package recfun

object DetailedTest extends App {
  
  println("=== DETAILED TESTING OF ALL FUNCTIONS ===\n")
  
  // Test Pascal's Triangle
  println("1. PASCAL'S TRIANGLE TESTS")
  println("-" * 40)
  
  // Print first 5 rows to visualize
  println("Visual representation:")
  for (row <- 0 to 4) {
    print(" " * (4 - row))
    for (col <- 0 to row) {
      print(Main.pascal(col, row) + " ")
    }
    println()
  }
  println()
  
  // Test specific values
  val pascalTests = List(
    ((0, 0), 1),
    ((0, 1), 1),
    ((1, 1), 1),
    ((0, 2), 1),
    ((1, 2), 2),
    ((2, 2), 1),
    ((0, 3), 1),
    ((1, 3), 3),
    ((2, 3), 3),
    ((3, 3), 1),
    ((2, 4), 6),
    ((3, 5), 10)
  )
  
  pascalTests.foreach { case ((c, r), expected) =>
    val result = Main.pascal(c, r)
    val status = if (result == expected) "✓" else "✗"
    println(f"pascal($c, $r) = $result (expected: $expected) $status")
  }
  println()
  
  // Test Parentheses Balancing
  println("2. PARENTHESES BALANCING TESTS")
  println("-" * 40)
  
  val balanceTests = List(
    ("(if (zero? x) max (/ 1 x))", true),
    ("I told him (that it's not (yet) done). (But he wasn't listening)", true),
    (":-)", false),
    ("())(", false),
    ("", true),  // empty string
    ("()", true),
    (")(", false),
    ("((()))", true),
    ("((())", false),
    ("()()())", false),
    ("(()()())", true),
    ("(()(())())", true),
    ("(()(())()))", false)
  )
  
  balanceTests.foreach { case (str, expected) =>
    val result = Main.balance(str.toList)
    val status = if (result == expected) "✓" else "✗"
    val shortStr = if (str.length > 30) str.take(27) + "..." else str
    println(f"'$shortStr%-35s' balanced: $result%-5s (expected: $expected%-5s) $status")
  }
  println()
  
  // Test Counting Change
  println("3. COUNTING CHANGE TESTS")
  println("-" * 40)
  
  val changeTests = List(
    ((4, List(1, 2)), 3),  // 1+1+1+1, 1+1+2, 2+2
    ((10, List(5, 2, 3)), 4), // 5+5, 5+2+3, 2+2+2+2+2, 2+2+3+3
    ((0, List(1, 2)), 1),  // only one way: use no coins
    ((7, List()), 0),  // no coins, can't make change
    ((5, List(1)), 1),  // only 1+1+1+1+1
    ((6, List(1, 2, 3)), 7),  // many combinations
    ((100, List(1, 5, 10, 25, 50)), 292),  // US coins
    ((-1, List(1, 2)), 0)  // negative amount
  )
  
  changeTests.foreach { case ((money, coins), expected) =>
    val result = Main.countChange(money, coins)
    val status = if (result == expected) "✓" else "✗"
    val coinsStr = coins.mkString("[", ", ", "]")
    println(f"countChange($money%-3d, $coinsStr%-20s) = $result%-4d (expected: $expected%-4d) $status")
  }
  
  println("\n=== TESTING COMPLETE ===")
  
  // Summary
  val pascalPassed = pascalTests.forall { case ((c, r), exp) => Main.pascal(c, r) == exp }
  val balancePassed = balanceTests.forall { case (str, exp) => Main.balance(str.toList) == exp }
  val changePassed = changeTests.forall { case ((m, c), exp) => Main.countChange(m, c) == exp }
  
  println(s"\nPascal's Triangle: ${if (pascalPassed) "✓ PASSED" else "✗ FAILED"}")
  println(s"Parentheses Balancing: ${if (balancePassed) "✓ PASSED" else "✗ FAILED"}")
  println(s"Counting Change: ${if (changePassed) "✓ PASSED" else "✗ FAILED"}")
  
  if (pascalPassed && balancePassed && changePassed) {
    println("\n🎉 ALL TESTS PASSED! 🎉")
  }
}