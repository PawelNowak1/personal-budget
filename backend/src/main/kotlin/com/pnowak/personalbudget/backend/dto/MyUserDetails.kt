package com.pnowak.personalbudget.backend.dto

import com.pnowak.personalbudget.backend.entities.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetails(val id: Long?, private val username: String?, val email: String?, private val password: String, val active: Boolean, private val authority: GrantedAuthority) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(authority)
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String? {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return active
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val user = other as MyUserDetails
        return id == user.id
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + active.hashCode()
        result = 31 * result + authority.hashCode()
        return result
    }

    companion object {
        private const val serialVersionUID = 1L
        fun build(user: User): MyUserDetails {
            val authority: GrantedAuthority = SimpleGrantedAuthority("ROLE_USER")
            return MyUserDetails(
                    user.id,
                    user.username,
                    user.email,
                    user.password,
                    user.active,
                    authority
            )
        }
    }

}
