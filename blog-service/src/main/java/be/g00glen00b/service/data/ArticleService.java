package be.g00glen00b.service.data;

import be.g00glen00b.service.web.ArticleSort;
import be.g00glen00b.service.web.ArticlesDTO;

public interface ArticleService {
    ArticlesDTO findAll(ArticleQuery query, int offset, int limit, ArticleSort sort);
}
