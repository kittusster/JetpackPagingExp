import com.kiranvarghese.MachineTest.data.models.ExampleJson2KtKotlin
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("/users")
    suspend fun getPassengersData(
        @Query("skip") page: Int,
        @Query("limit") size: Int
    ): ExampleJson2KtKotlin

    companion object {

        private const val BASE_URL = "https://dummyjson.com"

        operator fun invoke(): MyApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
}