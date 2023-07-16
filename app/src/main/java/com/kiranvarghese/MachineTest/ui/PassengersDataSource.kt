import androidx.paging.PagingSource
import com.kiranvarghese.MachineTest.data.models.ExampleJson2KtKotlin
import com.kiranvarghese.MachineTest.data.models.Users

class PassengersDataSource(private val api: MyApi) : PagingSource<Int, Users>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Users> {
        return try {
            val nextPageNumber = if (params.key != null) params.key!! + 10 else 0
            val response: ExampleJson2KtKotlin = api.getPassengersData(nextPageNumber, 10)

            LoadResult.Page(
                data = response.users,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.total!!) nextPageNumber else null

            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}