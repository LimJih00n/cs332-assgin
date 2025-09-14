package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  test("singletonSet(1) contains 1") {
    new TestSets {
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains only common elements") {
    new TestSets {
      val s12 = union(s1, s2)
      val s23 = union(s2, s3)
      val s = intersect(s12, s23)
      assert(!contains(s, 1), "Intersect 1")
      assert(contains(s, 2), "Intersect 2")
      assert(!contains(s, 3), "Intersect 3")
    }
  }

  test("diff contains elements in s but not in t") {
    new TestSets {
      val s12 = union(s1, s2)
      val s23 = union(s2, s3)
      val s = diff(s12, s23)
      assert(contains(s, 1), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
      assert(!contains(s, 3), "Diff 3")
    }
  }

  test("filter selects elements that satisfy predicate") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)
      val s = filter(s123, x => x > 1)
      assert(!contains(s, 1), "Filter 1")
      assert(contains(s, 2), "Filter 2")
      assert(contains(s, 3), "Filter 3")
    }
  }

  test("forall checks if all elements satisfy predicate") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)
      assert(forall(s123, x => x > 0), "All positive")
      assert(!forall(s123, x => x > 1), "Not all > 1")
      assert(forall(s123, x => x < 10), "All < 10")
    }
  }

  test("exists checks if at least one element satisfies predicate") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)
      assert(exists(s123, x => x == 2), "2 exists")
      assert(!exists(s123, x => x == 4), "4 doesn't exist")
      assert(exists(s123, x => x > 2), "Element > 2 exists")
    }
  }

  test("map transforms set by applying function") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)
      val s = map(s123, x => x * 2)
      assert(contains(s, 2), "Map: 1 -> 2")
      assert(contains(s, 4), "Map: 2 -> 4")
      assert(contains(s, 6), "Map: 3 -> 6")
      assert(!contains(s, 1), "Map: 1 not in result")
      assert(!contains(s, 3), "Map: 3 not in result")
    }
  }
}