package cs320

import Macros._

class Spec extends SpecBase {

  val run = Implementation.run _

  test(run("(31 + 7)"), "38")
  test(run("(((f) => f)(31) + ((v) => v)(7))"), "38")
  test(run("(31 * 7)"), "217")
  test(run("(((n) => n)(31) * ((q) => q)(7))"), "217")
  test(run("(31 / 7)"), "4")
  test(run("(((m) => m)(31) / ((g) => g)(7))"), "4")
  test(run("(31 / (-7))"), "-4")
  test(run("((-31) / 7)"), "-4")
  test(run("((-31) / (-7))"), "4")
  test(run("(31 % 7)"), "3")
  test(run("(((n) => n)(31) % ((c) => c)(7))"), "3")
  test(run("(31 % (-7))"), "3")
  test(run("((-31) % 7)"), "-3")
  test(run("((-31) % (-7))"), "-3")
  test(run("(7 == 7)"), "true")
  test(run("(((l) => l)(7) == ((s) => s)(7))"), "true")
  test(run("(7 == 31)"), "false")
  test(run("(7 < 7)"), "false")
  test(run("(((c) => c)(7) < ((d) => d)(7))"), "false")
  test(run("(7 < 31)"), "true")
  test(run("{ if (true) 7 else 31 }"), "7")
  test(run("{ if (((c) => c)(true)) 7 else 31 }"), "7")
  test(run("{ if (true) 7 else u }"), "7")
  test(run("{ if (false) 7 else 31 }"), "31")
  test(run("(7, 31)"), "(7, 31)")
  test(run("((7, 31), (7, 31))"), "((7, 31), (7, 31))")
  test(run("(7, 31)._1"), "7")
  test(run("((j) => j)((7, 31))._1"), "7")
  test(run("(7, 31)._2"), "31")
  test(run("(7 :: Nil)"), "(7 :: Nil)")
  test(run("(31 :: ((l) => l)((7 :: Nil)))"), "(31 :: (7 :: Nil))")
  test(run("(Nil :: Nil)"), "(Nil :: Nil)")
  test(run("Nil.isEmpty"), "true")
  test(run("((i) => i)(Nil).isEmpty"), "true")
  test(run("(7 :: Nil).isEmpty"), "false")
  test(run("((t) => t)((7 :: Nil)).isEmpty"), "false")
  test(run("(7 :: Nil).head"), "7")
  test(run("((c) => c)((7 :: Nil)).head"), "7")
  test(run("(31 :: (7 :: Nil)).tail"), "(7 :: Nil)")
  test(run("((n) => n)((31 :: (7 :: Nil))).tail"), "(7 :: Nil)")
  test(run("{ val u = 7; u }"), "7")
  test(run("{ val u = true; { val u = 31; u } }"), "31")
  test(run("{ val u = 7; { val c = u; c } }"), "7")
  test(run("(() => 7)()"), "7")
  test(run("((u, c) => (u + c))(7, 31)"), "38")
  test(run("((u) => ((c) => (u + c)))(7)(31)"), "38")
  test(run("{ val u = 7; { def u() = u; u } }"), "<function>")
  test(run("{ val u = 7; { def u() = u; u() } }"), "<function>")
  test(run("{ val c = 7; { def u() = c; u() } }"), "7")
  test(run("{ def l(m) = { if ((m < 3)) (m + 3) else (l((m + (-2))) + (m + 3)) }; l(12) }"), "60")
  test(run("{ def w(r) = { if ((r < 2)) (r + 1) else (j((r + (-2)), (r + 4), (r + 5)) + (r + 4)) }; def j(m, a, y) = { if ((m < 1)) (a + 4) else (w((m + (-1))) + (m + 1)) }; j(14, 12, 14) }"), "97")
  test(run("{ def c(d) = { if ((d < 1)) (d + 3) else (q((d + (-1))) + (d + 5)) }; def v(d, g, j) = { if ((g < 1)) (g + 2) else (c((g + (-2))) + (d + 1)) }; def q(a) = { if ((a < 3)) (a + 2) else (f((a + 1), (a + (-2)), (a + 3)) + (a + 2)) }; def n(e) = { if ((e < 2)) (e + 5) else (v((e + 5), (e + (-2)), (e + 4)) + (e + 2)) }; def f(l, u, h) = { if ((u < 2)) (u + 3) else (n((u + (-1))) + (u + 5)) }; n(13) }"), "95")
  test(run("(7.isInstanceOf[Int], false.isInstanceOf[Int], (7, 31).isInstanceOf[Int], Nil.isInstanceOf[Int], (7 :: Nil).isInstanceOf[Int], ((w) => w).isInstanceOf[Int])"), "(true, false, false, false, false, false)")
  test(run("(7.isInstanceOf[Boolean], true.isInstanceOf[Boolean], (7, 31).isInstanceOf[Boolean], Nil.isInstanceOf[Boolean], (7 :: Nil).isInstanceOf[Boolean], ((s) => s).isInstanceOf[Boolean])"), "(false, true, false, false, false, false)")
  test(run("(7.isInstanceOf[Tuple], false.isInstanceOf[Tuple], (7, 31).isInstanceOf[Tuple], Nil.isInstanceOf[Tuple], (7 :: Nil).isInstanceOf[Tuple], ((r) => r).isInstanceOf[Tuple])"), "(false, false, true, false, false, false)")
  test(run("(7.isInstanceOf[List], true.isInstanceOf[List], (7, 31).isInstanceOf[List], Nil.isInstanceOf[List], (7 :: Nil).isInstanceOf[List], ((f) => f).isInstanceOf[List])"), "(false, false, false, true, true, false)")
  test(run("(7.isInstanceOf[Function], true.isInstanceOf[Function], (7, 31).isInstanceOf[Function], Nil.isInstanceOf[Function], (7 :: Nil).isInstanceOf[Function], ((h) => h).isInstanceOf[Function])"), "(false, false, false, false, false, true)")
  test(run("(((s) => s)(7).isInstanceOf[Int], ((v) => v)(true).isInstanceOf[Int])"), "(true, false)")
  testExc(run("{ val u = 7; c }"), "")
  testExc(run("(7 + true)"), "")
  testExc(run("(7 * false)"), "")
  testExc(run("(7 / false)"), "")
  testExc(run("(7 / ((m) => m)(0))"), "")
  testExc(run("(7 % false)"), "")
  testExc(run("(7 % ((b) => b)(0))"), "")
  testExc(run("(7 == false)"), "")
  testExc(run("(7 < true)"), "")
  testExc(run("{ if (7) 7 else 31 }"), "")
  testExc(run("(31 :: (7 :: Nil))._1"), "")
  testExc(run("(7, 31)._3"), "")
  testExc(run("(31 :: 7)"), "")
  testExc(run("(7, 31).isEmpty"), "")
  testExc(run("(7, 31).head"), "")
  testExc(run("(7, 31).tail"), "")
  testExc(run("((v) => v)(Nil).head"), "")
  testExc(run("((f) => f)(Nil).tail"), "")
  testExc(run("{ val c = (() => u); { val u = 7; c() } }"), "")
  testExc(run("7()"), "")
  testExc(run("((s) => s)()"), "")
  testExc(run("((u) => u)(7, 31)"), "")

  test(run("""
  def fill(a, n) =
    if (n == 0)
      Nil
    else
      a :: fill(a, n - 1);
  def map(l, f) =
    if (l.isEmpty)
      Nil
    else
      f(l.head) :: map(l.tail, f);
  def filter(l, p) =
    if (l.isEmpty)
      Nil
    else if (p(l.head))
      l.head :: filter(l.tail, p)
    else
      filter(l.tail, p);
  def foldLeft(a, l, f) =
    def aux(i, r) =
      if (r.isEmpty)
        i
      else
        aux(f(i, r.head), r.tail);
    aux(a, l);
  val l = fill(1, 10);
  val l = foldLeft((0, Nil), l, (a, b) => (a._1 + b, a._1 + b :: a._2))._2;
  val l = filter(l, x => x % 2 != 0);
  val l = map(l, x => x * x);
  foldLeft(0, l, (a, b) => a + b)
  """), "165")
  test(run("""
  def isPrime(n) =
    def aux(m) =
      if (n == m)
        true
      else if (n % m == 0)
        false
      else
        aux(m + 1);
    aux(2);
  def factorize(n) =
    if (isPrime(n))
      n :: Nil
    else
      def aux(m) =
        if (n % m == 0)
          m :: factorize(n / m)
        else
          aux(m + 1);
      aux(2);
  factorize(9702)
  """), "(2 :: (3 :: (3 :: (7 :: (7 :: (11 :: Nil))))))")
  test(run("""
  def interp(e) =
    if(e.isInstanceOf[Int])
      e
    else if (e.head)
      interp_plus(e.tail)
    else
      interp_minus(e.tail);
  def interp_plus(e) =
    val v1 = interp(e.head);
    val v2 = interp(e.tail.head);
    v1 + v2;
  def interp_minus(e) =
    val v1 = interp(e.head);
    val v2 = interp(e.tail.head);
    v1 - v2;
  interp(true::(false::4::1::Nil)::6::Nil)
  """), "9")
  test(run("""
  def merge(l, r) =
    if (l.isEmpty)
      r
    else if (r.isEmpty)
      l
    else
      val x = l.head;
      val y = r.head;
      if (x <= y)
        x :: merge(l.tail, r)
      else
        y :: merge(l, r.tail);
  def split(o) =
    if (o.isEmpty)
      (Nil, Nil)
    else if (o.tail.isEmpty)
      (o, Nil)
    else
      val x = o.head;
      val y = o.tail.head;
      val zs = o.tail.tail;
      val (xs, ys) = split(zs);
      (x :: xs, y :: ys);
  def mergeSort(o) =
    if (o.isEmpty)
      Nil
    else if (o.tail.isEmpty)
      o
    else
      val (as, bs) = split(o);
      merge(mergeSort(as), mergeSort(bs));
  mergeSort(5 :: 1 :: 3 :: 2 :: 4 :: Nil)
  """), "(1 :: (2 :: (3 :: (4 :: (5 :: Nil)))))")
  test(run("""
  val emptyTree = 0;
  def makeNode(x, l, r) = (x, l, r);
  def match(tree) = (f, g) =>
    if (tree.isInstanceOf[Tuple])
      val (x, l, r) = tree;
      g(x, l, r)
    else
      f();
  def add(tree, elem) = match(tree)(
    () => makeNode(elem, emptyTree, emptyTree),
    (x, l, r) =>
      if (elem < x)
        makeNode(x, add(l, elem), r)
      else if (elem > x)
        makeNode(x, l, add(r, elem))
      else
        makeNode(x, l, r)
  );
  def append(l1, l2) =
    if (l1.isEmpty)
      l2
    else
      l1.head :: append(l1.tail, l2);
  def flatten(tree) = match(tree)(
    () => Nil,
    (x, l, r) => append(flatten(l), x :: flatten(r))
  );
  val t = emptyTree;
  val t = add(t, 4);
  val t = add(t, 8);
  val t = add(t, 6);
  val t = add(t, 2);
  val t = add(t, 10);
  flatten(t)
  """), "(2 :: (4 :: (6 :: (8 :: (10 :: Nil)))))")

  /* Write your own tests */
}
