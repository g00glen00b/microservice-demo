package be.g00glen00b.service.web;

import javax.validation.Valid;
import be.g00glen00b.service.data.ArticleQuery;
import be.g00glen00b.service.data.ArticleService;
import be.g00glen00b.service.web.model.ArticleDTO;
import be.g00glen00b.service.web.model.ArticleSort;
import be.g00glen00b.service.web.model.ArticlesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping
    public ArticlesDTO findAll(
        @RequestParam(required = false) String search,
        @RequestParam(required = false) String username,
        @RequestParam(required = false, defaultValue = "0") int offset,
        @RequestParam(required = false, defaultValue = "10") int limit,
        @RequestParam(required = false, defaultValue = "-created") ArticleSort sort
    ) {
        return service.findAll(new ArticleQuery(search, username), offset, limit, sort);
    }

    @GetMapping("/{slug}")
    public ArticleDTO findOne(
        @PathVariable String slug
    ) {
        return service.findOne(slug);
    }

    @DeleteMapping("/{slug}")
    public void delete(
        @PathVariable String slug
    ) {
        service.delete(slug);
    }

    @PostMapping
    public ArticleDTO save(@Valid @RequestBody ArticleDTO input) {
        return service.save(input);
    }
}
