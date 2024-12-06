package com.example.tp1jsp;

import org.springframework.data.repository.CrudRepository;

interface ArticleRepository extends CrudRepository<Article, Integer> {
}
