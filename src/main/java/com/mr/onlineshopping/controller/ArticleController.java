package com.mr.onlineshopping.controller;

import com.mr.onlineshopping.dto.ArticleDTO;
import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.exceptions.ArticleNotFound;
import com.mr.onlineshopping.interfaces.ArticleFunctions;
import com.mr.onlineshopping.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ArticleController {

    // Autowired del mapper per i DTO
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleFunctions articleService;

    // GET Articles
    @GetMapping("articles")
    public ResponseEntity<ApiResponse<List<ArticleDTO>>> getArticles() {
        List<Article> articles = articleService.getAllArticle();

        List<ArticleDTO> articlesDTO = articles.stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .toList();
//                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(articlesDTO, "Success", HttpStatus.OK.value()));
    }

    // GET Cart by cart_id
    @GetMapping("articles/{articleId}")
    public ResponseEntity<ApiResponse<ArticleDTO>> getArticles(@PathVariable("articleId") int articleId) throws ArticleNotFound {
        Article article = articleService.getArticleById(articleId).orElseThrow(() -> new ArticleNotFound(articleId));
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class); // Setto tutti gli attributi del DTO con il mapper
        return ResponseEntity.ok(new ApiResponse<>(articleDTO, "Success", HttpStatus.OK.value()));
    }

    
}
