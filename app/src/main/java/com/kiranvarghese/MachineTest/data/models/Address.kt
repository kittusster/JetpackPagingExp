package com.kiranvarghese.MachineTest.data.models

import com.google.gson.annotations.SerializedName


data class Address (

  @SerializedName("address"     ) var address     : String?      = null,
  @SerializedName("city"        ) var city        : String?      = null,
  @SerializedName("coordinates" ) var coordinates : Coordinates? = Coordinates(),
  @SerializedName("postalCode"  ) var postalCode  : String?      = null,
  @SerializedName("state"       ) var state       : String?      = null

)