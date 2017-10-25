package bookcase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import bookcase.model.Book;
import bookcase.service.BookService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("showBooks")
    public String tenBooks(Model model) {
        model.addAttribute("pageNum", 0);
        return tenBooks(model, 0);
    }

    @RequestMapping(value = "showBooks/{index}", method = RequestMethod.GET)
    public String tenBooks(Model model, @PathVariable("index") int index) {
        if(bookService.getNumberOfPages() == 0) {
            return "showBooks";
        }

        /*for pagination prev/next */
        if (index == -1)
            index = 0;
        if (index == bookService.getNumberOfPages())
            index = index - 1;

        List<Integer> refs = new ArrayList<>();
        for (int i = 1; i <= bookService.getNumberOfPages(); i++) {
            refs.add(i);
        }
        model.addAttribute("refs", refs);
        model.addAttribute("page", bookService.tenBooks(index));
        model.addAttribute("pageCount", bookService.getNumberOfPages());

        model.addAttribute("currentPage", index);

        return "showBooks";
    }

    @RequestMapping("addBook")
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @RequestMapping(value = "showBooks/add", method = RequestMethod.POST)
    public String addBook( @ModelAttribute("book") @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/addBook";
        }
        this.bookService.addBook(book);
        return "redirect:/showBooks";
    }

    @RequestMapping("/setRead/{id}/{currentPage}")
    public String setReadBook(@PathVariable("id") int id, @PathVariable("currentPage") int currentPage) {
        this.bookService.setRead(id);

        return "redirect:/showBooks/{currentPage}";
    }

    @RequestMapping("editBook/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "editBook";
    }

    @RequestMapping("showBooks/edit")
    public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        if (result.hasErrors())
            return "/editBook";

        bookService.updateBook(book);

        return "redirect:/showBooks";
    }

    @RequestMapping("/remove/{id}/{currentPage}")
    public String removeBook(@PathVariable("id") int id, @PathVariable("currentPage") int currentPage) {
        this.bookService.removeBook(id);

        return "redirect:/showBooks/{currentPage}";
    }

    @RequestMapping(value = "/searchAuthor", method = RequestMethod.GET)
    public String searchString(@RequestParam String author, Model model) {
        model.addAttribute("foundBooks", this.bookService.searchByAuthor(author));
        return "searchResult";
    }

    @RequestMapping(value = "/searchTheme", method = RequestMethod.GET)
    public String searchTheme(@RequestParam String theme, Model model) {
        model.addAttribute("foundBooks", this.bookService.searchByTheme(theme));
        return "searchResult";
    }

    @RequestMapping(value = "/searchByYear", method = RequestMethod.GET)
    public String searchYear(@RequestParam int year, Model model) {
        model.addAttribute("foundBooks", this.bookService.searchByYear(year));
        return "searchResult";
    }

    @RequestMapping(value = "/searchUnread", method = RequestMethod.GET)
    public String showUnread(Model model) {
        model.addAttribute("foundBooks", this.bookService.showUnread());
        return "searchResult";
    }

}
