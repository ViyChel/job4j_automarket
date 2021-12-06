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
import java.io.*;
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
    private static final Store<Photo> PHOTO_STORE = HbmPhoto.getStore();
    private static final Store<Brand> BRAND_STORE = HbmBrand.getStore();
    private static final Store<Body> BODY_STORE = HbmBody.getStore();
    private static final String CHARSET = "UTF-8";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        setCharset(req);
        resp.setContentType("application/json; charset=UTF-8");
        String id = req.getParameter("id");
        if (id != null) {
            Advertisement ad = ADD_STORE.findById(Integer.parseInt(id));
            List<Brand> brands = BRAND_STORE.findAll();
            List<Body> bodies = BODY_STORE.findAll();
            req.setAttribute("ad", ad);
            req.setAttribute("brands", brands);
            req.setAttribute("bodies", bodies);
            req.getRequestDispatcher("/advertisement/add.jsp").forward(req, resp);
        } else {
            List<Advertisement> advertisements = ADD_STORE.findAll();
            JsonArray jsonArray = new JsonArray();
            PrintWriter writer = new PrintWriter(resp.getOutputStream(), true, StandardCharsets.UTF_8);
            fillJsonArray(jsonArray, advertisements);
            writer.println(jsonArray);
        }
    }

    private void fillJsonArray(JsonArray jsonArray, List<Advertisement> ads) {
        ads.forEach(ad -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("addId", ad.getId());
            jsonObject.addProperty("carId", ad.getCar().getId());
            jsonObject.addProperty("brand", ad.getCar().getBrand().getName());
            jsonObject.addProperty("model", ad.getCar().getModel());
            jsonObject.addProperty("engineType", ad.getCar().getEngine().getEngineType());
            jsonObject.addProperty("enginePower", ad.getCar().getEngine().getEnginePower());
            jsonObject.addProperty("engineVolume", ad.getCar().getEngine().getEngineVolume());
            jsonObject.addProperty("mileage", ad.getCar().getMileage());
            jsonObject.addProperty("gear", ad.getCar().getGear());
            jsonObject.addProperty("transmission", ad.getCar().getTransmission());
            jsonObject.addProperty("year", ad.getCar().getYear());
            jsonObject.addProperty("price", ad.getPrice());
            jsonObject.addProperty("city", ad.getCity());
            jsonObject.addProperty("date", ad.dateFormat(ad.getDate()));
            jsonObject.addProperty("status", ad.isStatus());
            List<Photo> list = ad.getPhotos();
            if (!list.isEmpty()) {
                jsonObject.addProperty("photo", list.get(0).getName());
            } else {
                jsonObject.addProperty("photo", "empty");
            }
            jsonArray.add(jsonObject);
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setCharset(req);
        resp.setContentType("text/html; charset=UTF-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        Advertisement advertisement = Advertisement.of();
        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem itemID : items) {
                String field = itemID.getFieldName();
                if (itemID.isFormField()) {
                    if ("id".equals(field)) {
                        if (!itemID.getString(CHARSET).isEmpty()) {
                            advertisement = ADD_STORE.findById(Integer.parseInt(itemID.getString(CHARSET)));
                        } else {
                            advertisement.setCar(Car.of());
                        }
                        break;
                    }
                }
            }
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String field = item.getFieldName();
                    if ("brand".equals(field)) {
                        Brand brand = BRAND_STORE.findByName(item.getString(CHARSET)).get(0);
                        advertisement.getCar().setBrand(brand);
                    } else if ("model".equals(field)) {
                        advertisement.getCar().setModel(item.getString(CHARSET));
                    } else if ("yearRelease".equals(field)) {
                        advertisement.getCar().setYear(item.getString(CHARSET));
                    } else if ("color".equals(field)) {
                        advertisement.getCar().setColor(item.getString(CHARSET));
                    } else if ("engineType".equals(field)) {
                        advertisement.getCar().getEngine().setEngineType(item.getString(CHARSET));
                    } else if ("enginePower".equals(field)) {
                        advertisement.getCar().getEngine().setEnginePower(item.getString(CHARSET));
                    } else if ("engineVolume".equals(field)) {
                        advertisement.getCar().getEngine().setEngineVolume(item.getString(CHARSET));
                    } else if ("body".equals(field)) {
                        Body body = BODY_STORE.findByName(item.getString(CHARSET)).get(0);
                        advertisement.getCar().setBody(body);
                    } else if ("transmission".equals(field)) {
                        advertisement.getCar().setTransmission(item.getString(CHARSET));
                    } else if ("gear".equals(field)) {
                        advertisement.getCar().setGear(item.getString(CHARSET));
                    } else if ("mileage".equals(field)) {
                        advertisement.getCar().setMileage(item.getString(CHARSET));
                    } else if ("price".equals(field)) {
                        advertisement.setPrice(item.getString(CHARSET));
                    } else if ("city".equals(field)) {
                        advertisement.setCity(item.getString(CHARSET));
                    } else if ("desc".equals(field)) {
                        advertisement.setDescription(item.getString(CHARSET));
                    }
                } else {
                    if (!item.getName().isEmpty()) {
                        File folder = new File(getServletContext().getRealPath(".") + File.separator + "image");
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }
                        String fileName = UUID.randomUUID() + item.getName();
                        File file = new File(folder + File.separator + fileName);
                        try (FileOutputStream out = new FileOutputStream(file)) {
                            out.write(item.getInputStream().readAllBytes());
                        }
                        Photo photo = PHOTO_STORE.add(Photo.of(fileName));
                        advertisement.addPhoto(photo);
                    }
                }
            }
        } catch (FileUploadException | UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        if (advertisement.getId() != 0) {
            ADD_STORE.replace(advertisement.getId(), advertisement);
        } else {
            advertisement.setUser((User) req.getSession().getAttribute("user"));
            ADD_STORE.add(advertisement);
        }
        try {
            resp.sendRedirect(req.getContextPath() + "/private.jsp");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void setCharset(HttpServletRequest req) {
        try {
            req.setCharacterEncoding(CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
