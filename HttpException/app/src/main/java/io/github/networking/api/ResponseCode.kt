package io.github.networking.api

import com.google.gson.annotations.SerializedName

enum class ResponseCode {
    @SerializedName("200")
    OK,

    @SerializedName("204")
    NO_CONTENT,

    @SerializedName("400")
    BAD_REQUEST,

    @SerializedName("403")
    FORBIDDEN,

    @SerializedName("404")
    NOT_FOUND,

    @SerializedName("500")
    INTERNAL_SERVER_ERROR,

    @SerializedName("502")
    BAD_GATEWAY,

    @SerializedName("503")
    SERVICE_UNAVAILABLE;
}