package com.jonsnow.AssignmentSubmissionApp.filter;


import com.jonsnow.AssignmentSubmissionApp.repository.UserRepository;
import com.jonsnow.AssignmentSubmissionApp.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.hasText(header) ||(StringUtils.hasText(header) && !header.startsWith("Bearer "))){
            chain.doFilter(request,response);
            return;
        }

        // Authorizatio -> Bearer
        final String token = header.split("")[1].trim();
        //Get user identity and set it on the spring security context
        UserDetails userDetails = userRepository
                .findByUsername(jwtUtil.getUsernameFromToken(token))
                .orElse(null);

        //Get jwt token and validate
        if (!jwtUtil.validateToken(token, userDetails)){
            chain.doFilter(request,response);
            return;
        }



        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails == null ?
                                List.of(): userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }
}
