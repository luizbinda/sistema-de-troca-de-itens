package com.colatina.sti.service.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "userIndex", shards = 1, replicas = 0, refreshInterval = "5s", createIndex = true)
public class User {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private Category category;

    @Field(type = FieldType.Long)
    private double price;

    public enum Category {
      CLOTHES,
      ELECTRONICS,
      GAMES;
    }

}
