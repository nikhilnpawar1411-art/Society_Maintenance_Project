package com.example.societyMaintenanceMgmt.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class    JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        try {
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);

                //Validate Token
                if (jwtUtil.validateToken(token)) {
                    Long userId = jwtUtil.extractUserId(token);
                    Long societyId = jwtUtil.extractSocietyId(token);
                    String role = jwtUtil.extractRole(token);
                    String loginId = jwtUtil.extractLoginId(token);
                    String userName= jwtUtil.extractUserName(token);

                    LoggedInUser loggedInUser =
                            new LoggedInUser(
                                    userId,
                                    societyId,
                                    loginId,
                                    userName,
                                    role
                            );

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    loggedInUser,
                                    null,
                                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
                            );

                    if(SecurityContextHolder.getContext().getAuthentication()==null){
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } else {
                    response.setStatus((HttpServletResponse.SC_UNAUTHORIZED));
                    return;
                }
            }
            filterChain.doFilter(request, response);
        } catch (JwtException | IllegalArgumentException  ex){
            response.setStatus((HttpServletResponse.SC_UNAUTHORIZED));
            return;
        }
//        finally {
//            SocietyContext.clear();
//        }
    }
}
