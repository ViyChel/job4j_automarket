package ru.job4j.automarket.controller;

import lombok.extern.slf4j.Slf4j;
import ru.job4j.automarket.model.Advertisement;
import ru.job4j.automarket.model.Car;
import ru.job4j.automarket.model.User;
import ru.job4j.automarket.persistence.HbmAdvertisement;
import ru.job4j.automarket.persistence.HbmCar;
import ru.job4j.automarket.persistence.HbmUser;
import ru.job4j.automarket.persistence.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class AdvertisementServlet.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 14.01.2021
 */
@Slf4j
@WebServlet("/add.do")
public class AdvertisementServlet extends HttpServlet {
    private static final Store<Advertisement> ADD_STORE = HbmAdvertisement.getStore();
    private static final Store<Car> CAR_STORE = HbmCar.getStore();
    private static final Store<User> USER_STORE = HbmUser.getStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        resp.sendRedirect(req.getContextPath() + "/advertisement.do");
    }
}
