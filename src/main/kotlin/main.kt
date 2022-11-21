class Attachment

data class Post(
    var id: Int,
    val fromId: Int,
    val createdBy: Int,
    val published: Long,
    val text: String,
    val comments: String,
    val likes: Int,
    val original: Post?,
    val canDelete: Boolean,
    val canEdit: Boolean,
    var attachments: Array<Attachment> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (fromId != other.fromId) return false
        if (createdBy != other.createdBy) return false
        if (published != other.published) return false
        if (text != other.text) return false
        if (comments != other.comments) return false
        if (likes != other.likes) return false
        if (original != other.original) return false
        if (canDelete != other.canDelete) return false
        if (canEdit != other.canEdit) return false
        if (!attachments.contentEquals(other.attachments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + fromId
        result = 31 * result + createdBy
        result = 31 * result + published.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + comments.hashCode()
        result = 31 * result + likes
        result = 31 * result + (original?.hashCode() ?: 0)
        result = 31 * result + canDelete.hashCode()
        result = 31 * result + canEdit.hashCode()
        result = 31 * result + attachments.contentHashCode()
        return result
    }
}

object WallService {

    private var posts = emptyArray<Post>()
    private var idPosts: Int = 1

    fun clear() {
        posts = emptyArray()
    }

    //добавить пост
    fun add(post: Post): Post {
        for ((index, post) in posts.withIndex()) {
            if (post.id == idPosts) {
                idPosts += 1
            }
        }
        post.id = idPosts
        posts += post
        //println("добавляем " + posts.last())

        //val totalRecords = posts.size
        //println("Всего записей $totalRecords")
        return posts.last()
    }


    fun update(post: Post): Boolean {
        var (id) = post
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(text = "update text", comments = "update comment", likes = post.likes + 100, canDelete = false, canEdit = false)
                println(posts[index])
                return true
            }
        }
        return false
    }


    fun idById(id: Int) {
        //перебор по всем чтобы найти нужный
        //при этом получаем индекс
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                println("делаем изменения для id = " + post.id)
                posts[index] = post.copy(text = "update text update", comments = "update comment update", likes = post.likes + 10, canDelete = false, canEdit = false)
                //println("the element at $index is  $post")
            }
        }
    }


    fun repost(post: Post): Post {
        if (post.original == null) {
            println("Это оригинальный пост")
        }
        return post
    }


}


fun main() {

    val post = Post(0,11, 99, 20221010,"text post 0", "comment content", 0, null ,true,true)
    //val (id, fromId, _, published, text, content, likes) = post
    //println("$id, $fromId, $published, $text, $content, $likes")
    var result = WallService.add(post)
    //println(result)
    val repost = Post(0,51, 88, 20221010,"text post 88", "comment content 88", 0, post ,true,true)
    result = WallService.add(repost)
    //println(result)

    //новый объекты
    val postOne = post.copy(fromId = 12, text = "text post 1", likes = post.likes + 1, original = post)
    result = WallService.add(postOne)
    //println(result)
    val postTwo = post.copy(fromId = 13, text = "text post 2", likes = post.likes + 2)
    result = WallService.add(postTwo)
    //println(result)

    //изменим ранее созданный пост по id
    //WallService.idById(postOne.id)
    println("--------")
    println( WallService.update(postOne))

    WallService.repost(postOne)
}