package com.pnowak.personalbudget.backend.security

import com.pnowak.personalbudget.backend.service.interfaces.UserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class TokenAuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    private val jwtUtils: JwtUtils? = null
    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jsonWebToken = parseJwt(request)
            if (jsonWebToken != null && jwtUtils!!.validateJwtToken(jsonWebToken)) {
                val username = jwtUtils.getUsernameFromJwtToken(jsonWebToken)
                val userDetails: UserDetails? = userDetailsService?.loadUserByUsername(username)
                val authenticationToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails?.authorities
                )
                authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        } catch (e: Exception) {
            logger.error("Failed to set user authentication: $e")
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val authorizationHeader = request.getHeader(AUTHORIZATION_HEADER)
        return if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            authorizationHeader.removePrefix("Bearer").trim()
        } else null
    }

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
    }
}
