package com.kiranvarghese.MachineTest.data.models

data class PassengersResponse(
    val `data`: List<Passenger>,
    val totalPages: Int,
    val totalPassengers: Int
)