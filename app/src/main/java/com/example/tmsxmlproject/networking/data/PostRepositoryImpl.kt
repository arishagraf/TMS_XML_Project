package com.example.tmsxmlproject.networking.data

import com.example.tmsxmlproject.networking.domain.PostRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class PostRepositoryImpl : PostRepository {

    //we can use https://jsonplaceholder.typicode.com/posts - but it will not make real changes
    //so we are going to create our own api - https://mockapi.io/

    override suspend fun fetchPosts(): List<Post>? = withContext(Dispatchers.IO) {
        try {
            val url = URL("https://6898e221ddf05523e5600f48.mockapi.io/teachMeSkills/posts")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = InputStreamReader(connection.inputStream)
                val gson = Gson()

                val postsArray = gson.fromJson(reader, Array<Post>::class.java)
                reader.close()
                return@withContext postsArray.toList()
            } else {
                println("Error: ${connection.responseCode}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext null
    }

    override suspend fun deletePost(postId: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val url = URL("https://6898e221ddf05523e5600f48.mockapi.io/teachMeSkills/posts/$postId")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "DELETE"

            val responseCode = connection.responseCode
            return@withContext (
                    responseCode == HttpURLConnection.HTTP_OK ||
                            responseCode == HttpURLConnection.HTTP_NO_CONTENT
                    )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext false
    }

    override suspend fun updatePost(postId: String, updatedPost: Post): Post? =
        withContext(Dispatchers.IO) {
            try {
                val url = URL("https://6898e221ddf05523e5600f48.mockapi.io/teachMeSkills/posts/$postId")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "PUT"
                connection.doOutput = true
                connection.setRequestProperty("Content-Type", "application/json")

                val gson = Gson()
                val jsonBody = gson.toJson(updatedPost)

                connection.outputStream.use { os ->
                    os.write(jsonBody.toByteArray(Charsets.UTF_8))
                }

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = InputStreamReader(connection.inputStream)
                    return@withContext gson.fromJson(reader, Post::class.java)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@withContext null
        }
}