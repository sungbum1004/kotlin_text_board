fun readLineTrime() = readLine()!!.trim()


fun main() {
    println("== 게시판 관리 프로그램 시작 ==")

    var articlesLastId = 0

    while ( true ) {
        print("명령어) ")
        val command = readLineTrime()

        if ( command == "system exit" ) {
            println("프로그램을 종료합니다.")
            break
        }

        else if ( command == "article write" ) {
            val id = articlesLastId + 1
            print("제목 : ")
            val title = readLineTrime()
            print("내용 : ")
            val body = readLineTrime()
            val article = Article(id, title, body)

            println("${id}번 게시물이 작성되었습니다.")
            articlesLastId = id

        }
    }

    println("== 게시판 관리 프로그램 끝 ==")
}
data class Article(
    val id: Int,
    val title: String,
    val body: String
)