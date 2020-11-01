package com.pnowak.personalbudget.backend.security

import com.pnowak.personalbudget.backend.dto.MyUserDetails
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*

@Component
class JwtUtils {
    private val JWT_SECRET = "JWT_SECRET_KEY"
    private val JWT_EXPIRATION = 60 * 60 * 1000
    fun generateJwtToken(authentication: Authentication): String {
        val userDetails: MyUserDetails = authentication.principal as MyUserDetails
        return Jwts.builder()
                .setSubject(userDetails.username)
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact()
    }

    fun generateJwtToken(userName: String?): String {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact()
    }

    fun getUsernameFromJwtToken(token: String?): String {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject()
    }

    fun validateJwtToken(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: " + e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: " + e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: " + e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: " + e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: " + e.message)
        }
        return false
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtUtils::class.java)
    }
}
