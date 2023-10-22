package IaidoTest.JMcGowan.security
import IaidoTest.JMcGowan.exception.CustomAccessDeniedHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher
import javax.management.Query.and


// source for information: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html
//https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
// I chose to use .withDefaultPasswordEncoder for this test as it made testing and using the system simpler, if it was
// Production I would have opted for a password encoder

@Configuration
@EnableWebSecurity
class MultiHttpSecurityConfig(private val accessDeniedHandler: CustomAccessDeniedHandler) {
    private val customAccessDeniedHandler = CustomAccessDeniedHandler()
    @Bean
    fun userDetailsService(): UserDetailsService {
        val manager = InMemoryUserDetailsManager()
        manager.createUser(User.withUsername("guest").password("{noop}password").roles("GUEST").build())
        manager.createUser(User.withUsername("admin").password("{noop}password").roles("GUEST","ADMIN").build())
        return manager
    }
    // Above I initialise two users, one admin and a guest
    // Below I start an order that checks if the user is requesting a filtered list which is allowed by all users

    @Order(1)
    @Bean
    fun formLoginFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            antMatcher("/people/filter")
            authorizeRequests {
                authorize(anyRequest, authenticated)
            }
            formLogin { }
            exceptionHandling {
                accessDeniedHandler = customAccessDeniedHandler
            }
        }
        return http.build()
    }

    // If the URL is not the filtered list then only the Admin is allowed asccess
    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize(anyRequest, hasRole("ADMIN"))
            }
            httpBasic { }
            exceptionHandling {
                accessDeniedHandler = customAccessDeniedHandler
            }
        }
        return http.build()
    }


}