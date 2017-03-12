package be.g00glen00b.service.data;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import be.g00glen00b.service.ArticleNotFoundException;
import be.g00glen00b.service.web.model.ArticleDTO;
import be.g00glen00b.service.web.model.ArticleSort;
import be.g00glen00b.service.web.model.ArticlesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.Specifications.where;

import static be.g00glen00b.service.data.ArticleSpecifications.withSearch;
import static be.g00glen00b.service.data.ArticleSpecifications.withUser;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository repository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository repository) {
        this.repository = repository;
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
    public ArticleDTO save(@Valid ArticleDTO dto) {
        return ArticleDTO.fromEntity(repository.save(new Article(dto.getTitle(), dto.getText(), null, dto.getSlug(), dto.getCreated())));
    }

    private List<ArticleDTO> getDTO(List<Article> content) {
        return content.stream()
            .map(ArticleDTO::fromEntity)
            .collect(Collectors.toList());
    }

    private Specification<Article> getSpecification(ArticleQuery query) {
        return where(withSearch(query.getSearch()))
            .and(withUser(query.getUser()));
    }

    private OffsetPageRequest getPageRequst(int offset, int limit, ArticleSort sort) {
        Sort.Direction direction = sort.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        return new OffsetPageRequest(offset, limit, new Sort(new Sort.Order(direction, sort.getField())));
    }
}
