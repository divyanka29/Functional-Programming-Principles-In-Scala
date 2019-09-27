package testList

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.Head")
  def tail: Nothing = throw new NoSuchElementException("Nil.Head")
}

//Create a list with a single element
def singleton[T](elem: T) = new Con[T](elem, new Nil[T])

