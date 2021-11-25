package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet",urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-Line]
        response.setStatus(HttpServletResponse.SC_OK);

        //[response-Header]
        response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // 케시 무효화
        response.setHeader("Pragma","no=cache"); //이전 케시 무효화
        response.setHeader("my-header","hello");


        //[Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);
//[message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");

    }
    //컨텐트
    private void content(HttpServletResponse response) {
//Content-Type: text/plain;charset=utf-8
//Content-Length: 2
//response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }
    //쿠키
    private void cookie(HttpServletResponse response) {
//Set-Cookie: myCookie=good; Max-Age=600;
//response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }
    //리다이렉트
    private void redirect(HttpServletResponse response) throws IOException {
//Status Code 302
//Location: /basic/hello-form.html
//response.setStatus(HttpServletResponse.SC_FOUND); //302
//response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }


}
