package IaidoTest.JMcGowan.exception

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

//Custom API error response
// It is called upon when there is a user trying to access content without the proper permissions.
@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AccessDeniedException?
    ) {
        response?.sendError(HttpStatus.FORBIDDEN.value(), "You are not authorized to access this resource.")
    }
}