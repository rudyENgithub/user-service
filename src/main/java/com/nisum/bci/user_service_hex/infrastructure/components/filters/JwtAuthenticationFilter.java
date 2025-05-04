package com.nisum.bci.user_service_hex.infrastructure.components.filters;



import com.nisum.bci.user_service_hex.infrastructure.components.entry_point.CustomAuthenticationEntryPoint;
import com.nisum.bci.user_service_hex.infrastructure.components.exceptions.CustomAuthenticationException;
import com.nisum.bci.user_service_hex.infrastructure.components.exceptions.GeneralException;
import com.nisum.bci.user_service_hex.infrastructure.components.jwt.JwtTokenService;
import com.nisum.bci.user_service_hex.infrastructure.out.database.JpaUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JpaUserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public JwtAuthenticationFilter(JpaUserRepository userRepository, JwtTokenService jwtTokenService, @Lazy UserDetailsService userDetailsService, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = null;
        UUID userId = null;

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            if (jwtTokenService.validateToken(token)) {
                 if (!userRepository.existsByTokenAndIsActiveIsTrue(token)){
                     callEntryPoint(request, responseWrapper);
                     return;
                 }
                userId = UUID.fromString(jwtTokenService.getUserIdFromJWT(token));
            }
        }

        loadUserByUsername(request, userId);

        filterChain.doFilter(request, response);
    }

    private void loadUserByUsername(HttpServletRequest request, UUID userId) {
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(String.valueOf(userId));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
    private void callEntryPoint(HttpServletRequest request, ContentCachingResponseWrapper responseWrapper) throws IOException, ServletException {
        responseWrapper.resetBuffer();
        customAuthenticationEntryPoint.commence(request, responseWrapper,
                new CustomAuthenticationException("Error en autenticación JWT",
                        new GeneralException("Token no valido", HttpStatus.BAD_REQUEST)));
        responseWrapper.copyBodyToResponse();
    }
}
