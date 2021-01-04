package io.github.jesterz91.testapp.util

import com.squareup.moshi.Json
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object EnumConverterFactory {

    fun create() = object : Converter.Factory() {
        override fun stringConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<Enum<*>, String>? {
            return if (type is Class<*> && type.isEnum) {
                Converter { enum ->
                    try {
                        enum.javaClass.getField(enum.name).getAnnotation(Json::class.java)?.name
                    } catch (exception: Exception) {
                        null
                    } ?: enum.toString()
                }
            } else {
                null
            }
        }
    }
}