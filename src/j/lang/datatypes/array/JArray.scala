package j.lang.datatypes.array

import j.lang.datatypes.JTypeMacros._
import j.lang.datatypes.array.types._
import j.lang.datatypes.array.ArrayImplicits._
import JArrayFlag._
import j.util.Rational

import JArray._
import j.lang.datatypes.array.types.JNumber

object JArray {
  
  def apply[T <% JArrayType](flag: JArrayFlag, jaType: JType, refcount: Int, 
      numItems: Int, shape: List[Int], ravel: Array[T]) = 
        new JArray(flag, jaType, refcount, numItems, shape, ravel)
  
  def scalar[T <% JArrayType : Manifest](sc: T): JArray[T] = {
    JArray(afNONE, sc.typeMacro,0, 1, List(), Array[T](sc))
  }
    
    def arithmeticProgression(n: Int, b: Int, m: Int):JArray[JInt] =
      JArray(afNONE, jINT, 0, n, List(n), (0 to n).map(b + m * _).toArray)

    def string(str: String) = {
      JArray(afNONE, jCHAR, 0, str.length, List(str.length), str.toCharArray())
    }
      
    def vec2(a: Int, b: Int):JArray[JInt] = {
      JArray(afNONE, jINT, 0, 2, List(2), Array(a,b))
    }
    
    def vec1(a: Int):JArray[JInt] = {
      JArray(afNONE, jINT, 0, 1, List(1), Array(a))
    }

  val zero:JArray[JInt] = scalar(0)
  val one  = scalar(1)
  val two  = scalar(2)
  val mone = scalar(-1)
  val pi   = scalar(scala.Math.Pi)
}
class JArray[T <% JArrayType](val flag: JArrayFlag, val jaType: JType, 
    var refcount: Int, val numItems: Int, val shape: List[Int], 
    val ravel: Array[T]) {

  def rank = shape.length
  def tally = shape(0)
  
  def apply(i: Int) = ravel(i)
}