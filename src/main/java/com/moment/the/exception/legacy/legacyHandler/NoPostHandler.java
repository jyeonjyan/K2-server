package com.moment.the.exception.legacy.legacyHandler;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class NoPostHandler {
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/exception/noPost");
    }
}
