package alidev.projects.movieexplorer.data.remote.interceptor

import alidev.projects.movieexplorer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Interceptor que agrega automáticamente el api_key a todas las peticiones HTTP
 */
@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Agregar el api_key como query parameter a todas las peticiones
        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build()

        val requestWithApiKey = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(requestWithApiKey)
    }
}
