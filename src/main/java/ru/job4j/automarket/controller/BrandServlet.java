package ru.job4j.automarket.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.automarket.model.Brand;
import ru.job4j.automarket.persistence.HbmBrand;
import ru.job4j.automarket.persistence.Store;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Class BrandServlet.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 16.10.2021
 */
@Slf4j
@WebServlet("/brand.do")
public class BrandServlet extends HttpServlet {
    private static final Store<Brand> BRAND_STORE = HbmBrand.getStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        List<Brand> brands = BRAND_STORE.findAll();
        JsonArray jsonArray = new JsonArray();
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        fillJsonArray(jsonArray, brands);
        writer.println(jsonArray);
    }

    private void fillJsonArray(JsonArray jsonArray, List<Brand> brands) {
        brands.forEach(brand -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("brandId", brand.getId());
            jsonObject.addProperty("brandName", brand.getName());
            jsonArray.add(jsonObject);
        });
    }
}
