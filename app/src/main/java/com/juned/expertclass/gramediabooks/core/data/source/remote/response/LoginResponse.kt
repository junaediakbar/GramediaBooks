package com.juned.expertclass.gramediabooks.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	@field:SerializedName("token")
	val token: Token? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Token(

	@field:SerializedName("abilities")
	val abilities: List<String?>? = null,

	@field:SerializedName("plain_text")
	val plainText: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("expired_at")
	val expiredAt: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
