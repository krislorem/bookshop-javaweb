package com.example.bookshop.servlet;

import com.example.bookshop.model.Goods;
import com.example.bookshop.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Slf4j
@WebServlet(name = "admin_goods_edit", urlPatterns = "/admin/goods_edit")
public class AdminGoodsEditServlet extends HttpServlet {
    private final GoodsService gService = new GoodsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            Goods g = new Goods();
            int pageNumber = 1;
            int type = 0;
            for (FileItem item : list) {
                if (item.isFormField()) {              //isFormField():是否形成字段   true:普通类型;false:其它文件类型
                    switch (item.getFieldName()) {
                        case "id":
                            log.info("id: " + item.getString("utf-8"));
                            g.setId(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "name":
                            log.info("name: " + item.getString("utf-8"));
                            g.setName(item.getString("utf-8"));
                            break;
                        case "price":
                            log.info("price: " + item.getString("utf-8"));
                            g.setPrice(Float.parseFloat(item.getString("utf-8")));
                            break;
                        case "intro":
                            log.info("intro: " + item.getString("utf-8"));
                            g.setIntro(item.getString("utf-8"));
                            break;
                        case "author":
                            log.info("author: " + item.getString("utf-8"));
                            g.setAuthor(item.getString("utf-8"));
                            break;
                        case "press":
                            log.info("press: " + item.getString("utf-8"));
                            g.setPress(item.getString("utf-8"));
                            break;
                        case "isbn":
                            log.info("isbn: " + item.getString("utf-8"));
                            g.setIsbn(item.getString("utf-8"));
                            break;
                        case "cover":
                            log.info("cover: " + item.getString("utf-8"));
                            g.setCover(item.getString("utf-8"));
                            break;
                        case "image1":
                            log.info("image1: " + item.getString("utf-8"));
                            g.setImage1(item.getString("utf-8"));
                            break;
                        case "image2":
                            log.info("image2: " + item.getString("utf-8"));
                            g.setImage2(item.getString("utf-8"));
                            break;
                        case "stock":
                            log.info("stock: " + item.getString("utf-8"));
                            g.setStock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "typeid":
                            log.info("typeid: " + item.getString("utf-8"));
                            g.setTypeid(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "pageNumber":
                            log.info("pageNumber: " + item.getString("utf-8"));
                            pageNumber = Integer.parseInt(item.getString("utf-8"));
                            break;
                        case "type":
                            log.info("type: " + item.getString("utf-8"));
                            type = Integer.parseInt(item.getString("utf-8"));
                            break;
                    }
                } else {
                    if (item.getInputStream().available() <= 0) continue;
                    String fileName = item.getName();          //获取上传的文件名
                    fileName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = "/" + new Date().getTime() + fileName;
                    String path = this.getServletContext().getRealPath("/picture") + fileName;
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    while (in.read(buffer) > 0) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    switch (item.getFieldName()) {
                        case "cover":
                            g.setCover("/picture" + fileName);
                            break;
                        case "image1":
                            g.setImage1("/picture" + fileName);
                            break;
                        case "image2":
                            g.setImage2("/picture" + fileName);
                            break;
                    }
                }
            }
            gService.update(g);
            request.getRequestDispatcher("/admin/goods_list?pageNumber=" + pageNumber + "&type=" + type).forward(request, response);
        } catch (FileUploadException e) {
            log.info("FileUploadException", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}

