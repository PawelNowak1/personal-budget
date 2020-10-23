package com.pnowak.personalbudget.backend.security

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthEntryPoint : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(request: HttpServletRequest, response: HttpServletResponse,
                          authenticationException: AuthenticationException) {
        logger.error("Received unauthorized request, error: " + authenticationException.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error: unauthorized")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AuthEntryPoint::class.java)
    }
}
