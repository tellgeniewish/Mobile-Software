package mobilesoftware.week03.kotlin_test

fun lambdaWithParam_fun (num:Int) : Int {
    return num + 10
}

class MyClass constructor/*생략 가능*/(var dept: String) /* var을 붙이면 멤버변수 취급, 원래는 지역변수*/ { // 주생성자
    // var dept: String // 주생성자 부분에서 var을 붙여주면 생략 ㄱㄴ
    // var dept: String = "test" // 생성자가 없으면, 클래스의 멤버 변수는 반드시 초기화되어야 한다

    /* //부생성자
    constructor(dept: String) { // 생성자를 만들었기 때문에 default생성자는 없다
        this.dept = dept
    }
    */
    init { // 주생성자일 때 동작
        this.dept = dept
        println("Main ${dept}")
    }
    constructor(dept: String, name: String) : this(dept) { // 보조 생성자
        // 주생성자를 명시적으로 호출할 경우 반드시 보조 생성자에서 주생성자 호출해야 함
        // this(dept)에서 주생성자 먼저 실행됨
        println("${dept} & ${name}")
    }

    fun getDept() {
        println(dept)
    }
}

open /*open 써야지 상속 ㄱㄴ*/ class SuperClass {
    init {
        println("Super init")
    }

    open /*open 안 쓰면 override 불가*/ fun member_func() {
        println("super func")
    }
}

class SubClass : SuperClass() {
    init {
        println("Sub init")
    }

    override fun member_func() { // 재정의 함수 없으면 메인에서 sub.member_func()했을 때 부모 멤버 함수 호출됨
        println("sub func")
    }
}

class YourClass01  // 멤버도 아무것도 없는 클래스
class YourClass02(val data: String /* 멤버변수로 선언됨*/) // 멤버변수가 하나인 클래스

data class subject(val title:String, var credit:Int) {
    // data 선언하면
    // toString 재정의함
    // 멤버에 같은 값 들어오면 똑같다고 계산하도록 equals 메소드 만들어줌

    override fun equals(other: Any?): Boolean { // Ctrl + O 물려받은 함수 재정의하고 싶을 때
        // return super.equals(other) // 원래 있는 문장

        // return this.title == other.title // 오류: Any클래스 업캐스팅 되어있음
        return this.title == (other as subject).title // 다운 캐스팅 해줘야 함
    }

    // data 생략하고 class 생성하면
    // toString 기본 기능이 실행 -> 객체의 아이디
    // equals 기본 기능이 실행 -> 객체의 아이디 비교
}

interface someInterface { // interface는 open 없어도 됨
    val dept: String
    fun getDept()
    /*
    하나의 클래스는 멤버변수와 멤버함수(몸체가 만들어져있음)로 구성됨 -> 객체 생성 ㄱㄴ
    인터페이스는 멤버변수와 멤버함수(몸체 없음)가 있을 수 있다.
    추상 클래스는 멤버변수와 멤버함수(몸체 있,없이 섞여있음)가 있을 수 있다.

    객체를 만들(몸체 있어야 함) 때는
    상속받아서 클래스화시켜서 실제 클래스를 만들고 object를 만든다.
    */
}
val obj = object: someInterface {
    override val dept:String = "computer"
    override fun getDept() {
        println("$dept")
    }
}

class companionClass(var data: Int) {
    // 코틀린에는 static 전역 변수가 없다
    companion object { // static과 유사
        var cData = 5
    }

}
fun main() {
    // 변수 선언 후 {} 나오면 람다 함수!
    val lambdaNoParam = { println("lambda!!!") } // 매개 변수가 없어서 -> 생략한 람다 함수
    // { -> println("lambda!!!")}
    // val lambdaNoParam : () -> Unit = { println("lambda!!!") }
    // val lambdaNoParam : (Unit) -> Unit = { println("lambda!!!") }
    lambdaNoParam()

    val noParam : (Int) -> String = { num: Int -> "No Param" }
    println(noParam(1))

    val lambdaWithParam = {num:Int -> num + 10} // num + 10값이 val lambdaWithParam에 저장되는 것이 아님!

    val result = lambdaWithParam(10) // num + 10값을 저장하고 싶으면 변수 선언해야 한다
    println(result)

    val lPower : (Int) -> Int = { // num:Int -> // 매개변수가 1개이면 it으로 생략 가능
        it * it // num*num
    }
    println(lPower(2))

    val nameFunc : () -> Unit = { println("SomSom!") }
    val subjectFunc : () -> Unit = {
        val subjectName = "Mobile Software"
        println("subjectName!")
    }
    fun higherOrderFunc (argFunc: () -> Unit) : (String) -> Unit {
        println("Dept: Computer")
        argFunc()
        return { grade:String -> println(grade) }
    }
    // 함수의 매개변수로 다른 함수 사용 ㄱㄴ
    var returnFun = higherOrderFunc(nameFunc)
    higherOrderFunc(subjectFunc)

    val t: String? = "test"
    println(t?.length)
    println(t?.length ?: 0) // null이 아니면 왼쪽

    val str: String? = null // ?가 없으면 에러
    println(str?.length) // ?를 써야 null을 포용 가능
    println(str?.length ?: 0) // 엘비스 연산자 // null이면 오른쪽
    //println(str!!.length ?: 0) // null이면 nullPointerException 발생

    val myObject: MyClass = MyClass("computer")
    myObject.getDept()

    val sub = SubClass()
    sub.member_func() // 상속 받아서 사용 ㄱㄴ

    val subject1 = subject("mobile", 3)
    val subject2 = subject("mobile", 3)
    // 최상위Any 클래스가 가진 기본 메소드 3개: toString, equals, hash
    println(subject("mobile", 3))
    
    println("${subject1}") // 문자열로 취급되서 toString메소드 호출됨
    println("${subject1.equals(subject2)}") // val subject1 = subject("mobile", 2) credit값을 다르게 하면 false 뜬다
    // credit값이 달라도 title만 같으면 true가 뜨게 하고 싶으면 equals메소드 재정의해야 함

    println(companionClass.cData)

    val companionObject = companionClass(5)
    println(companionObject.data)
}