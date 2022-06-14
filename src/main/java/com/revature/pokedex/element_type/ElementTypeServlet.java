package com.revature.pokedex.element_type;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class ElementTypeServlet {
    // @Autowired don't do this, makes charles sad and cry inside
    private final ElementTypeServices elementTypeServices;

    @Autowired // Once again not necessary, but I like it, to mee it's best practice
    public ElementTypeServlet(ElementTypeServices elementTypeServices) {
        this.elementTypeServices = elementTypeServices;
    }

    // TODO: IMPLEMENT ME

}
