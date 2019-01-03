package com.tiny.servlet;

import com.tiny.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@WebServlet("/checkCode.action")
public class GeneratedCode extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession(true);
        Map<String, BufferedImage> image = ImageUtil.createImage();
        String key = image.keySet().iterator().next();
        session.setAttribute("verifyCode", key);

        System.out.println(key);
        BufferedImage codeImage = image.get(key);

//        ImageWriter imageWriter = ImageIO.getImageWritersBySuffix("jpg").next();
        ServletOutputStream servletOutputStream = response.getOutputStream();
//        imageWriter.setOutput(servletOutputStream);
//        imageWriter.write(codeImage);
        ImageIO.write(codeImage, "jpg", servletOutputStream);
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
