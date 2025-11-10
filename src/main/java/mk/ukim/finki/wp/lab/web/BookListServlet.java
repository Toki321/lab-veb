package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

// @WebServlet(name = "BookListServlet", urlPatterns = "/")
public class BookListServlet extends HttpServlet {

    private final BookService bookService;
    private final SpringTemplateEngine templateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine templateEngine) {
        this.bookService = bookService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchText = req.getParameter("searchText");
        String searchRatingStr = req.getParameter("searchRating");

        List<Book> books;

        if (searchText != null && !searchText.isEmpty() && searchRatingStr != null && !searchRatingStr.isEmpty()) {
            try {
                Double searchRating = Double.parseDouble(searchRatingStr);
                books = bookService.searchBooks(searchText, searchRating);
            } catch (NumberFormatException e) {
                books = bookService.listAll();
            }
        } else {
            books = bookService.listAll();
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("books", books);
        context.setVariable("searchText", searchText != null ? searchText : "");
        context.setVariable("searchRating", searchRatingStr != null ? searchRatingStr : "");

        templateEngine.process("listBooks.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}