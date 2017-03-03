package be.g00glen00b.service.web.model;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class ArticleSortFormatter implements Formatter<ArticleSort> {
    @Override
    public ArticleSort parse(String s, Locale locale) throws ParseException {
        return Arrays.stream(ArticleSort.values())
            .filter(sort -> StringUtils.equalsIgnoreCase(sort.getSortString(), s))
            .findFirst().orElseThrow(() -> new ParseException("Could not find proper sort", 0));
    }

    @Override
    public String print(ArticleSort articleSort, Locale locale) {
        return articleSort.getSortString();
    }
}
