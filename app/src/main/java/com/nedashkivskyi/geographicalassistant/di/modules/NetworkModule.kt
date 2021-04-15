package com.nedashkivskyi.geographicalassistant.di.modules

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun apolloClient(): ApolloClient{
        return ApolloClient.builder().serverUrl("https://countries.trevorblades.com/").build()
    }
}