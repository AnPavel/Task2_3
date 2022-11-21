import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {

        var result = false
        val post = Post(0,12, 99, 20221010,"text post 0", "comment content", 0, null, true,true)
        WallService.add(post)
        val (id)= post

        if (id != 0) result = true

        assertTrue(result)
        println("$post.id = $id")
        assertEquals(post.id, id)
    }

    @Test
    fun updateFound() {

        //val service = WallService
        // заполняем несколькими постами
        //service.add(Post(0,22, 99, 20221010,"text post 0", "comment content", 0, true,true))
        //service.add(Post(0,23, 99, 20221010,"text post 1", "comment content", 1, true,true))
        //service.add(Post(0,24, 99, 20221010,"text post 2", "comment content", 2, true,true))

        //val update = Post(1, 12, 199, 20221011,"text post 33", "comment content 33", 10, false,false)
        //val result = service.update(update)

        val post = Post(0,12, 99, 20221010,"text post 0", "comment content", 0, null,true,true)
        WallService.add(post)
        val postOne = Post(0,22, 99, 20221010,"text post 1", "comment content", 0, null,true,true)
        WallService.add(postOne)
        val postTwo = Post(0,32, 99, 20221010,"text post 2", "comment content", 0, null, true,true)
        WallService.add(postTwo)

        val result = WallService.update(postOne)
        println(result)
        assertTrue(result)
    }

    @Test
    fun updateNoFound() {

        val post = Post(0,12, 99, 20221010,"text post 0", "comment content", 0, null, true,true)
        WallService.add(post)
        val postOne = Post(0,22, 99, 20221010,"text post 1", "comment content", 0, null, true,true)
        WallService.add(postOne)
        val postTwo = Post(0,32, 99, 20221010,"text post 2", "comment content", 0, null, true,true)
        //WallService.add(postTwo)

        val result = WallService.update(postTwo)
        println(result)
        assertFalse(result)
    }


}