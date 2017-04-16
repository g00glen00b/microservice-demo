package be.g00glen00b.service.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import be.g00glen00b.service.ArticleNotFoundException;
import be.g00glen00b.service.security.service.UserService;
import be.g00glen00b.service.web.model.ArticleDTO;
import be.g00glen00b.service.web.model.ArticleSort;
import be.g00glen00b.service.web.model.ArticlesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.Specifications.where;

import static be.g00glen00b.service.data.ArticleSpecifications.withSearch;
import static be.g00glen00b.service.data.ArticleSpecifications.withUsername;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository repository;
    private UserService userService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public ArticlesDTO findAll(ArticleQuery query, int offset, int limit, ArticleSort sort) {
        Page<Article> page = repository.findAll(getSpecification(query), getPageRequst(offset, limit, sort));
        return new ArticlesDTO(offset, limit, page.getTotalElements(), getDTO(page.getContent()));
    }

    @Override
    public ArticleDTO findOne(String slug) {
        return ArticleDTO.fromEntity(repository.findBySlug(slug).orElseThrow(ArticleNotFoundException::new));
    }

    @Override
    @Transactional
    public ArticleDTO save(@Valid ArticleDTO dto) {
        Article entity = new Article(dto.getTitle(), dto.getText(), userService.getUsername(), dto.getSlug(), LocalDateTime.now());
        return ArticleDTO.fromEntity(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(String slug) {
        Article article = repository.findBySlug(slug).orElseThrow(ArticleNotFoundException::new);
        repository.delete(article);
    }

    private List<ArticleDTO> getDTO(List<Article> content) {
        return content.stream()
            .map(ArticleDTO::fromEntity)
            .collect(Collectors.toList());
    }

    private Specification<Article> getSpecification(ArticleQuery query) {
        return where(withSearch(query.getSearch()))
            .and(withUsername(query.getUsername()));
    }

    private OffsetPageRequest getPageRequst(int offset, int limit, ArticleSort sort) {
        Sort.Direction direction = sort.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        return new OffsetPageRequest(offset, limit, new Sort(new Sort.Order(direction, sort.getField())));
    }
}
