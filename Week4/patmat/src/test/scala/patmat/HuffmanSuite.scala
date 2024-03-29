package patmat

import java.util.Locale.LanguageRange

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("times(\"hello\")") {
    assert(times(List('h', 'e', 'l', 'l', 'o')) ===  List(('o',1), ('l',2), ('h',1), ('e',1)))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test( testName = "decode" ) {
    val tree: CodeTree = Fork(Leaf('a', 3), Fork(Leaf('b', 2), Leaf('c', 1), List('b', 'c'), 3), List('a', 'b', 'c'), 6)
    val secret: List[Bit] = List(1,0,0,1,1)
    assert(decode(tree, secret) === List('b', 'a', 'c'))
  }

  test( testName = "encode" ) {
    val tree: CodeTree = Fork(Leaf('a', 3), Fork(Leaf('b', 2), Leaf('c', 1), List('b', 'c'), 3), List('a', 'b', 'c'), 6)
    assert(encode(tree)(List('b', 'a', 'c')) === List[Bit](1,0,0,1,1))
  }


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

}
