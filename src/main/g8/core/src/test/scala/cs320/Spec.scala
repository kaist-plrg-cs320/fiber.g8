package cs320

import Macros._

class Spec extends SpecBase {

  val run = Implementation.run _

  test(run("(26 + 6)"), "32")
  test(run("(((q) => q)(26) + ((k) => k)(6))"), "32")
  test(run("(26 * 6)"), "156")
  test(run("(((t) => t)(26) * ((l) => l)(6))"), "156")
  test(run("(((v) => v)(26) / ((m) => m)(6))"), "4")
  test(run("(26 / (-6))"), "-4")
  test(run("((-26) / 6)"), "-4")
  test(run("((-26) / (-6))"), "4")
  test(run("(((q) => q)(26) % ((l) => l)(6))"), "2")
  test(run("(26 % (-6))"), "2")
  test(run("((-26) % 6)"), "-2")
  test(run("((-26) % (-6))"), "-2")
  test(run("(((n) => n)(6) == ((p) => p)(6))"), "true")
  test(run("(6 == 26)"), "false")
  test(run("(((a) => a)(6) < ((o) => o)(6))"), "false")
  test(run("(6 < 26)"), "true")
  test(run("{ if (((m) => m)(true)) 6 else 26 }"), "6")
  test(run("{ if (true) 6 else x }"), "6")
  test(run("{ if (false) 6 else 26 }"), "26")
  test(run("(6, 26)"), "(6, 26)")
  test(run("((6, 26), (6, 26))"), "((6, 26), (6, 26))")
  test(run("(6, 26)._1"), "6")
  test(run("((x) => x)((6, 26))._1"), "6")
  test(run("(6, 26)._2"), "26")
  test(run("(6 :: Nil)"), "(6 :: Nil)")
  test(run("(26 :: ((t) => t)((6 :: Nil)))"), "(26 :: (6 :: Nil))")
  test(run("(Nil :: Nil)"), "(Nil :: Nil)")
  test(run("Nil.isEmpty"), "true")
  test(run("((d) => d)(Nil).isEmpty"), "true")
  test(run("(6 :: Nil).isEmpty"), "false")
  test(run("((i) => i)((6 :: Nil)).isEmpty"), "false")
  test(run("(6 :: Nil).head"), "6")
  test(run("((x) => x)((6 :: Nil)).head"), "6")
  test(run("(26 :: (6 :: Nil)).tail"), "(6 :: Nil)")
  test(run("((f) => f)((26 :: (6 :: Nil))).tail"), "(6 :: Nil)")
  test(run("{ val x = 6; x }"), "6")
  test(run("{ val x = true; { val x = 26; x } }"), "26")
  test(run("{ val x = 6; { val f = x; f } }"), "6")
  test(run("(() => 6)()"), "6")
  test(run("((x, f) => (x + f))(6, 26)"), "32")
  test(run("((x) => ((f) => (x + f)))(6)(26)"), "32")
  test(run("{ val x = 6; { def x() = x; x } }"), "<function>")
  test(run("{ val x = 6; { def x() = x; x() } }"), "<function>")
  test(run("{ val f = 6; { def x() = f; x() } }"), "6")
  test(run("{ def u(r) = { if ((r < 3)) (r + 5) else (u((r + (-1))) + (r + 5)) }; u(13) }"), "150")
  test(run("{ def h(d, n, t) = { if ((n < 1)) (t + 1) else (x((n + (-2))) + (d + 5)) }; def x(i) = { if ((i < 1)) (i + 2) else (h((i + 5), (i + (-2)), (i + 4)) + (i + 5)) }; x(12) }"), "95")
  test(run("{ def t(r, x) = { if ((x < 2)) (r + 4) else (q((r + 2), (x + (-1))) + (x + 2)) }; def k(j) = { if ((j < 3)) (j + 5) else (i((j + (-2))) + (j + 1)) }; def i(s) = { if ((s < 2)) (s + 3) else (l((s + (-2))) + (s + 5)) }; def l(o) = { if ((o < 1)) (o + 1) else (t((o + 1), (o + (-1))) + (o + 5)) }; def q(b, x) = { if ((x < 3)) (b + 3) else (k((x + (-1))) + (b + 2)) }; t(14, 11) }"), "86")
  test(run("(6.isInstanceOf[Int], false.isInstanceOf[Int], (6, 26).isInstanceOf[Int], Nil.isInstanceOf[Int], (6 :: Nil).isInstanceOf[Int], ((s) => s).isInstanceOf[Int])"), "(true, false, false, false, false, false)")
  test(run("(6.isInstanceOf[Boolean], false.isInstanceOf[Boolean], (6, 26).isInstanceOf[Boolean], Nil.isInstanceOf[Boolean], (6 :: Nil).isInstanceOf[Boolean], ((t) => t).isInstanceOf[Boolean])"), "(false, true, false, false, false, false)")
  test(run("(6.isInstanceOf[Tuple], false.isInstanceOf[Tuple], (6, 26).isInstanceOf[Tuple], Nil.isInstanceOf[Tuple], (6 :: Nil).isInstanceOf[Tuple], ((c) => c).isInstanceOf[Tuple])"), "(false, false, true, false, false, false)")
  test(run("(6.isInstanceOf[List], true.isInstanceOf[List], (6, 26).isInstanceOf[List], Nil.isInstanceOf[List], (6 :: Nil).isInstanceOf[List], ((s) => s).isInstanceOf[List])"), "(false, false, false, true, true, false)")
  test(run("(6.isInstanceOf[Function], false.isInstanceOf[Function], (6, 26).isInstanceOf[Function], Nil.isInstanceOf[Function], (6 :: Nil).isInstanceOf[Function], ((e) => e).isInstanceOf[Function])"), "(false, false, false, false, false, true)")
  test(run("(((z) => z)(6).isInstanceOf[Int], ((k) => k)(true).isInstanceOf[Int])"), "(true, false)")
  testExc(run("{ val x = 6; f }"), "")
  testExc(run("(6 + false)"), "")
  testExc(run("(6 * false)"), "")
  testExc(run("(6 / true)"), "")
  testExc(run("(6 / ((l) => l)(0))"), "")
  testExc(run("(6 % false)"), "")
  testExc(run("(6 % ((o) => o)(0))"), "")
  testExc(run("(true == false)"), "")
  testExc(run("(6 < false)"), "")
  testExc(run("{ if (6) 6 else 26 }"), "")
  testExc(run("(26 :: (6 :: Nil))._1"), "")
  testExc(run("(6, 26)._3"), "")
  testExc(run("(26 :: 6)"), "")
  testExc(run("(6, 26).isEmpty"), "")
  testExc(run("(6, 26).head"), "")
  testExc(run("(6, 26).tail"), "")
  testExc(run("((g) => g)(Nil).head"), "")
  testExc(run("((p) => p)(Nil).tail"), "")
  testExc(run("{ val f = (() => x); { val x = 6; f() } }"), "")
  testExc(run("6()"), "")
  testExc(run("((i) => i)()"), "")
  testExc(run("((f) => f)(6, 26)"), "")
  test(run("""
  def fill(a, n) =
    if (n == 0) Nil
    else a :: fill(a, n - 1);
  def map(l, f) =
    if (l.isEmpty) Nil
    else f(l.head) :: map(l.tail, f);
  def filter(l, p) =
    if (l.isEmpty) Nil
    else if (p(l.head)) l.head :: filter(l.tail, p)
    else filter(l.tail, p);
  def foldLeft(a, l, f) =
    def aux(i, r) =
      if (r.isEmpty) i
      else aux(f(i, r.head), r.tail);
    aux(a, l);
  val l = fill(1, 10);
  val l = foldLeft((6, Nil), l, (a, b) => (a._1 + b, a._1 + b :: a._2))._2;
  val l = filter(l, x => x % 2 != 0);
  val l = map(l, x => x * x);
  foldLeft(0, l, (a, b) => a + b)
  """), "645")
  test(run("""
  def isPrime(n) =
    def aux(m) =
      if (n == m) true
      else if (n % m == 0) false
      else aux(m + 1);
    aux(2);
  def factorize(n) =
    if (isPrime(n)) n :: Nil
    else
      def aux(m) =
        if (n % m == 0) m :: factorize(n / m)
        else aux(m + 1);
      aux(2);
  factorize(936)
  """), "(2 :: (2 :: (2 :: (3 :: (3 :: (13 :: Nil))))))")
  test(run("""
  def interp(e) =
    if (e.isInstanceOf[Int]) e
    else if (e.head) interp_plus(e.tail)
    else interp_minus(e.tail);
  def interp_plus(e) =
    val v1 = interp(e.head);
    val v2 = interp(e.tail.head);
    v1 + v2;
  def interp_minus(e) =
    val v1 = interp(e.head);
    val v2 = interp(e.tail.head);
    v1 - v2;
  interp(true :: (false :: 6 :: 26 :: Nil) :: 6 :: Nil)
  """), "-14")
  test(run("""
  def merge(l, r) =
    if (l.isEmpty) r
    else if (r.isEmpty) l
    else
      val x = l.head;
      val y = r.head;
      if (x <= y) x :: merge(l.tail, r)
      else y :: merge(l, r.tail);
  def split(o) =
    if (o.isEmpty) (Nil, Nil)
    else if (o.tail.isEmpty) (o, Nil)
    else
      val x = o.head;
      val y = o.tail.head;
      val zs = o.tail.tail;
      val (xs, ys) = split(zs);
      (x :: xs, y :: ys);
  def mergeSort(o) =
    if (o.isEmpty) Nil
    else if (o.tail.isEmpty) o
    else
      val (as, bs) = split(o);
      merge(mergeSort(as), mergeSort(bs));
  mergeSort(9 :: 10 :: 8 :: 7 :: 6 :: Nil)
  """), "(6 :: (7 :: (8 :: (9 :: (10 :: Nil)))))")
  test(run("""
  val emptyTree = 0;
  def makeNode(x, l, r) = (x, l, r);
  def match(tree) = (f, g) =>
    if (tree.isInstanceOf[Tuple])
      val (x, l, r) = tree;
      g(x, l, r)
    else f();
  def add(tree, elem) = match(tree)(
    () => makeNode(elem, emptyTree, emptyTree),
    (x, l, r) =>
      if (elem < x) makeNode(x, add(l, elem), r)
      else if (elem > x) makeNode(x, l, add(r, elem))
      else makeNode(x, l, r)
  );
  def append(l1, l2) =
    if (l1.isEmpty) l2
    else l1.head :: append(l1.tail, l2);
  def flatten(tree) = match(tree)(
    () => Nil,
    (x, l, r) => append(flatten(l), x :: flatten(r))
  );
  val t = emptyTree;
  val t = add(t, 12);
  val t = add(t, 24);
  val t = add(t, 18);
  val t = add(t, 6);
  val t = add(t, 30);
  flatten(t)
  """), "(6 :: (12 :: (18 :: (24 :: (30 :: Nil)))))")

  /* Write your own tests */
}
