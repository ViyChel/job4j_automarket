package ru.job4j.automarket.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.automarket.model.Advertisement;
import ru.job4j.automarket.model.Photo;
import ru.job4j.automarket.model.User;
import ru.job4j.automarket.persistence.HbmAdvertisement;
import ru.job4j.automarket.persistence.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class PrivateAccessServlet.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 28.01.2021
 */
@Slf4j
@WebServlet("/private.do")
public class PrivateAccessServlet extends HttpServlet {
    private static final Store<Advertisement> ADD_STORE = HbmAdvertisement.getStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        List<Advertisement> advertisements = ADD_STORE.findAll();
        JsonArray jsonArray = new JsonArray();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Advertisement> ads = advertisements.stream()
                    .filter(el -> el.getUser().equals(user))
                    .collect(Collectors.toList());
            fillJsonArray(jsonArray, ads);
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        writer.println(jsonArray);
    }

    private void fillJsonArray(JsonArray jsonArray, List<Advertisement> ads) {
        ads.forEach(ad -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("addId", ad.getId());
            jsonObject.addProperty("carId", ad.getCar().getId());
            jsonObject.addProperty("model", ad.getCar().getModel());
            jsonObject.addProperty("engineType", ad.getCar().getEngineType());
            jsonObject.addProperty("engineVolume", ad.getCar().getEngineVolume());
            jsonObject.addProperty("mileage", ad.getCar().getMileage());
            jsonObject.addProperty("gear", ad.getCar().getGear());
            jsonObject.addProperty("transmission", ad.getCar().getTransmission());
            jsonObject.addProperty("year", ad.getCar().getYear());
            jsonObject.addProperty("price", ad.getPrice());
            jsonObject.addProperty("city", ad.getCity());
            jsonObject.addProperty("date", ad.dateFormat(ad.getDate()));
            jsonObject.addProperty("status", ad.isStatus());
            List<Photo> list = ad.getPhotos();
            jsonObject.addProperty("photo", list.get(0).getName());
            jsonArray.add(jsonObject);
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        if (req.getParameter("action") != null) {
            if ("remove".equals(req.getParameter("action"))) {
                int id = Integer.parseInt(req.getParameter("id"));
                Advertisement temp = ADD_STORE.findById(id);
                temp.setStatus(true);
                ADD_STORE.replace(id, temp);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/private.jsp");
    }
}
