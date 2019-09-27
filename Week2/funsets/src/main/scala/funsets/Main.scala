package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))

  val elems = List(1, 2, 3)
  println(contains(multipleSet(elems), 1))
  println(contains(multipleSet(elems), 5))
  printSet(map(multipleSet(elems), x => x * x))
}
