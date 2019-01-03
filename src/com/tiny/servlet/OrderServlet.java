package com.tiny.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tiny.module.Address;
import com.tiny.module.Order;
import com.tiny.module.ShoppingCart;
import com.tiny.module.User;
import com.tiny.service.IAddressService;
import com.tiny.service.ServiceFactory;
//import org.json.JSONArray;
//import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

@WebServlet("/order.do")
public class OrderServlet extends HttpServlet {
    private static final String METHOD_CONFIRM = "0";
    private static final String METHOD_SUBMIT  = "1";
    private static final String METHOD_ADDRESS = "2";
    private static final String METHOD_END     = "3";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        Map<Integer, ShoppingCart> map = (Map<Integer, ShoppingCart>) session.getAttribute("s_car");
        if (null != method) {
            switch (method) {
                case METHOD_CONFIRM : confirm(request, response, map); break;

                case METHOD_SUBMIT : submit(request, response);break;

                case METHOD_ADDRESS : address(request, response);break;

                case METHOD_END : end(request, response);break;

                default : break;
            }
        }
    }

    private double total(Map<Integer, ShoppingCart> map) {
        double totalPrice = 0.0;
        for (Map.Entry<Integer, ShoppingCart> entry : map.entrySet()) {
            totalPrice += entry.getValue().getNum() * entry.getValue()
                    .getProduct().getDangPrice();
        }

        BigDecimal bigDecimal = new BigDecimal(totalPrice);
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal1.doubleValue();
    }

    private void submit(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.getRequestDispatcher("/WEB-INF/view/order/address_form.jsp").forward(request, response);
    }

    private void confirm(HttpServletRequest request, HttpServletResponse response,
                         Map<Integer, ShoppingCart> map) throws ServletException, IOException{
        request.setAttribute("totalDang", total(map));
        request.setAttribute("s_car", map);
        System.out.println("执行confirm");
        request.getRequestDispatcher("/WEB-INF/view/order/order_info.jsp").forward(request, response);
    }

    private void end(HttpServletRequest request, HttpServletResponse response) throws  ServletException,
            IOException {
        Address address = new Address();
        Order order = new Order();
        HttpSession session = request.getSession();
        try {
            String receiveName = request.getParameter("receiveName");
            String fullAddress = request.getParameter("fullAddress");
            String postalCode = request.getParameter("postalCode");
            String phone = request.getParameter("phone");
            String mobile = request.getParameter("mobile");
//            String addressId = request.getParameter("addressId");

            int userId = ((User) session.getAttribute("s_user")).getId();
            Map<Integer, ShoppingCart> map = (Map<Integer, ShoppingCart>) session.getAttribute("s_car");

            address.setUserId(userId);
            address.setFullAddress(fullAddress);
            address.setReceiveName(receiveName);
            address.setPostalCode(postalCode);
            address.setPhone(phone);
            address.setMobile(mobile);
//            System.out.println(addressId);
//            address.setId(Integer.parseInt(addressId));

            order.setFullAddress(address.getFullAddress());
            order.setUserId(userId);
            order.setMobile(address.getMobile());
            order.setOrderDesc("desc demo");
            order.setOrderTime(System.currentTimeMillis());
            order.setPhone(address.getPhone());
            order.setPostalCode(address.getPostalCode());
            order.setReceiveName(address.getReceiveName());
            order.setStatus(0);
            order.setTotalPrice(total(map));

            IAddressService addressService = ServiceFactory.getAddressService();
            if (null == addressService.getAddressById(address.getId()))
                addressService.saveAddress(address);

            int orderId = ServiceFactory.getOrderService().saveOrder(order);

            map.clear();
            session.setAttribute("s_car", map);

            request.setAttribute("orderId", orderId);
            request.setAttribute("totalPrice", order.getTotalPrice());
            request.getRequestDispatcher("/WEB-INF/view/order/order_ok.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void address(HttpServletRequest request, HttpServletResponse response) {
        int userId = ((User) request.getSession().getAttribute("s_user")).getId();
        List<Address> addresses;
        try {
            addresses = ServiceFactory.getAddressService().getAddressByUserId(userId);
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            for (Address address : addresses) {
                JSONObject json = (JSONObject) JSON.toJSON(address);
                jsonArray.add(json);
            }
            jsonObject.put("result", jsonArray);
            PrintWriter printWriter = response.getWriter();
            printWriter.print(jsonObject.toString());
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
