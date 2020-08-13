package com.upc.lw.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by liwei on 2020/6/21
 */
@Builder
@Data
@Document(collection = "demo_collection")
public class DemoEntity {
    private String title;

    private String description;

    private String by;

    private String url;
}
