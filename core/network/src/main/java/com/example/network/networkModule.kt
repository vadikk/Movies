package com.example.network

import com.example.network.fake.FakeNetworkSource
import com.example.network.retrofit.MovieRetrofitApi
import com.example.network.retrofit.RetrofitNetwork
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single { AuthenticationInterceptor(BuildConfig.TMBD_API_KEY) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<AuthenticationInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MovieRetrofitApi::class.java) }

    single<NetworkDataSource> { RetrofitNetwork() }

    single<NetworkDataSource>(qualifier = named("Test")) { FakeNetworkSource() }
}