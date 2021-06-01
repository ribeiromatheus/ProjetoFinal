package com.example.mobile.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class Product(val product_name: String, val price: String, val category: Int)
data class GetProduct(val product_name: String, val price: String, val category_name: String)
data class Category(val category_name: String)
data class GetCategory(val category_name: String, val id: Int)
data class User(val login: String)

interface ApiService {
    @GET("/product")
    suspend fun getProduct(): Response<List<GetProduct>>

    @POST("/product")
    suspend fun createProduct(@Body product: Product): Response<Product>

    @GET("/category")
    suspend fun getCategory(): Response<List<GetCategory>>

    @POST("/category")
    suspend fun createCategory(@Body category: Category): Response<Category>
}