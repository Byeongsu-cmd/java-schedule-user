package org.example.scheduleManagement.auth.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {
    // 로그인 검증을 건너뛸 수 있는 URL 목록 (화이트리스트)
    private static final String[] WHITE_LIST = {"/", "/signup", "/login","/logout"};

    @Override
    public void doFilter(
            ServletRequest request, // 클라이언트 요청
            ServletResponse response, // 서버 응답
            FilterChain chain // 다음 필터 또는 서블릿으로 요청 전달
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI(); // 요청된 URI 추출

        // 화이트리스트에 포함되지 않은 URI라면 로그인 여부 체크
        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false); // 세션 가져오기(없으면 null)

            // 로그인하지 않은 사용자인 경우
            if (session == null || session.getAttribute("LOGIN_USER") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }
        }
        // 다음 필터 또는 컨트롤러로 요청 전달
        chain.doFilter(request, response);
    }
    // 요청 URI가 화이트리스트에 포함되어 있는지 확인
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
