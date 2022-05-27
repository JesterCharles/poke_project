package com.revature.pokedex.element_type;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ElementTypeServlet extends HttpServlet {
    private final ElementTypeServices elementTypeServices;
    private final ObjectMapper mapper;

    public ElementTypeServlet(ElementTypeServices elementTypeServices, ObjectMapper mapper) {
        this.elementTypeServices = elementTypeServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null){
            ElementType elementType = elementTypeServices.readById(req.getParameter("id"));
            String payload = mapper.writeValueAsString(elementType);
            resp.getWriter().write(payload);
            return;
        }

        List<ElementType> elementTypes = elementTypeServices.readAll();
        String payload = mapper.writeValueAsString(elementTypes);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ElementType elementType = mapper.readValue(req.getInputStream(), ElementType.class);
        ElementType persistedElementType = elementTypeServices.create(elementType);

        String payload = mapper.writeValueAsString(persistedElementType);
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authTrainer") == null){
            resp.getWriter().write("Unauthorized request - not log in as registered user");
            resp.setStatus(401); // Unauthorized
            return false;
        }
        return true;
    }

}
