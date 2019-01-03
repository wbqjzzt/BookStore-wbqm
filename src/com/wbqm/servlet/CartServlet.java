package com.wbqm.servlet;

import com.wbqm.module.ShoppingCart;
import com.wbqm.service.IProductService;
import com.wbqm.service.ServiceFactory;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/car.do")
public class CartServlet extends HttpServlet {
    private static final String METHOD_ADD      = "0";
    private static final String METHOD_DELETE   = "1";
    private static final String METHOD_EDIT     = "2";
    private static final String METHOD_CLEAR    = "3";
    private static final String METHOD_LIST     = "4";
    private static final String METHOD_RECORD   = "5";

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
        try {
            Map<Integer, ShoppingCart> map = (Map<Integer, ShoppingCart>) session.getAttribute("s_car");
            Map<Integer, ShoppingCart> delMap = (Map<Integer, ShoppingCart>) session.getAttribute("ciDel");
            if (null != method) {
                switch (method) {
                    case METHOD_ADD : add(request, response); break;

                    case METHOD_LIST : list(request, response, map); break;

                    case METHOD_DELETE : delete(request, response, map); break;

                    case METHOD_CLEAR : clear(request, response, map, delMap); break;

                    case METHOD_EDIT : edit(request, response, map); break;

                    case METHOD_RECORD : record(request, response, map, delMap); break;

                    default: break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void record(HttpServletRequest request, HttpServletResponse response, Map<Integer, ShoppingCart> map,
                        Map<Integer, ShoppingCart> delMap) throws ServletException,
            IOException {
        int recordId = Integer.parseInt(request.getParameter("id"));

        if (delMap.containsKey(recordId)) {
            if (map.containsKey(recordId)) {
                map.get(recordId).setNum(map.get(recordId).getNum() + delMap.get(recordId).getNum());

            } else {
                if (null == map.get(recordId)) {
                    map.put(recordId, delMap.get(recordId));
                } else {
                    map.get(recordId).setNum(delMap.get(recordId).getNum());

                }
//                delMap.remove(recordId);
//                list(request, response, map);
            }

            delMap.remove(recordId);
            list(request, response, map);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        boolean flag = true;
        Map<Integer, ShoppingCart> cartMap = null;
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            HttpSession session = request.getSession();
            if (session.getAttribute("s_user") == null) {
                flag = false;
            } else {
                cartMap = (Map<Integer, ShoppingCart>) session.getAttribute("s_car");
                if (null == cartMap) cartMap = new HashMap<>();

                if (cartMap.containsKey(id)) {
                    ShoppingCart cartItem = cartMap.get(id);
                    cartItem.setNum(cartItem.getNum() + 1);

                } else {
                    ShoppingCart cartItem = new ShoppingCart();
                    IProductService productService = ServiceFactory.getProductService();
                    cartItem.setProduct(productService.findBookById(id));
                    cartMap.put(id, cartItem);
                }
            }

            session.setAttribute("s_car", cartMap);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", flag);
            PrintWriter printWriter = response.getWriter();
            printWriter.print(jsonObject.toString());
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double totalNum(double totalNum) {
        BigDecimal total = new BigDecimal(totalNum);
        BigDecimal bigDecimal = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    private double total(Map<Integer, ShoppingCart> map, boolean flag) {
        double totalPrice = 0.0;
        if (flag) {
            for (Map.Entry<Integer, ShoppingCart> entry : map.entrySet()) {
                totalPrice += entry.getValue().getNum() * entry.getValue()
                        .getProduct().getFixedPrice();
            }

        } else {
            for (Map.Entry<Integer, ShoppingCart> entry : map.entrySet()) {
                totalPrice += entry.getValue().getNum() * entry.getValue()
                        .getProduct().getDangPrice();
            }
        }
        BigDecimal bigDecimal = new BigDecimal(totalPrice);
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

        return bigDecimal1.doubleValue();
    }
    private void delete(HttpServletRequest request, HttpServletResponse response, Map<Integer, ShoppingCart> map) throws ServletException,
            IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        HttpSession session = request.getSession();

            if (map.containsKey(id)) {
                Map<Integer, ShoppingCart> delMap = (Map<Integer, ShoppingCart>) session.getAttribute("ciDel");

                if (null == delMap) {
                    delMap = new HashMap<>();
                    ShoppingCart cartItem = new ShoppingCart();
                    cartItem.setNum(1);
                    cartItem.setProduct(map.get(id).getProduct());
                    cartItem.setDelete(true);
                    delMap.put(id, cartItem);
                    System.out.println("id: " + id + " === product Num: " + cartItem.getNum()
                            + " === ProductName: " +cartItem.getProduct().getProductName());

                } else {
                    ShoppingCart cartItem = delMap.get(id);
                    if (cartItem == null) {
                        cartItem = new ShoppingCart();
                        cartItem.setNum(1);
                        cartItem.setProduct(map.get(id).getProduct());
                        cartItem.setDelete(true);
                        delMap.put(id, cartItem);
                    } else {
                        cartItem.setNum(cartItem.getNum() + 1);

                    }

                    System.out.println("id: " + id + " === product Num: " + cartItem.getNum()
                            + " === ProductName: " +cartItem.getProduct().getProductName());

                }

                System.out.println(" All product keySet: " + delMap.keySet());
//                request.setAttribute("ciDel", delMap);
                session.setAttribute("ciDel", delMap);

            }

            if (map.get(id).getNum() != 1) {
                map.get(id).setNum(map.get(id).getNum() - 1);

            } else {
                map.remove(id);
            }

        list(request, response, map);
    }

    private void clear(HttpServletRequest request, HttpServletResponse response, Map<Integer, ShoppingCart> map,
                       Map<Integer, ShoppingCart> delMap) throws ServletException,
            IOException {
        map.clear();
        if (null != delMap) delMap.clear();
        list(request, response, map);
    }

    private void list(HttpServletRequest request, HttpServletResponse response, Map<Integer, ShoppingCart> map) throws ServletException,
            IOException {
        request.setAttribute("totalDang", total(map, false));
        request.setAttribute("totalSave", totalNum(total(map, true) - totalNum(total(map, false))));
        request.setAttribute("s_car", map);
        request.getRequestDispatcher("/WEB-INF/view/car/cart_list.jsp").forward(request, response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response,  Map<Integer, ShoppingCart> map) throws ServletException,
            IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        ShoppingCart cartItem = map.get(id);
        cartItem.setNum(Integer.valueOf(request.getParameter("num")));
        map.put(id, cartItem);
        list(request, response, map);
    }
}
