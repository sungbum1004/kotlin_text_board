import java.text.SimpleDateFormat

fun readLineTrime() = readLine()!!.trim()

// 가장 마지막에 입력된 게시물 번호
var articlesLastId = 0

val articles = mutableListOf<Article>()

fun getArticleById(id: Int): Article? {
    for (article in articles) {
        if (article.id == id) {
            return article
        }
    }

    return null
}

fun addArticle(title: String, body: String): Int {
    val id = articlesLastId + 1
    val regDate = Util.getNowDateStr()
    val updateDate = Util.getNowDateStr()

    val article = Article(id, regDate, updateDate, title, body)
    articles.add(article)

    articlesLastId = id

    return id
}


fun main() {
    println("== 게시판 관리 프로그램 시작 ==")

    loop@ while ( true ) {
        print("명령어) ")
        val command = readLineTrime()

        when {
           command == "system exit" -> {
                println("프로그램을 종료합니다.")
                break@loop
            }
           command.startsWith("article delete ") -> {
               val id = command.trim().split(" ")[2].toInt()

               val articleToDelete = getArticleById(id)

               if (articleToDelete == null) {
                   println("${id}번 게시물은 존재하지 않습니다.")
                   continue
               }
               articles.remove(articleToDelete)
               println("${id}번 게시물을 삭제하였습니다.")
           }
            command.startsWith("article modify ") -> {
                val id = command.trim().split(" ")[2].toInt()

                val articleToModify = getArticleById(id)

                if (articleToModify == null) {
                    println("${id}번 게시물은 존재하지 않습니다.")
                    continue
                }

                print("${id}번 게시물 새 제목 : ")
                articleToModify.title = readLineTrime()
                print("${id}번 게시물 새 내용 : ")
                articleToModify.body = readLineTrime()
                articleToModify.updateDate = Util.getNowDateStr()

                println("${id}번 게시물을 수정하였습니다.")
            }
            command.startsWith("article detail ") -> {
                val id = command.trim().split(" ")[2].toInt()

                val articleToDetail = getArticleById(id)

                if (articleToDetail == null) {
                    println("${id}번 게시물은 존재하지 않습니다.")
                    continue
                }

                println("번호 : ${articleToDetail.id}")
                println("작성날짜 : ${articleToDetail.regDate}")
                println("갱신날짜 : ${articleToDetail.updateDate}")
                println("제목 : ${articleToDetail.title}")
                println("내용 : ${articleToDetail.body}")
            }
           command == "article write" -> {

                print("제목 : ")
                val title = readLineTrime()
                print("내용 : ")
                val body = readLineTrime()

                val id = addArticle(title, body)

                println("${id}번 게시물이 작성되었습니다.")

            }
            command == "article list" -> {
                println("번호 / 작성날짜 / 제목")
                for ( article in articles ) {
                    println("${article.id} / ${article.regDate} / ${article.title}")
                }
            }
            else -> {
                println("`$command` 은(는) 존재하지 않는 명령어 입니다.")
            }
        }
    }

    println("== 게시판 관리 프로그램 끝 ==")
}
data class Article(
    val id: Int,
    val regDate: String,
    var updateDate: String,
    var title: String,
    var body: String
)

object Util {
    fun getNowDateStr(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        return dateFormat.format(System.currentTimeMillis())
    }
}