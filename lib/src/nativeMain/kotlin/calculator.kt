import kotlin.native.CName

@CName("sum", "sum")
fun sum(a: Int, b: Int): Int {
    return a + b
}

@CName("mult", "mult")
fun mult(a: Int, b: Int): Int {
    return a * b
}