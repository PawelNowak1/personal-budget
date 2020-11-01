package com.pnowak.personalbudget.backend.dto

class JwtResponse(var token: String, var id: Long?, var username: String?, var email: String?) {
    var type = "Bearer"

}
