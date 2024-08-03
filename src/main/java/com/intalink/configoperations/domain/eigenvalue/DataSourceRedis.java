package com.intalink.configoperations.domain.eigenvalue;
import java.util.List;
import lombok.Data;

@Data
public class DataSourceRedis {
    private String dataSource;
    private List<DataItem> dataItems;
}