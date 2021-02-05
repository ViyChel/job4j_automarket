package ru.job4j.automarket.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class DownloadServlet.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 22.01.2021
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("image/png; image/jpeg");
        String fileName = req.getParameter("name");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        File file;
        if (!"empty".equals(fileName)) {
            file = new File("images" + File.separator
                    + "automarket" + File.separator
                    + "car_photo" + File.separator
                    + fileName);
        } else {
            file = new File(req.getContextPath() + "/default.png");
        }
        try (FileInputStream in = new FileInputStream(file)) {
            resp.getOutputStream().write(in.readAllBytes());
        }
    }
}
