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

  // Fall 2021
  test(run("((3 + 1) / (2 / 1))"), "2")
  test(run("((3 * (-1 % 3)) * ((3 * 2) + (2 + 2)))"), "-30")
  test(run("(((1 / -1) * (1 + 2)) * ((3 / -1) / 3))"), "3")
  test(run("(((1 + 0) % 2) % ((1 * 1) + 2))"), "1")
  test(run("((3 + (0 / 3)) * (3 / (3 / 3)))"), "9")
  test(run("{ if ((2 < 1)) 2 else { if (true) 2 else 3 } }"), "2")
  test(run("((3 / 2) + { if (true) 0 else 2 })"), "1")
  test(run("((-1 + 2) * { if (true) -1 else 3 })"), "-1")
  test(run("((1 + 1) * { if (true) -1 else -1 })"), "-2")
  test(run("{ if ({ if (false) true else true }) 3 else { if (true) 2 else 1 } }"), "3")
  test(run("((2, 1, 3, 2), 3, (2, 3))._1._1"), "2")
  test(run("((-1, 3, 1)._2, (2, (0, 3, 2))._1)._1"), "3")
  test(run("(2, 1, 2, (2, 3))._4._1"), "2")
  test(run("((3, 2, 2)._1 * (1, -1, 2)._1)"), "3")
  test(run("(((3, 1, 2)._1 * (-1 + 3)) + (1, 3, 2, 3)._2)"), "9")
  test(run("((-1 :: (2 :: Nil)).tail.head + ((3 :: Nil).head :: (1 :: Nil)).head)"), "5")
  test(run("((3 + 2) :: (2 :: Nil)).tail.head"), "2")
  test(run("((((3 :: Nil).head * (1 :: Nil).head) * (3 :: Nil).head) % ((2 + 3) :: (2 :: Nil).tail).head)"), "4")
  test(run("((3 :: Nil) :: ((2 :: Nil) :: Nil)).tail.head.head"), "2")
  test(run("((1 :: Nil) :: ((2 :: Nil) :: Nil)).tail.head.head"), "2")
  test(run("{ val v = { val w = { val z = 0; 3 }; w }; { val v = { val y = 1; -1 }; { val t = -1; v } } }"), "-1")
  test(run("({ val u = 3; { val y = u; y } } / { val t = (1 * 3); 2 })"), "1")
  test(run("({ val x = { val u = 3; u }; (x * x) } * ({ val r = 1; 2 } / 2))"), "9")
  test(run("{ val s = (2 * 3); { val r = (3 / s); s } }"), "6")
  test(run("{ val s = ((3 + 3) / (3 / 3)); ({ val s = s; s } % (s * s)) }"), "6")
  test(run("((w) => (() => w)())(((z, s) => s)(0, 3))"), "3")
  test(run("((((y, t) => y)(3, 1) + ((q) => q)(3)) * ((z, y, t) => t)((3 + 1), ((t, u, s) => u)(0, 2, 1), ((r, y) => y)(3, 1)))"), "6")
  test(run("((q, x) => q)(((u, q) => u)((1 + 2), (1 % 1)), (0 + 1))"), "3")
  test(run("((((u, y) => y)(2, 1) * 3) * ((() => -1)() + (3 % 1)))"), "-3")
  test(run("((s, t, u) => t)(((2 % 1) % (3 * 1)), (1 % ((x) => x)(2)), (((r) => r)(-1) % (1 + 3)))"), "1")
  test(run("(((w, y) => ((r, u, x) => 2))(3, 2)(2, ((s) => 2)(3), (() => -1)()) + (((t) => t)(3) / ((s) => 3)(((s) => 3))))"), "3")
  test(run("(((q, x) => ((z) => q))(3, 2)((2 + 1)) / ((y) => ((q, w, y) => q))(0)((3 / 2), 3, (() => 3)))"), "3")
  test(run("((u) => ((z, x, s) => u))((2 % 3))(((z, y) => y)(((t, v) => v)(1, 1), (-1 * 1)), ((s) => s)(((y, x) => x)(3, 3)), ((w, q) => 0)((2 % 1), (() => 2)()))"), "2")
  test(run("((t) => ((r, x) => 3)(t, t))(((r, s) => r)((() => 3)(), (1 / 3)))"), "3")
  test(run("((((r, w, y) => r)(-1, 3, ((v) => v)) + (2 % 1)) % ((z) => z)(2))"), "-1")
  test(run("{ def r(w, z) = w(-1, 2); def q(r, u) = { def z(v, u) = ((y, z) => 3); def y(u) = ((w, t) => 1); r }; r(q, 2) }"), "-1")
  test(run("{ def v(q) = ((q, t, u) => u)(1, q, 2); def z(v) = v(3); def t(y, w) = ((u, w) => u)(w, y); z(v) }"), "2")
  test(run("{ def t(z) = (z / z); def z(u) = ((z, y, x) => z)(u, 2, u); def w(u, y) = y(u); w(1, t) }"), "1")
  test(run("{ def z(v, x) = { def r(y, q) = 3; def q() = 3; v }; def v(z) = z(2, 3); v(z) }"), "2")
  test(run("{ def w(r, x) = ((q, z) => x)(((r, s) => 3), 3); def z(w) = w(3, 3); def y(r) = { def r(v, t) = t; def z(q, v) = v; 1 }; z(w) }"), "3")
  test(run("{ def w(y, t) = { def y(s, q) = 1; def v(t, r) = t; t }; def v(q, s) = q(s, s); def q(z, y) = ((z) => y)(2); v(q, 3) }"), "3")
  test(run("{ def q(x) = (3 * x); def r(v, z) = { def u(y) = v; def w(t, x) = t; def q(v) = -1; 2 }; def x(y, s) = s(2); x(1, q) }"), "6")
  test(run("{ def v(s, y) = ((x, w, v) => 0)(2, y, y); def y(q, u) = u(q); def u(x) = ((v) => 3)(x); y(0, u) }"), "3")
  test(run("{ def w(u) = u(3, 1); def y(r, q) = ((x) => r)(r); def t(y) = { def v() = y; def r(u, z) = u; def u(s) = s; u }; w(y) }"), "3")
  test(run("{ def y(v) = v(2); def v(z) = (() => z)(); y(v) }"), "2")
  test(run("{ val v = 2; (((u, r) => 2), (true :: Nil), 2) }._1((2, ((((() => -1) :: Nil), Nil, true), 3))._2._1, ((v, t) => ((z) => ((y) => 3)))((((1 :: Nil) :: Nil), false), 2)(((u) => 2)(((w) => -1))))"), "2")
  test(run("((2, (true :: Nil), (3, 1, 1))._3._2 * ((((r) => 2) :: Nil), 3, 2, ((t, u, r) => u))._4((1 < 3), ((v, t) => 1)(false, 2), ((r, t) => ((u, r) => r))((2, 1, (true :: Nil), 1), 2)))"), "1")
  test(run("(((z) => ((w, r, z) => 3))(2)(((2, true, ((w) => 2)), 3, 1, (0 :: Nil))._4, ((q, z) => ((q, q, z) :: Nil))(3, 2), (2 == 2)) + (((v) => 2), (true, false, (false :: Nil), 1), true, 3)._4)"), "6")
  test(run("((v, s, x) => ((r) => ((x) => 3)))(1, 2, true)((2 / 1))((((x, t, s) => s), false, false)._1((-1, false, 1)._3, (() => (((q) => (1 :: Nil)), true, -1))(), (1 < 1)))"), "3")
  test(run("((((t, u) => u)(((2 :: Nil), 2, 2, (1, 2, ((z) => (() => -1)))), 2) + ((x, u) => 1)(3, false)) % ((s, u) => ((z) => 2))(2, ((true :: Nil), true, 0))(((s) => 2)(true)))"), "1")
  test(run("{ def q(u) = u; (() => ((x, z, y) => z)) }()(((true, -1, ((y) => y))._2 / (1, 3, true, 1)._1), ((t) => (1 :: Nil))((() => 0)).head, { val t = ((x) => false)(0); (true :: Nil) })"), "1")
  test(run("((((z) => 3)((2 :: Nil)) * ((v) => 2)(((2 :: Nil), true, false))) + ((s, y) => ((v, x) => s))(1, (false, (1 :: Nil)))((3 * 1), (() => -1)()))"), "7")
  test(run("((((y, z, s) => 2) :: Nil), 3)._1.head((((v, u) => u)(((x) => 3), 2) % ((y) => y)(3)), { val r = (1 == 2); ((t, y) => y)(-1, r) }, (3 / (3 :: Nil).head))"), "2")
  test(run("(((t, s) => ((y) => y)) :: Nil).head((false :: Nil).head, (((s, v, r) => s) :: Nil).head)((((q, w) => 2), false, ((x) => ((w) => 0)))._1((false :: Nil).isEmpty, ((t, q) => q)(1, false)))"), "2")
  test(run("{ val y = -1; (() => ((z, t) => t)) }()({ val t = (false, true, -1)._3; (true, Nil, false, (t, t, false))._1 }, (((x, r) => r)(false, 3) * ((y, s, w) => w)(2, true, -1)))"), "-3")
  test(run("((r, s) => ((w) => ((w) => s)))(((z) => z), 1)(((w, q) => w)(2, (1, (((3, false) :: Nil) :: Nil), false, true)))(({ def w(v, z) = 3; 3 } < (1, false)._1))"), "1")
  test(run("(((s, t) => 3) :: Nil).head((((t, x) => t)(3, (false, false, (2 :: Nil))) == { val v = (2, false, ((v) => 2)); 3 }), (((t, r) => t)(3, 2), ((t) => true)(2), { if (true) 2 else 1 }))"), "3")
  test(run("{ def s(v, r) = 3; def y(z, x) = z; def w(z) = 3; ((s, u, v) => ((z) => u)) }(((t) => true)(((y, z) => z)), (-1 * 1), (3 == 2))(((y, z) => true)((0 % 2), ((z) => (2, 1))(true)))"), "-1")
  test(run("(((x, y) => 2) :: (((w, q) => 2) :: Nil)).head({ def q(v) = true; ((s, v) => true) }({ if (true) 0 else 0 }, ((s) => 2)(2)), (((s, w) => w)(3, -1) == ((u) => u)(0)))"), "2")
  test(run("{ val v = (0 + 1); ((q) => ((v, y) => q))(v) }((2, ((v, w) => ((s) => (1, ((x) => (((w) => v), x)), v))))._2({ if (true) 1 else 2 }, ((true :: Nil), false)._1), (true :: (false :: (false :: Nil))))"), "1")
  test(run("((2 + 1).isInstanceOf[Int], (4 + 4).isInstanceOf[Boolean], (true && false).isInstanceOf[Tuple], (true || false).isInstanceOf[Boolean])"), "(true, false, false, true)")
  test(run("((3 :: Nil).isInstanceOf[List], (() => x).isInstanceOf[Function], (true, 0).isInstanceOf[Tuple], (true :: Nil).isInstanceOf[Function])"), "(true, true, true, false)")
  test(run("{ def r(x, z) = { if ((x < -1)) 1 else { if ((x == z)) r((x + -3), 0) else (r((x + -3), (z * x)) + r((x + -1), (2 + z))) } }; r(8, 6) }"), "60")
  test(run("{ def x(y) = { if ((y < -1)) 3 else { if ((y < 2)) v((y + -2), (y * 0)) else v((y + -1), 3) } }; def v(w, u) = { if ((w < 1)) (u + w) else { if ((w < 2)) v((w + -2), 0) else (v((w + -1), 2) + v((w + -1), 1)) } }; v(8, 8) }"), "-128")
  test(run("{ def r(x, y) = { if ((y < 1)) (x + 4) else { if ((3 == y)) v((y + -2)) else (r(1, (y + -3)) * v((y + -1))) } }; def v(u) = { if ((u < -1)) (u + 4) else { if ((0 == u)) (r((4 + u), (u + -3)) + r(0, (u + -1))) else (v((u + -3)) + v((u + -1))) } }; v(9) }"), "272")
  test(run("{ def t(s, v) = { if ((v < 3)) 1 else { if ((2 < s)) u((v + -1), 3) else (u((v + -2), 4) * t((4 * s), (v + -3))) } }; def z(q) = { if ((q < 3)) 3 else { if ((1 == q)) u((q + -2), (3 + q)) else (u((q + -3), (q * 4)) + 0) } }; def u(q, y) = { if ((q < -1)) 1 else { if ((y < 0)) (z((q + -3)) * u((q + -1), (y + 2))) else (z((q + -2)) * u((q + -1), 3)) } }; t(7, 9) }"), "3486784401")
  testExc(run("(4, 1)._4"), "")
  testExc(run("(b => b._1 * b._2 / b._3)(2, 9)"), "")
  testExc(run("3 :: 0 + 5"), "")
  testExc(run("if (!!!true) 3 > 2 else (4 || false)"), "")
  testExc(run("if (false || true) (7 || true) else 2 * 9 + 3"), "")
  testExc(run("(125 :: 2 && true :: Nil).head"), "")
  testExc(run("((c, d) => c)(5, 3 || true)"), "")
  testExc(run("val foo = (a => 3); val foo = (a => 1); foo((3, 2, 6)._4)"), "")
  testExc(run("def f(x, y) = if (x < 1) 0 else x + f(x - 1); f(12, Nil.tail)"), "")
  test(run("{ val y = false; { def r(v) = ((s, x) => v)(y, v); r(y) } }"), "false")

  /* Write your own tests */
}
