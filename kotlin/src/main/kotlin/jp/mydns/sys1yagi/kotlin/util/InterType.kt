import android.content.Intent
import android.content.Context

fun String.countUpperCase() : Int {
    fun _countUpperCase(s : List<Char>, n : Int) : Int =
            if(s.size == 0) n
            else _countUpperCase(s.tail, n + if(s.head!!.isUpperCase()) 1 else 0)

    return _countUpperCase(toCharList(), 0)
}

fun Intent.startActivity(context : Context?) : Unit {
    fun _startActivity(context : Context?) : Unit =
            context?.startActivity(this)
    return _startActivity(context)
}