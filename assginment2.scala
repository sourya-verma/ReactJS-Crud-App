
//Q1
def show(f: Int, n: Int) = {
  val list = List.range(1, n+1)
  list.flatMap { num => List.fill(f)(num) }
}
val f = 3
val n = 4
show(f, n)

//Q2--------------------------------------------------------------
// reverse using foldLeft
val list = List(1,2,3,4)

def reverse(list:List[Int]):List[Int] = {
  list.foldLeft(List[Int]()){(acc,ele)=>
    (ele::acc)
  }
}
reverse(list)
// reverse using recursion
def reverse(list:List[Int]):List[Int]= {
  def reverse(list:List[Int], i:Int):List[Int]= {
    if(list.size == i) Nil
    else reverse(list, i+1) :+list(i)
  }
  reverse(list, 0)
}

reverse(List(1,2,3,4,5,6))


//Q3-------------------------------------------------------------------

def sum( opt1:Option[Int],opt2: Option[Int]):Option[Int]={
  Some(opt1.getOrElse(0) + opt2.getOrElse(0))
}
val a:Option[Int] = Some(20)
val b:Option[Int] = Some(10)
sum(a,b).get

//Q4-------------------------------------------------------------------
// Fill using recursion

def fill(ele:Int)(nTime:Int):List[Int] = {
  if(nTime == 1) List[Int](ele)
  else ele :: fill(ele)(nTime-1)
}
val l = fill(6)(5)

//Q5----------------------------------------------------------------
// for removing duplicate
val list = List(1,2,3,4)
def dedupe(list:List[Int]):List[Int] = {
  list.foldLeft(List[Int]()){(acc,ele)=>
    if(!acc.contains(ele))(ele::acc)
    else acc
  }
}

val l = dedupe(list)

println(l)
//Q6-----------------------------------------------------------------
def wordCount(str:String):Map[String, Int] = {
  val arr = str.split(" ")
  arr.groupBy(f=>f).mapValues(x=>x.size)

}

val str = "hello how are you hello"
wordCount(str)


//Q7-----------------------------------------------------------------
implicit class Square(val num:Int) {
  def square = num * num
}

10 square

//Q8-----------------------------------------------------------------------------------
import java.io.File

val path:String = "/home/sourya/Desktop/javascript/demo"

(new File(path).list).count(i=>i==i)


//Q9---------------------------------------------------------------
def removeOdd(map:Map[String, Int]):Map[String,Int]={
  map.flatMap{
    i=> if(i._2 % 2 == 0) Map(i)
    else None
  }
}
val map = Map("a" -> 1,"b" -> 2, "c" -> 3, "d"->4)
removeOdd(map)
//Q10--------------------------------------------------------------
def  removeKeys( keys:List[String], map:Map[String,Int]):Map[String,Int]={
  map.flatMap{
    i=> if(!keys.contains(i._1)) Map(i)
    else None
  }
}
val k = List("a","b","c")
val m = Map("a"->1, "d"->2, "c"->3, "e"->4, "b"->6)
removeKeys(k,m)


//Q11--------------------------------------------------------------
def concat(list1:List[Int], list2:List[Int]):List[Int] = {
  list1 ::: list2
}

val l1 = List(1,2,3)
val l2 = List(4,5,6)

concat(l1,l2)

//Q12-------------------------------------------------------------
def concatenate(map1:Map[String,Int], map2:Map[String,Int])={
  val m1 = map1.flatMap{
    i=> if(map2.contains(i._1)) None
    else map1
  }
  val m2 = map2.flatMap{
    i=> if(map1.contains(i._1)) None
    else map2
  }
  val m3 = map2.flatMap{
    i=> if(map1.contains(i._1)) Map((i._1, map1(i._1)+map2(i._1)))
    else None
  }
  m1.++(m2).++(m3)
}

val m1 = Map("a"->1,"b"->1,"c"->1)
val m2 = Map("a"->1,"b"->4,"d"->1,"e"->4, "f"->5)

concatenate(m1,m2)

//Q13--------------------------------------------------------------
def zip(list1:List[Int], list2:List[String]):List[(Int,String)]={
  val l = list1.foldLeft(List[(Int, String)]()){ (acc, ele)=>
    (ele, list2(list1.indexOf(ele))) :: acc
  }
  l.sortWith((i,j)=>i._1< j._1)
}

val l1 = List(1,2,3)
val l2 = List("one", "two", "three")

zip(l1, l2)

//Q14--------------------------------------------------------------

def merge(l1:List[Int], l2:List[Int]):List[Int] = {
  (l1, l2) match {
    case (Nil, Nil) => List()
    case (_, Nil) => l1
    case (Nil, _) => l2
    case (_,_) => {
      if(l1(0) < l2(0)) l1(0) ::  merge(l1.tail, l2)
      else l2(0) :: merge(l1,l2.tail)
    }
  }
}

val l1 = List(1,3,5)
val l2 = List(2,4,6 )
merge(l1, l2  )

//Q15----------------------------------------------------------------

case class Salary(basic: Double, hra: Double, ta: Double)

case class Employee(id: Int, email: String, salary: Salary, age: Int)

def increaseSal(sal:Salary): Salary ={
  var b = sal.basic * 1.1;
  val h = sal.hra
  val t = sal.ta
  Salary(b,h,t)

}
def increaseHra(sal:Salary): Salary ={
  var b = sal.basic * 1.1;
  val h = sal.hra * 1.2
  val t = sal.ta
  Salary(b,h,t)
}

def appraisal(emps: List[Employee]): List[Employee] = {
  emps.map {
    i => {
      if(i.age > 50) new Employee(i.id, i.email, increaseHra(i.salary) ,i.age)
      else new Employee(i.id, i.email, increaseSal(i.salary) ,i.age)
    }
  }
}
val s1 = Salary(10000, 2000, 3000)
val s2 = Salary(20000, 3000, 4000)
val s3 = Salary(30000, 5000, 7000)

val e1 = Employee(1, "sourya@abc", s1, 70)
val e2 = Employee(1, "aditya@abc", s2, 24)
val e3 = Employee(1, "dharma@abc", s3, 25)

val list = appraisal(List(e1, e2, e3))

//Q16-----------------------------------------------------------------------------------
case class Student(id:Int, name:String, age:Int, branch:String)

def spiltByBranch(list:List[Student]):(List[Student],List[Student],List[Student],List[Student])={
  val cs = list.filter(i=> i.branch == "CS")
  val it = list.filter(i=> i.branch == "IT")
  val ec = list.filter(i=> i.branch == "EC")
  val me = list.filter(i=> i.branch == "ME")
  (cs,it,ec,me)
}

val s1 = Student(1,"aaaaaaaa", 21, "CS")
val s2 = Student(2,"bbbbbbb", 21, "CS")
val s3 = Student(3,"cccccccc", 21, "EC")
val s4 = Student(4,"dddddddd", 21, "ME")
val s5 = Student(5,"eeeeeee", 21, "CS")
val s6 = Student(6,"qqqqqqq", 21, "IT")
val s7 = Student(7,"wwwwwww", 21, "EC")
val s8 = Student(8,"rrrrrrrr", 21, "ME")

val list = List(s1, s2, s3, s4, s5, s6, s7, s8)

val tup = spiltByBranch(list)

tup._1.foreach(i=>println(i))
tup._2.foreach(i=>println(i))
tup._3.foreach(i=>println(i))
tup._4.foreach(i=>println(i))


//Q18------------------------------------------------------------------------------
case class Customer(value: Int)
case class Consultant(portfolio: List[Customer])
case class Branch(consultants: List[Consultant])
case class Company(branches: List[Branch])

def getCompanyValue(company: Company): Int = {

  company.branches.foldLeft(0){
    (acc,ele)=> acc + ele.consultants.foldLeft(0){(acc,ele)=>
      acc + ele.portfolio.foldLeft(0){(acc,ele)=>
        acc + ele.value
      }
    }
  }
}






val cus1= Customer(13);
val cus2= Customer(16);
val cus3= Customer(19);
val cus4= Customer(29);

val cons1 = Consultant(List(cus1,cus2, cus4))
val cons2 = Consultant(List(cus1,cus2,cus3))
val cons3 = Consultant(List(cus4,cus2,cus3))

val br1 = Branch(List(cons1, cons2))
val br2 = Branch(List(cons3, cons2))

val com = Company(List(br1, br2))


getCompanyValue(com)

//-----------------------------------------------------------------------------------






//---------------------------------------
def max(list:List[Int]):Int = {
  list match {
    case List() => -1
    case x::y=> if(x > max(y)) x else max(y)
  }
}
max(List(1,21,3,4,5,6))

//----------------------------------------------------------

def sum(list:List[Int]):Int={
  list match {
    case List() => 0
    case x::y => x + sum(y)
  }
}

sum(List(1,2,3,4,5))
