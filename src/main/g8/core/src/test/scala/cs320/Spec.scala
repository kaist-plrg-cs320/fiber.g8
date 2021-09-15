package cs320

import Macros._

class Spec extends SpecBase {

  val run = Implementation.run _

  test(run("42"), "42")
  test(run("1 + 2"), "3")
  test(run("7 - 2"), "5")
  test(run("2 * 4"), "8")
  test(run("5 / 2"), "2")
  test(run("13 % 5"), "3")
  test(run("1 - -1"), "2")
  test(run("true"), "true")
  test(run("1 == 3 - 2"), "true")
  test(run("1 < 3 - 2"), "false")
  test(run("(1, 2 + 3, true)"), "(1, 5, true)")
  test(run("((42, 3 * 2), false)"), "((42, 6), false)")
  test(run("(1, 2 + 3, true)._1"), "1")
  test(run("((42, 3 * 2), false)._1._2"), "6")
  test(run("Nil"), "Nil")
  test(run("1 :: 1 + 1 :: Nil"), "(1 :: (2 :: Nil))")
  test(run("Nil.isEmpty"), "true")
  test(run("(1 :: Nil).isEmpty"), "false")
  test(run("(1 :: Nil).head"), "1")
  test(run("(1 :: Nil).tail"), "Nil")
  test(run("(1 :: 2 :: Nil).tail.head"), "2")
  test(run("""
    val x = 1 + 2;
    val y = x * 4 + 1;
    y / (x - 1)
  """), "6")
  test(run("""
    val (x, y) = (1 + 2, 3 + 4);
    val z = x * y;
    val (a, b, c) = (z, z + z, z + z + z);
    c - b
  """), "21")
  test(run("x => x + x"), "<function>")
  test(run("(x => x + x)(1)"), "2")
  test(run("(x => y => x + y)(1)(2)"), "3")
  test(run("((x, y) => x + y)(1, 2)"), "3")
  test(run("1.isInstanceOf[Int]"), "true")
  test(run("1.isInstanceOf[Boolean]"), "false")
  test(run("(1 :: Nil).isInstanceOf[List]"), "true")
  test(run("(x => x + x).isInstanceOf[Function]"), "true")
  test(run("if (true) 1 else 2"), "1")
  test(run("!true"), "false")
  test(run("true && false"), "false")
  test(run("true || false"), "true")
  test(run("1 != 2"), "true")
  test(run("1 <= 1"), "true")
  test(run("1 > 1"), "false")
  test(run("1 >= 1"), "true")
  test(run("Nil.nonEmpty"), "false")
  test(run("""
    def f(x) = x - 1;
    f(2)
  """), "1")
  test(run("""
    def f(x) = if (x < 1) 0 else x + f(x - 1);
    f(10)
  """), "55")

  // Fall 2020
  test(run("42 + -4 * 2"), "34")
  test(run("13 / 3 % 3"), "1")
  test(run("(1 :: 3 :: Nil).head == 7 % 2"), "true")
  test(run("1 == (4, 7)._2 % 2"), "true")
  test(run("((42, 4 :: 3 * 2 :: Nil), false)"), "((42, (4 :: (6 :: Nil))), false)")
  test(run("((42, 4 :: 3 * 2 :: Nil), false)._1._2.tail.head"), "6")
  test(run("((1, (x => x + 1), true)._2)(4)"), "5")
  test(run("(1, false, true).isInstanceOf[Tuple]"), "true")
  test(run("((x, y) => x._1 + y._2._2)((1, 2, 3), (1, (2, 3)))"), "4")
  test(run("""
    val (x, y) = (1 + 2, 3 + 4);
    val z = (y, x);
    if (z.isInstanceOf[Tuple]) 42 else false
  """), "42")
  test(run("if (false || (1 == 2)) 42 else 84"), "84")
  test(run("3 + 7 * 2"), "17")
  test(run("""
    val x = 4;
    val f = (x => x * x);
    f(12)
  """), "144")
  testExc(run("Nil.head"), "")
  testExc(run("(2, 3)._4"), "")
  testExc(run("(1, false)._9"), "")
  testExc(run("(a => a._1 * a._2 / a._3)(1, 7)"), "")
  testExc(run("1 :: 2 + 3"), "")
  test(run("42 :: 2 - 4 :: 23 > 11 && !false :: Nil"), "(42 :: (-2 :: (true :: Nil)))")
  test(run("(17, 42, 38)._2 / 3 == 14 || false :: false :: Nil"), "(true :: (false :: Nil))")
  test(run("if (!!true) 3 > 2 else (4 || true)"), "true")
  testExc(run("if (!!!true) 3 > 2 else (4 || true)"), "")
  testExc(run("if (false || true) (4 || true) else 4 * 10 + 2"), "")
  test(run("if (1 < 17 && 3 == 4) (4 || true) else 4 * 10 + 2"), "42")
  testExc(run("(123 :: 4 && true :: Nil).head"), "")
  testExc(run("((a, b) => a)(1, 1 || true)"), "")
  testExc(run("""
    val foo = (a => 1);
    foo((1, 2, 3)._4)
  """), "")
  testExc(run("""
    def f(x, y) = if (x < 1) 0 else x + f(x - 1);
    f(10, Nil.tail)
  """), "")
  test(run("""
    val x = 6;
    def f(y) = if (y == 0) 0 else x + f(y - 1);
    f(10)
  """), "60")
  test(run("""
    val (a, b) = (42, true);
    val f = (b => if (b) a else 7);
    f(false)
  """), "7")
  test(run("""
    val a = 42;
    val f = (x => a);
    f(12)
  """), "42")
  test(run("""
    def f(x) = if (x % 2 == 1) f(x - 1) else x + g(x);
    def g(x) = if (x == 0) 0 else x * x + f(x - 2);
    f(11)
  """), "250")
  test(run("""
    def f(x) = if (x == 0) 0 else (if (x % 3 == 2) h(x - 1) else x + g(x - 1));
    def g(x) = if (x == 0) 0 else (if (x % 3 == 1) f(x - 1) else x + h(x - 1));
    def h(x) = if (x == 0) 0 else (if (x % 3 == 0) g(x - 1) else x + f(x - 1));
    f(9)
  """), "45")

  // Spring 2021
  test(run("(((2 / -1) + (1 + 3)) % ((2 + 1) + (2 * 3)))"), "2")
  test(run("(((1 / 1) * (3 / 3)) + (2 * (2 * -1)))"), "-3")
  test(run("(((2 + 3) % 2) + ((0 / 2) + (2 * 0)))"), "1")
  test(run("((2 % (1 + 3)) / (3 % 2))"), "2")
  test(run("((2 + (3 + 2)) + ((3 + -1) * (2 % 3)))"), "11")
  test(run("{ if ({ if (true) false else true }) (-1 / 2) else { if (false) 2 else -1 } }"), "-1")
  test(run("{ if ({ if (false) false else false }) { if (true) 1 else 2 } else (1 * 1) }"), "1")
  test(run("((-1 / 3) + { if (false) -1 else 3 })"), "3")
  test(run("{ if (false) (-1 * 1) else { if (true) 1 else 3 } }"), "1")
  test(run("({ if (true) 3 else 1 } % 2)"), "1")
  test(run("((3, -1)._1, (3, 3)._1)._2"), "3")
  test(run("((3 + 1), ((2, 3, 0, 2), 3, (-1, 1, 0), 3)._1, (3, 3, 2)._1)._3"), "3")
  test(run("((3, 2, 1, 2), 1, 1, 3)._1._2"), "2")
  test(run("((2, 2, 3), 3, 3, 1)._1._2"), "2")
  test(run("((3, 2, (3, 2)), 2)._1._3._2"), "2")
  test(run("(((3 :: Nil).head / 2) + (1 * (1 % 1)))"), "1")
  test(run("(((2 :: Nil) :: Nil).head.head / (2 :: (1 :: Nil)).head)"), "1")
  test(run("(0 :: (1 :: Nil)).tail.head"), "1")
  test(run("((1 :: Nil) :: ((0 :: Nil) :: Nil)).head.head"), "1")
  test(run("(((1 :: Nil).head + (1 + 3)) + (1 % (3 / 3)))"), "5")
  test(run("{ val w = ({ val v = 2; 1 } / (-1 * 1)); ({ val x = w; 2 } * { val z = w; z }) }"), "-2")
  test(run("(({ val q = 0; q } + 3) / { val v = (2 / 2); { val q = v; v } })"), "3")
  test(run("{ val r = { val t = (3 / 3); { val q = 1; t } }; ({ val w = r; r } * { val y = r; r }) }"), "1")
  test(run("{ val x = { val w = (3 + 3); { val r = w; r } }; { val q = { val z = x; z }; { val t = 2; q } } }"), "6")
  test(run("({ val q = 1; { val y = q; y } } % ({ val z = 3; z } / { val r = 1; r }))"), "1")
  test(run("((x) => 2)((((q, t) => t)(1, 0) % ((s) => s)(3)))"), "2")
  test(run("((r) => ((r, s) => 3))((2 * -1))(((r) => r)(1), ((t) => -1)(3))"), "3")
  test(run("((x, y) => ((z) => y))(3, ((t) => t)(3))(((u, q, v) => u)(2, (3 + 2), (3 * 1)))"), "3")
  test(run("((t, r) => ((r, w) => 1))(((w) => w)(3), 1)((((s) => s)(2) % 3), (((v, x) => x)(1, 1) * ((u) => u)(3)))"), "1")
  test(run("((u, s) => u)((((t, u) => u)((() => 1), 3) * ((w, v) => w)(3, 2)), (((x) => 2)(2) + (1 * 2)))"), "9")
  test(run("{ def z(s, q) = ((y, x) => x)(s, s); def x() = ((t) => ((q) => t))(3); def y(t) = (t + 3); { def q(s, z) = -1; 1 } }"), "1")
  test(run("((u, x) => ((r, x, v) => u))(2, 1)({ def z(y) = y; def t(v, q) = v; def s(w) = w; 3 }, { def t(w, z) = w; 1 }, ((y) => y)(3))"), "2")
  test(run("((z, x) => 2)({ def x(z) = z; 1 }, ((w, z) => 3)(0, 2))"), "2")
  test(run("{ def v(t, r) = (() => -1)(); def s() = ((y) => y)(2); v(3, 3) }"), "-1")
  test(run("((t, q) => ((t, z, s) => 2))(3, 2)({ def u(y) = y; def r(x, u) = u; def t() = ((s, u) => 1); 1 }, { def w(u, q) = q; def q() = 3; def z(s) = 1; 3 }, { def q() = 3; def w() = 3; def u(s, y) = s; -1 })"), "2")
  test(run("{ def u(s) = { def u(q) = s; def w(s, t) = t; def v() = s; 0 }; def w(q, y) = ((v) => q)(y); { def v(w, x) = w; def u(v, x) = ((y, r) => r); 1 } }"), "1")
  test(run("((1 + 2).isInstanceOf[Int], (2 + 3).isInstanceOf[Boolean], (true && false).isInstanceOf[Tuple], (true || false).isInstanceOf[Boolean])"), "(true, false, false, true)")
  test(run("((1 :: Nil).isInstanceOf[List], (() => x).isInstanceOf[Function], (true, 1).isInstanceOf[Tuple], (true :: Nil).isInstanceOf[Function])"), "(true, true, true, false)")
  test(run("{ val s = (false :: Nil).tail; { val y = true; ((2 :: Nil), ((y, x) => (x, ((x, x, x, x), x, s))), -1, 1) } }._4"), "1")
  test(run("(3, (-1 :: Nil))._2.head"), "-1")
  test(run("((s, y) => 3)({ val y = 3; ((w, q) => y) }(3, (((false, false, 1) :: Nil), 2, (2, 3), false)._4), (() => (2 :: Nil).head))"), "3")
  test(run("((w, s, r) => ((v, x) => (1, ((u, q, s) => r))))((2, 3, true, 3), true, 2)((true :: Nil).head, (2 :: Nil).head)._1"), "1")
  test(run("{ val u = 2; (false, u) }._2"), "2")
  test(run("(((t) => 2), -1, { val r = true; -1 })._2"), "-1")
  test(run("(((x, v) => 2) :: Nil).head((false :: Nil).head, ((((x) => 1) :: Nil) :: Nil).head.head)"), "2")
  testExc(run("(3, 2)._4"), "")
  testExc(run("(b => b._1 * b._2 / b._3)(0, 6)"), "")
  testExc(run("2 :: 1 + 4"), "")
  testExc(run("if (!!!true) 4 > 1 else (5 || false)"), "")
  testExc(run("if (false || true) (5 || true) else 3 * 11 + 1"), "")
  testExc(run("(124 :: 3 && true :: Nil).head"), "")
  testExc(run("((c, d) => c)(2, 2 || true)"), "")
  testExc(run("val foo = (a => 2); val foo = (a => 2); foo((2, 3, 4)._4)"), "")
  testExc(run("def f(x, y) = if (x < 1) 0 else x + f(x - 1); f(10, Nil.tail)"), "")
  test(run("""
    def even(x) = if (x == 0) true else odd(x - 1);
    def odd(x) = if (x == 0) false else even(x - 1);
    (even(10), odd(10), even(9), odd(9))
  """), "(true, false, false, true)")
  test(run("(true, 2, 2, Nil)._4.isEmpty"), "true")
  test(run("(false :: (2, Nil)._2)"), "(false :: Nil)")
  test(run("(1 % -1)"), "0")
  test(run("{ val r = 1; (r :: Nil).head }"), "1")
  test(run("{ def z() = 1; { val z = 1; ((t, q) => z) }(true, false) }"), "1")
  test(run("{ val z = 1; { def z() = 2; z } }"), "<function>")
  test(run("(Nil :: Nil)"), "(Nil :: Nil)")
  test(run("Nil.isInstanceOf[Int]"), "false")
  testExc(run("-1 / 0"), "")
  testExc(run("true / false"), "")
  testExc(run("false.isEmpty"), "")
  testExc(run("(() => 1)(Nil, false)"), "")
  testExc(run("(2 % (1 / 2))"), "")
  testExc(run("(Nil == 0)"), "")
  testExc(run("((z, w, x) => w)(false, ((x, v) => z)(3, 3), false)"), "")
  testExc(run("(2 % 0)"), "")

  /* Write your own tests */
}
