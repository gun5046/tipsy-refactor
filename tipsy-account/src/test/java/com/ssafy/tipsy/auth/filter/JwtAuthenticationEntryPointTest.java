package com.ssafy.tipsy.auth.filter;


import com.ssafy.tipsy.auth.model.AuthFail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtAuthenticationEntryPointTest{
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Test
    public void Token에_Jwt_형식이_아닌_값이_들어있을_때_response에_잘못되었다라는_응답을_넣어_돌려준다() throws IOException, ServletException {
        // given
        AuthenticationException authException = mock(AuthenticationException.class);
        AuthFail authFail = AuthFail.WRONG;

        request.setAttribute("Exception", authFail);
        when(request.getAttribute("Exception")).thenReturn(authFail);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // when
        authenticationEntryPoint.commence(request, response, authException);

        // then
        verify(response).setStatus(AuthFail.WRONG.getCode());
        verify(response).setCharacterEncoding("utf-8");
        verify(response).setContentType("text/html; charset=UTF-8");
        verify(printWriter).write(AuthFail.WRONG.getMsg());
    }
    @Test
    public void 만료된_Token일_경우_response에_만료되었다라는_응답을_넣어_돌려준다() throws IOException, ServletException {
        // given
        AuthenticationException authException = mock(AuthenticationException.class);
        AuthFail authFail = AuthFail.EXPIRED;

        request.setAttribute("Exception", authFail);
        when(request.getAttribute("Exception")).thenReturn(authFail);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // when
        authenticationEntryPoint.commence(request, response, authException);

        // then
        verify(response).setStatus(AuthFail.EXPIRED.getCode());
        verify(response).setCharacterEncoding("utf-8");
        verify(response).setContentType("text/html; charset=UTF-8");
        verify(printWriter).write(AuthFail.EXPIRED.getMsg());
    }

    @Test
    public void 조작된_Jwt값이_들어있을_때_response에_유효하지_않았다라는_응답을_넣어_돌려준다() throws IOException, ServletException {
        // given
        AuthenticationException authException = mock(AuthenticationException.class);
        AuthFail authFail = AuthFail.INVALID;

        request.setAttribute("Exception", authFail);
        when(request.getAttribute("Exception")).thenReturn(authFail);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // when
        authenticationEntryPoint.commence(request, response, authException);

        // then
        verify(response).setStatus(AuthFail.INVALID.getCode());
        verify(response).setCharacterEncoding("utf-8");
        verify(response).setContentType("text/html; charset=UTF-8");
        verify(printWriter).write(AuthFail.INVALID.getMsg());
    }
}



