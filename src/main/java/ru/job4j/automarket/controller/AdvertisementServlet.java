package ru.job4j.automarket.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.automarket.model.*;
import ru.job4j.automarket.persistence.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

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
    private static final Store<Photo> PHOTO_STORE = HbmPhoto.getStore();
    private static final Store<Brand> BRAND_STORE = HbmBrand.getStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        List<Advertisement> advertisements = ADD_STORE.findAll();
        JsonArray jsonArray = new JsonArray();
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
        fillJsonArray(jsonArray, advertisements);
        writer.println(jsonArray);
    }

    private void fillJsonArray(JsonArray jsonArray, List<Advertisement> ads) {
        ads.forEach(ad -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("addId", ad.getId());
            jsonObject.addProperty("carId", ad.getCar().getId());
            jsonObject.addProperty("brand", ad.getCar().getBrand().getName());
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
            if (list.size() != 0) {
                jsonObject.addProperty("photo", list.get(0).getName());
            } else {
                jsonObject.addProperty("photo", "empty");
            }
            jsonArray.add(jsonObject);
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        final Advertisement advertisement = Advertisement.of();
        final Car car = new Car();
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("images" + File.separator + "automarket" + File.separator + "car_photo");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String field = item.getFieldName();
                    if ("brand".equals(field)) {
                        car.setBrand(BRAND_STORE.findByName(field).get(0));
                    } else if ("model".equals(field)) {
                        car.setModel(item.getString("UTF-8"));
                    } else if ("yearRelease".equals(field)) {
                        car.setYear(item.getString("UTF-8"));
                    } else if ("color".equals(field)) {
                        car.setColor(item.getString("UTF-8"));
                    } else if ("engineType".equals(field)) {
                        car.setEngineType(item.getString("UTF-8"));
                    } else if ("enginePower".equals(field)) {
                        car.setEnginePower(item.getString("UTF-8"));
                    } else if ("engineVolume".equals(field)) {
                        car.setEngineVolume(item.getString("UTF-8"));
                    } else if ("body".equals(field)) {
                        car.setBody(item.getString("UTF-8"));
                    } else if ("transmission".equals(field)) {
                        car.setTransmission(item.getString("UTF-8"));
                    } else if ("gear".equals(field)) {
                        car.setGear(item.getString("UTF-8"));
                    } else if ("mileage".equals(field)) {
                        car.setMileage(item.getString("UTF-8"));
                    } else if ("price".equals(field)) {
                        advertisement.setPrice(item.getString("UTF-8"));
                    } else if ("city".equals(field)) {
                        advertisement.setCity(item.getString("UTF-8"));
                    } else if ("desc".equals(field)) {
                        advertisement.setDescription(item.getString("UTF-8"));
                    }
                } else {
                    if (!item.getName().isEmpty()) {
                        String name = UUID.randomUUID() + item.getName();
                        String path = folder + File.separator + name;
                        File file = new File(path);
                        try (FileOutputStream out = new FileOutputStream(file)) {
                            out.write(item.getInputStream().readAllBytes());
                        }
                        Photo photo = PHOTO_STORE.add(Photo.of(name));
                        advertisement.addPhoto(photo);
                    }
                }
            }
        } catch (FileUploadException e) {
            log.error(e.getMessage(), e);
        }
        if (req.getParameter("id") != null) {
            advertisement.setId(Integer.parseInt(req.getParameter("id")));
        }
        CAR_STORE.add(car);
        advertisement.setCar(car);
        advertisement.setUser((User) req.getSession().getAttribute("user"));
        ADD_STORE.add(advertisement);
        resp.sendRedirect(req.getContextPath());
    }
}
