package be.g00glen00b.service.data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ArticleSpecifications {

    public static final String WILDCARD = "%";

    public static Specification<Article> withSearch(String search) {
        if (StringUtils.isNotEmpty(search)) {
            String pattern = WILDCARD + StringUtils.lowerCase(search) + WILDCARD;
            return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("title")), pattern),
                cb.like(cb.lower(root.get("text")), pattern),
                cb.like(cb.lower(root.get("slug")), pattern));
        } else {
            return null;
        }
    }

    public static Specification<Article> withUsername(String user) {
        if (user != null) {
            return (root, query, cb) -> cb.equal(cb.lower(root.get("username")), user);
        } else {
            return null;
        }
    }
}
